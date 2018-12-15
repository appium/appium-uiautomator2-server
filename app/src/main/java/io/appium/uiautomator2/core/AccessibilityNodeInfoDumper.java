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
import android.util.SparseArray;
import android.util.Xml;
import android.view.Display;
import android.view.accessibility.AccessibilityNodeInfo;

import org.apache.commons.lang.StringUtils;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.StringWriter;

import androidx.annotation.Nullable;
import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.model.NotificationListener;
import io.appium.uiautomator2.model.UiElement;
import io.appium.uiautomator2.utils.Attribute;
import io.appium.uiautomator2.utils.Logger;

import static io.appium.uiautomator2.model.UiAutomationElement.rebuildForNewRoot;
import static io.appium.uiautomator2.utils.AXWindowHelpers.currentActiveWindowRoot;
import static io.appium.uiautomator2.utils.XMLHelpers.toNodeName;
import static io.appium.uiautomator2.utils.XMLHelpers.toSafeString;

public class AccessibilityNodeInfoDumper {
    // https://github.com/appium/appium/issues/10204
    private static final int MAX_DEPTH = 70;
    private static final String UI_ELEMENT_INDEX = "uiElementIndex";
    private static final String NON_XML_CHAR_REPLACEMENT = "?";
    private static final String NAMESPACE = "";
    private final static String DEFAULT_VIEW_CLASS_NAME = "android.view.View";

    @Nullable
    private final AccessibilityNodeInfo root;
    @Nullable
    private final SparseArray<UiElement<?, ?>> uiElementsMapping;
    private boolean shouldAddDisplayInfo;
    private XmlSerializer serializer;

    public AccessibilityNodeInfoDumper() {
        this(null, null);
    }

    public AccessibilityNodeInfoDumper(@Nullable AccessibilityNodeInfo root,
                                       @Nullable SparseArray<UiElement<?, ?>> uiElementsMapping) {
        this.root = root;
        this.uiElementsMapping = uiElementsMapping;
    }

    private void addDisplayInfo() throws IOException {
        Display display = UiAutomatorBridge.getInstance().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        serializer.attribute(NAMESPACE, "rotation", Integer.toString(display.getRotation()));
        serializer.attribute(NAMESPACE, "width", Integer.toString(size.x));
        serializer.attribute(NAMESPACE, "height", Integer.toString(size.y));
    }

    private static String toXmlNodeName(@Nullable String className) {
        if (StringUtils.isBlank(className)) {
            return DEFAULT_VIEW_CLASS_NAME;
        }

        String fixedName = className
                .replaceAll("[$@#&]", ".")
                .replaceAll("\\.+", ".")
                .replaceAll("(^\\.|\\.$)", "");
        fixedName = toNodeName(fixedName);
        if (StringUtils.isBlank(fixedName)) {
            fixedName = DEFAULT_VIEW_CLASS_NAME;
        }
        if (!fixedName.equals(className)) {
            Logger.info(String.format("Rewrote class name '%s' to XML node name '%s'", className, fixedName));
        }
        return fixedName;
    }

    private void serializeUiElement(UiElement<?, ?> uiElement, final int depth) throws IOException {
        final String className = uiElement.getClassName();
        final String nodeName = toXmlNodeName(className);
        serializer.startTag(NAMESPACE, nodeName);

        for (Attribute attr : uiElement.attributeKeys()) {
            if (!attr.isExposableToXml()) {
                continue;
            }
            Object value = uiElement.get(attr);
            if (value == null) {
                continue;
            }
            serializer.attribute(NAMESPACE, attr.getName(), toSafeString(String.valueOf(value), NON_XML_CHAR_REPLACEMENT));
        }
        if (shouldAddDisplayInfo) {
            addDisplayInfo();
            // Display info is only added once to the root node
            shouldAddDisplayInfo = false;
        }

        if (uiElementsMapping != null) {
            final int uiElementIndex = uiElementsMapping.size();
            uiElementsMapping.put(uiElementIndex, uiElement);
            serializer.attribute(NAMESPACE, UI_ELEMENT_INDEX, Integer.toString(uiElementIndex));
        }

        if (depth >= MAX_DEPTH) {
            Logger.error(String.format("The xml tree dump has reached its maximum depth of %s at " +
                    "'%s'. The recursion is stopped to avoid StackOverflowError", MAX_DEPTH, className));
        } else {
            for (UiElement<?, ?> child : uiElement.getChildren()) {
                serializeUiElement(child, depth + 1);
            }
        }
        serializer.endTag(NAMESPACE, nodeName);
    }

    public synchronized String dumpToXml() {
        serializer = Xml.newSerializer();
        if (uiElementsMapping != null) {
            uiElementsMapping.clear();
        }
        shouldAddDisplayInfo = root == null;

        final StringWriter writer = new StringWriter();
        try {
            serializer.setOutput(writer);
            serializer.startDocument("UTF-8", true);
            serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
            final long startTime = SystemClock.uptimeMillis();
            final UiElement<?, ?> xpathRoot = root == null
                    ? rebuildForNewRoot(currentActiveWindowRoot(), NotificationListener.getInstance().getToastMessage())
                    : rebuildForNewRoot(root, null);
            serializeUiElement(xpathRoot, 0);
            serializer.endDocument();
            Logger.info(String.format("The source XML tree has been fetched in %sms", SystemClock.uptimeMillis() - startTime));
            return writer.toString();
        } catch (Exception e) {
            throw new UiAutomator2Exception(e);
        }
    }
}
