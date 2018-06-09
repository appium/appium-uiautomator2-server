/*
 * Copyright (C) 2013 DroidDriver committers
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
package io.appium.uiautomator2.model;

import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.accessibility.AccessibilityNodeInfo;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.lang.reflect.Field;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import io.appium.uiautomator2.common.exceptions.InvalidSelectorException;
import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.core.UiAutomatorBridge;
import io.appium.uiautomator2.utils.Attribute;
import io.appium.uiautomator2.utils.Device;
import io.appium.uiautomator2.utils.Logger;
import io.appium.uiautomator2.utils.NodeInfoList;

import static android.support.test.internal.util.Checks.checkNotNull;

/**
 * Find matching UiElement by XPath.
 */
public class XPathFinder implements Finder {
    private static final XPath XPATH_COMPILER = XPathFactory.newInstance().newXPath();
    private static final String UI_ELEMENT_INDEX = "uiElementIndex";
    private final String xPathString;

    private XPathFinder(String xPathString) {
        this.xPathString = checkNotNull(xPathString);
    }

    public static NodeInfoList getNodesList(String xpathExpression,
                                            @Nullable AccessibilityNodeInfo nodeInfo)
            throws InvalidSelectorException {
        final UiAutomationElement root = nodeInfo == null
                ? XPathFinder.refreshUiElementTree()
                : XPathFinder.refreshUiElementTree(nodeInfo);
        return new XPathFinder(xpathExpression).find(root);
    }

    private static void setNodeLocalName(Element element, String className) {
        try {
            Field localName = element.getClass().getDeclaredField("localName");
            localName.setAccessible(true);
            localName.set(element, tag(className));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            Logger.error("Unable to set field localName:" + e.getMessage());
        }
    }

    private static Element toDOMElement(UiElement<?, ?> uiElement, final Document document,
                                        final SparseArray<UiElement<?, ?>> uiElementsMapping) {
        String className = uiElement.getClassName();
        if (className == null) {
            className = "UNKNOWN";
        }
        Element element = document.createElement(simpleClassName(className));
        final int uiElementIndex = uiElementsMapping.size();
        uiElementsMapping.put(uiElementIndex, uiElement);

        /*
         * Setting the Element's className field.
         * Reason for setting className field in Element object explicitly,
         * className property might contain special characters like '$' if it is a Inner class and
         * just not possible to create Element object with special characters.
         * But Appium should consider Inner classes i.e special characters should be included.
         */
        setNodeLocalName(element, className);

        setAttribute(element, Attribute.INDEX, String.valueOf(uiElement.getIndex()));
        setAttribute(element, Attribute.CLASS, className);
        setAttribute(element, Attribute.RESOURCE_ID, uiElement.getResourceId());
        setAttribute(element, Attribute.PACKAGE, uiElement.getPackageName());
        setAttribute(element, Attribute.CONTENT_DESC, uiElement.getContentDescription());
        setAttribute(element, Attribute.TEXT, uiElement.getText());
        setAttribute(element, Attribute.CHECKABLE, uiElement.isCheckable());
        setAttribute(element, Attribute.CHECKED, uiElement.isChecked());
        setAttribute(element, Attribute.CLICKABLE, uiElement.isClickable());
        setAttribute(element, Attribute.ENABLED, uiElement.isEnabled());
        setAttribute(element, Attribute.FOCUSABLE, uiElement.isFocusable());
        setAttribute(element, Attribute.FOCUSED, uiElement.isFocused());
        setAttribute(element, Attribute.SCROLLABLE, uiElement.isScrollable());
        setAttribute(element, Attribute.LONG_CLICKABLE, uiElement.isLongClickable());
        setAttribute(element, Attribute.PASSWORD, uiElement.isPassword());
        if (uiElement.hasSelection()) {
            element.setAttribute(Attribute.SELECTION_START.getName(),
                    Integer.toString(uiElement.getSelectionStart()));
            element.setAttribute(Attribute.SELECTION_END.getName(),
                    Integer.toString(uiElement.getSelectionEnd()));
        }
        setAttribute(element, Attribute.SELECTED, uiElement.isSelected());
        element.setAttribute(Attribute.BOUNDS.getName(),
                uiElement.getBounds() == null ? null : uiElement.getBounds().toShortString());
        element.setAttribute(UI_ELEMENT_INDEX, Integer.toString(uiElementIndex));

        for (UiElement<?, ?> child : uiElement.getChildren()) {
            element.appendChild(toDOMElement(child, document, uiElementsMapping));
        }
        return element;
    }

