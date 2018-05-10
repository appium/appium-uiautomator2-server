/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.appium.uiautomator2.core;

import android.graphics.Point;
import android.os.SystemClock;
import android.util.Xml;
import android.view.Display;
import android.view.accessibility.AccessibilityNodeInfo;

import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.StringWriter;

import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.utils.Logger;

import static io.appium.uiautomator2.utils.XMLHierarchy.safeCharSeqToString;


/**
 * The AccessibilityNodeInfoDumper in Android Open Source Project contains a lot of bugs which will
 * stay in old android versions forever. By coping the code of the latest version it is ensured that
 * all patches become available on old android versions. <p/> down ported bugs are e.g. { @link
 * https://code.google.com/p/android/issues/detail?id=62906 } { @link
 * https://code.google.com/p/android/issues/detail?id=58733 }
 */
public class AccessibilityNodeInfoDumper {
    private static final String[] NAF_EXCLUDED_CLASSES = new String[]{
            android.widget.GridView.class.getName(),
            android.widget.GridLayout.class.getName(),
            android.widget.ListView.class.getName(),
            android.widget.TableLayout.class.getName()};
    // https://github.com/appium/appium/issues/10204
    private static final int MAX_DEPTH = 70;

    /**
     * Using {@link AccessibilityNodeInfo} this method will walk the layout hierarchy and return
     * String object of xml hierarchy
     *
     * @param root The root accessibility node.
     */
    public static String getWindowXMLHierarchy(AccessibilityNodeInfo root) {
        final long startTime = SystemClock.uptimeMillis();
        StringWriter xmlDump = new StringWriter();
        try {
            XmlSerializer serializer = Xml.newSerializer();
            serializer.setOutput(xmlDump);
            serializer.startDocument("UTF-8", true);
            serializer.startTag("", "hierarchy");

            if (root != null) {
                Display display = UiAutomatorBridge.getInstance().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                final int width = size.x;
                final int height = size.y;

                serializer.attribute("", "rotation", Integer.toString(display.getRotation()));

                dumpNodeRec(root, serializer, 0, width, height, 0);
            }

            serializer.endTag("", "hierarchy");
            serializer.endDocument();

            /*FileWriter writer = new FileWriter(dumpFile);
            writer.write(stringWriter.toString());
            writer.close();*/
        } catch (IOException e) {
            throw new UiAutomator2Exception("Cannot dump views hierarchy to XML format", e);
        }
        final long endTime = SystemClock.uptimeMillis();
        Logger.info("Fetch time: " + (endTime - startTime) + "ms");
        return xmlDump.toString();
    }
    
    private static void dumpNodeRec(AccessibilityNodeInfo node, XmlSerializer serializer,
                                    int index, int width, int height, final int depth)
            throws IOException {
        // Some views might have unlimited number of children:
        // https://bugs.chromium.org/p/chromium/issues/detail?id=805014
        if (depth >= MAX_DEPTH) {
            Logger.error(String.format("The xml tree dump has reached its maximum depth of %s at " +
                            "%s. The recursion is stopped to avoid StackOverflowError", MAX_DEPTH,
                    node.toString()));
            return;
        }

        serializer.startTag("", "node");
        if (!isOfNafExcludedClass(node) && !isAccessibilityFriendly(node))
            serializer.attribute("", "NAF", Boolean.toString(true));
        serializer.attribute("", "index", Integer.toString(index));
        final String text;
        if (node.getRangeInfo() == null) {
            text = safeCharSeqToString(node.getText());
        } else {
            text = Float.toString(node.getRangeInfo().getCurrent());
        }
        serializer.attribute("", "text", text);
        serializer.attribute("", "class", safeCharSeqToString(node.getClassName()));
        serializer.attribute("", "package", safeCharSeqToString(node.getPackageName()));
        serializer.attribute("", "content-desc", safeCharSeqToString(node.getContentDescription()));
        serializer.attribute("", "checkable", Boolean.toString(node.isCheckable()));
        serializer.attribute("", "checked", Boolean.toString(node.isChecked()));
        serializer.attribute("", "clickable", Boolean.toString(node.isClickable()));
        serializer.attribute("", "enabled", Boolean.toString(node.isEnabled()));
        serializer.attribute("", "focusable", Boolean.toString(node.isFocusable()));
        serializer.attribute("", "focused", Boolean.toString(node.isFocused()));
        serializer.attribute("", "scrollable", Boolean.toString(node.isScrollable()));
        serializer.attribute("", "long-clickable", Boolean.toString(node.isLongClickable()));
        serializer.attribute("", "password", Boolean.toString(node.isPassword()));
        serializer.attribute("", "selected", Boolean.toString(node.isSelected()));
        serializer.attribute("", "bounds",
                AccessibilityNodeInfoHelper.getVisibleBoundsInScreen(node, width, height).toShortString());
        serializer.attribute("", "resource-id", safeCharSeqToString(node.getViewIdResourceName()));

        int count = node.getChildCount();
        for (int i = 0; i < count; i++) {
            AccessibilityNodeInfo child = node.getChild(i);
            if (child != null) {
                if (child.isVisibleToUser()) {
                    dumpNodeRec(child, serializer, i, width, height, depth + 1);
                    child.recycle();
                } else {
                    Logger.info(String.format("Skipping invisible child: %s", child.toString()));
                }
            } else {
                Logger.info(String.format("Null child %s/%s, parent: %s", i, count, node.toString()));
            }
        }
        serializer.endTag("", "node");
    }

