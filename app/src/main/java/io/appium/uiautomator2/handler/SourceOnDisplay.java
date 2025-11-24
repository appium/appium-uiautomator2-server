/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.appium.uiautomator2.handler;

import android.os.Build;
import android.view.Display;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObjectNotFoundException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.appium.uiautomator2.handler.request.SafeRequestHandler;
import io.appium.uiautomator2.http.AppiumResponse;
import io.appium.uiautomator2.http.IHttpRequest;
import io.appium.uiautomator2.model.internal.CustomUiDevice;
import io.appium.uiautomator2.utils.DisplayIdResolver;
import io.appium.uiautomator2.utils.Logger;

public class SourceOnDisplay extends SafeRequestHandler {
    private static final String TAG = SourceOnDisplay.class.getSimpleName();
    private static final Pattern DISPLAY_INDEX_PATTERN = Pattern.compile("displayIndex=(\\d+)");

    public SourceOnDisplay(String mappedUri) {
        super(mappedUri);
    }

    @Override
    protected AppiumResponse safeHandle(IHttpRequest request) throws UiObjectNotFoundException {
        Integer displayIndex = extractDisplayIndexFromQuery(request);

        String xmlSource;
        if (displayIndex != null) {
            int displayId = DisplayIdResolver.resolveDisplayId(displayIndex);
            Logger.info(TAG, String.format("Getting source for displayIndex=%d (displayId=%d)", displayIndex, displayId));
            xmlSource = dumpDisplayHierarchy(displayId);
        } else {
            Logger.info(TAG, "Getting source for all displays");
            xmlSource = dumpAllDisplaysHierarchy();
        }

        return new AppiumResponse(getSessionId(request), xmlSource);
    }

    private Integer extractDisplayIndexFromQuery(IHttpRequest request) {
        try {
            String uri = request.uri();
            Matcher matcher = DISPLAY_INDEX_PATTERN.matcher(uri);
            if (matcher.find()) {
                return Integer.parseInt(matcher.group(1));
            }
            return null;
        } catch (Exception e) {
            Logger.error(TAG, "Failed to extract displayIndex from query", e);
            return null;
        }
    }

