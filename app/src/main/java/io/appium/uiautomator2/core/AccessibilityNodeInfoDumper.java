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
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.Display;
import android.view.accessibility.AccessibilityNodeInfo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.lang.reflect.Field;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.model.NotificationListener;
import io.appium.uiautomator2.model.UiAutomationElement;
import io.appium.uiautomator2.model.UiElement;
import io.appium.uiautomator2.utils.Attribute;
import io.appium.uiautomator2.utils.Logger;

import static io.appium.uiautomator2.utils.AXWindowHelpers.currentActiveWindowRoot;
import static io.appium.uiautomator2.utils.XMLHelpers.DEFAULT_VIEW_CLASS_NAME;
import static io.appium.uiautomator2.utils.XMLHelpers.toNodeName;
import static io.appium.uiautomator2.utils.XMLHelpers.toSafeXmlString;

public class AccessibilityNodeInfoDumper {
    // https://github.com/appium/appium/issues/10204
    private static final int MAX_DEPTH = 70;
    private static final String UI_ELEMENT_INDEX = "uiElementIndex";

    private static void setNodeLocalName(Element element, String className) {
        try {
            Field localName = element.getClass().getDeclaredField("localName");
            localName.setAccessible(true);
            localName.set(element, tag(className));
        } catch (Exception e) {
            Logger.error(String.format("Unable to set field's localName to '%s'", className), e);
        }
    }

    private static Element toDOMElement(UiElement<?, ?> uiElement, final Document document,
                                        @Nullable final SparseArray<UiElement<?, ?>> uiElementsMapping,
                                        final int depth) {
        String className = uiElement.getClassName();
        if (className == null) {
            className = DEFAULT_VIEW_CLASS_NAME;
        }
        Element element = document.createElement(toNodeName(className));

        /*
         * Setting the Element's className field.
         * Reason for setting className field in Element object explicitly,
         * className property might contain special characters like '$' if it is a Inner class and
         * just not possible to create Element object with special characters.
         * But Appium should consider Inner classes i.e special characters should be included.
         */
        setNodeLocalName(element, className);

        for (Attribute attr : Attribute.values()) {
            setAttribute(element, attr, toSafeXmlString(uiElement.get(attr), "?"));
        }

        if (uiElementsMapping != null) {
            final int uiElementIndex = uiElementsMapping.size();
            uiElementsMapping.put(uiElementIndex, uiElement);
            element.setAttribute(UI_ELEMENT_INDEX, Integer.toString(uiElementIndex));
        }

        if (depth >= MAX_DEPTH) {
            Logger.error(String.format("The xml tree dump has reached its maximum depth of %s at " +
                    "'%s'. The recursion is stopped to avoid StackOverflowError", MAX_DEPTH, className));
        } else {
            for (UiElement<?, ?> child : uiElement.getChildren()) {
                element.appendChild(toDOMElement(child, document, uiElementsMapping, depth + 1));
            }
        }
        return element;
    }

    private static void setAttribute(Element element, Attribute attr, Object value) {
        if (value != null) {
            element.setAttribute(attr.getName(), String.valueOf(value));
        }
    }

    public static Document asXmlDocument() {
        return asXmlDocument(null, null);
    }

    public static Document asXmlDocument(@Nullable AccessibilityNodeInfo root,
                                         @Nullable SparseArray<UiElement<?, ?>> uiElementsMapping) {
        final long startTime = SystemClock.uptimeMillis();
        final Document document;
        try {
            document = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .newDocument();
        } catch (ParserConfigurationException e) {
            throw new UiAutomator2Exception(e);
        }
        final UiElement<?, ?> xpathRoot = root == null
                ? UiAutomationElement.rebuildForNewRoot(currentActiveWindowRoot(), NotificationListener
                .getInstance().getToastMessage())
                : UiAutomationElement.rebuildForNewRoot(root, null);
        final Element domNode = toDOMElement(xpathRoot, document, uiElementsMapping, 0);
        if (root == null) {
            alterDisplayInfo(domNode);
        }
        document.appendChild(domNode);
        Logger.info(String.format("XML tree fetch time: %sms", SystemClock.uptimeMillis() - startTime));
        return document;
    }

    private static void alterDisplayInfo(Element node) {
        Display display = UiAutomatorBridge.getInstance().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        node.setAttribute("rotation", Integer.toString(display.getRotation()));
        node.setAttribute("width", Integer.toString(size.x));
        node.setAttribute("height", Integer.toString(size.y));
    }

    /**
     * @param clsName the original class name
     * @return The tag name used to build UiElement DOM. It is preferable to use
     * this to build XPath instead of String literals.
     */
    private static String tag(String clsName) {
        // the nth anonymous class has a class name ending in "Outer$n"
        // and local inner classes have names ending in "Outer.$1Inner"
        return clsName
                .replaceAll("\\?+", "_")
                .replaceAll("\\$[0-9]+", "\\$");
    }
}