    /**
     * The list of classes to exclude may not be complete. We're attempting to only reduce noise from
     * standard layout classes that may be falsely configured to accept clicks and are also
     * enabled.
     *
     * @return true if node is excluded.
     */
    private static boolean isOfNafExcludedClass(AccessibilityNodeInfo node) {
        String className = safeCharSeqToString(node.getClassName());
        for (String excludedClassName : NAF_EXCLUDED_CLASSES) {
            if (className.endsWith(excludedClassName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * We're looking for UI controls that are enabled, clickable but have no text nor
     * content-description. Such controls configuration indicate an interactive control is present
     * in the UI and is most likely not accessibility friendly. We refer to such controls here as
     * NAF controls (Not Accessibility Friendly)
     *
     * @return false if a node fails the check, true if all is OK
     */
    private static boolean isAccessibilityFriendly(AccessibilityNodeInfo node) {
        boolean isNaf = node.isClickable() && node.isEnabled() &&
                safeCharSeqToString(node.getContentDescription()).isEmpty() &&
                safeCharSeqToString(node.getText()).isEmpty();
        if (!isNaf) {
            return true;
        }
        // check children since sometimes the containing element is clickable
        // and NAF but a child's text or description is available. Will assume
        // such layout as fine.
        return isAnyDescendantAccessibilityFriendly(node, 0);
    }

    /**
     * This should be used when it's already determined that the node is NAF and a further check of
     * its children is in order. A node maybe a container such as LinerLayout and may be set to be
     * clickable but have no text or content description but it is counting on one of its children
     * to fulfill the requirement for being accessibility friendly by having one or more of its
     * children fill the text or content-description. Such a combination is considered by this
     * dumper as acceptable for accessibility.
     *
     * @return false if node fails the check.
     */
    private static boolean isAnyDescendantAccessibilityFriendly(AccessibilityNodeInfo node,
                                                                final int depth) {
        // Some views might have unlimited number of children:
        // https://bugs.chromium.org/p/chromium/issues/detail?id=805014
        if (depth >= MAX_DEPTH) {
            Logger.error(String.format("The NAF verification has reached its maximum depth of %s at " +
                            "%s. The recursion is stopped to avoid StackOverflowError", MAX_DEPTH,
                    node.toString()));
            return false;
        }

        int childCount = node.getChildCount();
        for (int x = 0; x < childCount; x++) {
            AccessibilityNodeInfo childNode = node.getChild(x);
            if (childNode == null) {
                Logger.info(String.format("Null child %s/%s, parent: %s", x, childCount, node.toString()));
                continue;
            }
            if (!safeCharSeqToString(childNode.getContentDescription()).isEmpty()
                    || !safeCharSeqToString(childNode.getText()).isEmpty()) {
                return true;
            }
            if (isAnyDescendantAccessibilityFriendly(childNode, depth + 1)) {
                return true;
            }
        }
        return false;
    }
}