    private static void setAttribute(Element element, Attribute attr, String value) {
        if (value != null) {
            element.setAttribute(attr.getName(), value);
        }
    }

    private static void setAttribute(Element element, Attribute attr, boolean value) {
        element.setAttribute(attr.getName(), String.valueOf(value));
    }

    public static UiAutomationElement refreshUiElementTree() {
        return UiAutomationElement.newRootElement(getRootAccessibilityNode(),
                NotificationListener.getInstance().getToastMessage());
    }

    public static UiAutomationElement refreshUiElementTree(AccessibilityNodeInfo nodeInfo) {
        return UiAutomationElement.newRootElement(nodeInfo, null /*Toast Messages*/);
    }

    public static AccessibilityNodeInfo getRootAccessibilityNode() throws UiAutomator2Exception {
        final long timeoutMillis = 10000;
        Device.waitForIdle();

        long end = SystemClock.uptimeMillis() + timeoutMillis;
        while (end > SystemClock.uptimeMillis()) {
            AccessibilityNodeInfo root = null;
            try {
                root = UiAutomatorBridge.getInstance().getQueryController().getAccessibilityRootNode();
            } catch (IllegalStateException ignore) {
                /*
                 * Sometimes getAccessibilityRootNode() throws
                 * "java.lang.IllegalStateException: Cannot perform this action on a sealed instance."
                 * Ignore it and try to re-get root node.
                 */
                Logger.debug("IllegalStateException was catched while invoking getAccessibilityRootNode() - ignore it");
            }
            if (root != null) {
                return root;
            }
            SystemClock.sleep(250);
        }
        final String message = "Timed out after %d milliseconds waiting for root AccessibilityNodeInfo";
        throw new UiAutomator2Exception(String.format(message, timeoutMillis));
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

    /**
     * returns by excluding inner class name.
     */
    private static String simpleClassName(String clsName) {
        clsName = tag(clsName);
        // we want the index of the inner class
        int start = clsName.lastIndexOf('$');
        // if this isn't an inner class, just find the start of the
        // top level class name.
        return start <= 0 ? clsName : clsName.substring(0, start);
    }

    @Override
    public String toString() {
        return xPathString;
    }

    @Override
    public NodeInfoList find(UiElement context) {
        final Document document;
        try {
            document = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .newDocument();
        } catch (ParserConfigurationException e) {
            throw new UiAutomator2Exception(e);
        }
        final SparseArray<UiElement<?, ?>> uiElementsMapping = new SparseArray<>();
        final Element domNode = toDOMElement((UiElement<?, ?>) context, document, uiElementsMapping);
        document.appendChild(domNode);
        final NodeList nodes;
        final NodeInfoList matchesList = new NodeInfoList();
        try {
            nodes = (NodeList) XPATH_COMPILER
                    .compile(xPathString)
                    .evaluate(domNode, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            throw new InvalidSelectorException(e);
        }
        for (int i = 0; i < nodes.getLength(); i++) {
            if (nodes.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            final NamedNodeMap attributes = nodes.item(i).getAttributes();
            final Node uiElementIndexAttribute = attributes.getNamedItem(UI_ELEMENT_INDEX);
            if (uiElementIndexAttribute == null) {
                continue;
            }
            final UiElement uiElement = uiElementsMapping
                    .get(Integer.parseInt(uiElementIndexAttribute.getNodeValue()));
            if (uiElement == null || uiElement.getClassName().equals("hierarchy")) {
                continue;
            }

            matchesList.addToList(uiElement.node);
        }
        return matchesList;
    }
}