    private String dumpDisplayHierarchy(int displayId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && displayId != Display.DEFAULT_DISPLAY) {
            return dumpMultiDisplayHierarchy(displayId);
        } else {
            return dumpDefaultDisplayHierarchy();
        }
    }

    private String dumpAllDisplaysHierarchy() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            return dumpDefaultDisplayHierarchy();
        }

        try {
            android.util.SparseArray<List<AccessibilityWindowInfo>> windowsOnAllDisplays =
                    CustomUiDevice.getInstance().getUiAutomation().getWindowsOnAllDisplays();

            StringBuilder xmlBuilder = new StringBuilder();
            xmlBuilder.append("<?xml version='1.0' encoding='UTF-8' standalone='yes' ?>\n");
            xmlBuilder.append("<hierarchies>\n");

            boolean hasValidContent = false;

            for (int i = 0; i < windowsOnAllDisplays.size(); i++) {
                int displayId = windowsOnAllDisplays.keyAt(i);
                List<AccessibilityWindowInfo> displayWindows = windowsOnAllDisplays.valueAt(i);

                if (displayWindows == null || displayWindows.isEmpty()) {
                    continue;
                }

                String displayXml = dumpWindowsToXml(displayId, displayWindows);
                if (displayXml != null && !displayXml.trim().isEmpty()) {
                    xmlBuilder.append(displayXml).append("\n");
                    hasValidContent = true;
                }
            }

            xmlBuilder.append("</hierarchies>");

            if (!hasValidContent) {
                Logger.warn(TAG, "No accessible UI content found on any display");
                return "<hierarchies><error>No accessible UI content found on any display</error></hierarchies>";
            }

            return xmlBuilder.toString();

        } catch (Exception e) {
            Logger.error(TAG, "Multi-display dump failed", e);
            return "<hierarchies><error>Dump failed: " + e.getMessage() + "</error></hierarchies>";
        }
    }

    private String dumpDefaultDisplayHierarchy() {
        try {
            UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            device.dumpWindowHierarchy(outputStream);
            return outputStream.toString("UTF-8");
        } catch (IOException e) {
            Logger.error(TAG, "Failed to dump default display hierarchy", e);
            return "<hierarchy><error>Failed to dump default display: " + e.getMessage() + "</error></hierarchy>";
        }
    }

    private String dumpMultiDisplayHierarchy(int displayId) {
        try {
            android.util.SparseArray<List<AccessibilityWindowInfo>> windowsOnAllDisplays =
                    CustomUiDevice.getInstance().getUiAutomation().getWindowsOnAllDisplays();

            List<AccessibilityWindowInfo> displayWindows = windowsOnAllDisplays.get(displayId);
            if (displayWindows == null || displayWindows.isEmpty()) {
                int displayIndex = DisplayIdResolver.resolveDisplayIndex(displayId);
                Logger.warn(TAG, String.format("No windows found for displayId=%d (displayIndex=%d)", displayId, displayIndex));
                return String.format("<hierarchy displayId=\"%d\" displayIndex=\"%d\"><error>No windows found on this display</error></hierarchy>",
                        displayId, displayIndex);
            }

            Logger.debug(TAG, String.format("Found %d windows for displayId=%d", displayWindows.size(), displayId));

            return dumpWindowsToXml(displayId, displayWindows);

        } catch (Exception e) {
            Logger.error(TAG, String.format("Multi-display dump failed for displayId=%d", displayId), e);
            int displayIndex = DisplayIdResolver.resolveDisplayIndex(displayId);
            return String.format("<hierarchy displayId=\"%d\" displayIndex=\"%d\"><error>Dump failed: %s</error></hierarchy>",
                    displayId, displayIndex, e.getMessage());
        }
    }

    private String dumpWindowsToXml(int displayId, List<AccessibilityWindowInfo> windows) {
        int displayIndex = DisplayIdResolver.resolveDisplayIndex(displayId);
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<hierarchy displayId=\"").append(displayId)
                .append("\" displayIndex=\"").append(displayIndex).append("\">\n");

        boolean hasValidContent = false;

        for (AccessibilityWindowInfo window : windows) {
            if (window == null) continue;

            AccessibilityNodeInfo root = window.getRoot();
            if (root == null) continue;

            try {
                String nodeXml = dumpSingleNodeInfo(root);
                if (nodeXml != null && !nodeXml.trim().isEmpty()) {
                    xmlBuilder.append("  ").append(nodeXml.replace("\n", "\n  ")).append("\n");
                    hasValidContent = true;
                }
            } catch (Exception e) {
                Logger.error(TAG, String.format("Failed to dump window on displayId=%d", displayId), e);
            } finally {
                if (root != null) {
                    root.recycle();
                }
            }
        }

        xmlBuilder.append("</hierarchy>");

        if (!hasValidContent) {
            Logger.warn(TAG, String.format("No accessible UI content found for displayId=%d", displayId));
            return String.format("<hierarchy displayId=\"%d\" displayIndex=\"%d\"><error>No accessible UI content found</error></hierarchy>",
                    displayId, displayIndex);
        }

        return xmlBuilder.toString();
    }

    private String dumpSingleNodeInfo(AccessibilityNodeInfo node) {
        if (node == null) {
            return "";
        }

        StringBuilder xmlBuilder = new StringBuilder();
        dumpNodeRecursive(node, xmlBuilder, 0);
        return xmlBuilder.toString();
    }

    private void dumpNodeRecursive(AccessibilityNodeInfo node, StringBuilder xmlBuilder, int depth) {
        if (node == null) {
            return;
        }

        String indent = getIndent(depth);

        // Start node
        xmlBuilder.append(indent).append("<node");

        // Add attributes
        appendAttribute(xmlBuilder, "index", String.valueOf(node.getChildCount()));
        appendAttribute(xmlBuilder, "text", safeGetText(node));
        appendAttribute(xmlBuilder, "resource-id", safeGetResourceId(node));
        appendAttribute(xmlBuilder, "class", safeGetClassName(node));
        appendAttribute(xmlBuilder, "package", safeGetPackageName(node));
        appendAttribute(xmlBuilder, "content-desc", safeGetContentDescription(node));
        appendAttribute(xmlBuilder, "checkable", String.valueOf(node.isCheckable()));
        appendAttribute(xmlBuilder, "checked", String.valueOf(node.isChecked()));
        appendAttribute(xmlBuilder, "clickable", String.valueOf(node.isClickable()));
        appendAttribute(xmlBuilder, "enabled", String.valueOf(node.isEnabled()));
        appendAttribute(xmlBuilder, "focusable", String.valueOf(node.isFocusable()));
        appendAttribute(xmlBuilder, "focused", String.valueOf(node.isFocused()));
        appendAttribute(xmlBuilder, "scrollable", String.valueOf(node.isScrollable()));
        appendAttribute(xmlBuilder, "long-clickable", String.valueOf(node.isLongClickable()));
        appendAttribute(xmlBuilder, "password", String.valueOf(node.isPassword()));
        appendAttribute(xmlBuilder, "selected", String.valueOf(node.isSelected()));

        // Add bounds
        android.graphics.Rect bounds = new android.graphics.Rect();
        node.getBoundsInScreen(bounds);
        appendAttribute(xmlBuilder, "bounds", String.format("[%d,%d][%d,%d]",
                bounds.left, bounds.top, bounds.right, bounds.bottom));

        int childCount = node.getChildCount();
        if (childCount == 0) {
            xmlBuilder.append("/>\n");
        } else {
            xmlBuilder.append(">\n");

            // Recursively process child nodes
            for (int i = 0; i < childCount; i++) {
                AccessibilityNodeInfo child = node.getChild(i);
                if (child != null) {
                    dumpNodeRecursive(child, xmlBuilder, depth + 1);
                    child.recycle();
                }
            }

            xmlBuilder.append(indent).append("</node>\n");
        }
    }

    private String getIndent(int depth) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            indent.append("  ");
        }
        return indent.toString();
    }

    private void appendAttribute(StringBuilder xmlBuilder, String name, String value) {
        if (value != null) {
            // XML escaping
            String escapedValue = value.replace("&", "&amp;")
                    .replace("<", "&lt;")
                    .replace(">", "&gt;")
                    .replace("\"", "&quot;")
                    .replace("'", "&apos;");
            xmlBuilder.append(" ").append(name).append("=\"").append(escapedValue).append("\"");
        }
    }

    private String safeGetText(AccessibilityNodeInfo node) {
        try {
            CharSequence text = node.getText();
            return text != null ? text.toString() : "";
        } catch (Exception e) {
            return "";
        }
    }

    private String safeGetResourceId(AccessibilityNodeInfo node) {
        try {
            CharSequence id = node.getViewIdResourceName();
            return id != null ? id.toString() : "";
        } catch (Exception e) {
            return "";
        }
    }

    private String safeGetClassName(AccessibilityNodeInfo node) {
        try {
            CharSequence className = node.getClassName();
            return className != null ? className.toString() : "";
        } catch (Exception e) {
            return "";
        }
    }

    private String safeGetPackageName(AccessibilityNodeInfo node) {
        try {
            CharSequence packageName = node.getPackageName();
            return packageName != null ? packageName.toString() : "";
        } catch (Exception e) {
            return "";
        }
    }

    private String safeGetContentDescription(AccessibilityNodeInfo node) {
        try {
            CharSequence contentDesc = node.getContentDescription();
            return contentDesc != null ? contentDesc.toString() : "";
        } catch (Exception e) {
            return "";
        }
    }
}