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
import android.view.accessibility.AccessibilityNodeInfo;

import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.QName;
import net.sf.saxon.s9api.SaxonApiException;
import net.sf.saxon.s9api.XdmItem;
import net.sf.saxon.s9api.XdmNode;
import net.sf.saxon.s9api.XdmNodeKind;
import net.sf.saxon.s9api.XdmValue;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.lang.IllegalStateException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;

import io.appium.uiautomator2.common.exceptions.ElementNotFoundException;
import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.core.UiAutomatorBridge;
import io.appium.uiautomator2.utils.Attribute;
import io.appium.uiautomator2.utils.Device;
import io.appium.uiautomator2.utils.Logger;
import io.appium.uiautomator2.utils.NodeInfoList;
import io.appium.uiautomator2.utils.Preconditions;

/**
 * Find matching UiElement by XPath.
 */
public class XPathFinder implements Finder {
    // Saxon XML API allows to use XPath 2.0 queries
    private static final Processor processor = new Processor(false);
    private final Document document;
    // The two maps should be kept in sync
    private final Map<String, UiElement<?, ?>> UI_ELEMENTS_MAP = new HashMap<>();
    private static final String UUID_ATTRIBUTE = "uuid";
    private static final QName QNAME_UUID_ATTRIBUTE = new QName(UUID_ATTRIBUTE);
    private final String xPathString;

    @Override
    public String toString() {
        return xPathString;
    }

    private XPathFinder(String xPathString) {
        this.xPathString = Preconditions.checkNotNull(xPathString);
        try {
            this.document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        } catch (ParserConfigurationException e) {
            throw new UiAutomator2Exception(e);
        }
    }

    @Override
    public NodeInfoList find(UiElement context) {
        Element domNode = toDOMElement((UiElement<?, ?>) context);
        getDocument().appendChild(domNode);
        try {
            final XdmNode xdm = processor
                    .newDocumentBuilder()
                    .build(new DOMSource(getDocument()));
            final XdmValue queryEvaluationResult = processor
                    .newXPathCompiler()
                    .evaluate(xPathString, xdm);
            final NodeInfoList matchesList = new NodeInfoList();
            for (final XdmItem item : queryEvaluationResult) {
                if (!(item instanceof XdmNode)) {
                    continue;
                }
                final XdmNode node = (XdmNode) item;
                if (node.getNodeKind() != XdmNodeKind.ELEMENT) {
                    continue;
                }

                final String uuid = node.getAttributeValue(QNAME_UUID_ATTRIBUTE);
                if (!UI_ELEMENTS_MAP.containsKey(uuid) ||
                        UI_ELEMENTS_MAP.get(uuid).getClassName().equals("hierarchy")) {
                    continue;
                }
                matchesList.addToList(UI_ELEMENTS_MAP.get(uuid).node);
            }
            return matchesList;
        } catch (SaxonApiException e) {
            throw new ElementNotFoundException(e);
        }
    }

    public static NodeInfoList getNodesList(String xpathExpression,
                                            @Nullable AccessibilityNodeInfo nodeInfo) {
        final UiAutomationElement root = nodeInfo == null
                ? XPathFinder.refreshUiElementTree()
                : XPathFinder.refreshUiElementTree(nodeInfo);
        return new XPathFinder(xpathExpression).find(root);
    }

    private Document getDocument() {
        return this.document;
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

    private Element toDOMElement(UiElement<?, ?> uiElement) {
        String className = uiElement.getClassName();
        if (className == null) {
            className = "UNKNOWN";
        }
        Element element = getDocument().createElement(simpleClassName(className));
        final String uuid = UUID.randomUUID().toString();
        UI_ELEMENTS_MAP.put(uuid, uiElement);

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
        element.setAttribute(UUID_ATTRIBUTE, uuid);

        for (UiElement<?, ?> child : uiElement.getChildren()) {
            element.appendChild(toDOMElement(child));
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
                NotificationListener.getToastMSGs());
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
     * @return The tag name used to build UiElement DOM. It is preferable to use
     * this to build XPath instead of String literals.
     */
    private static String tag(String className) {
        // the nth anonymous class has a class name ending in "Outer$n"
        // and local inner classes have names ending in "Outer.$1Inner"
        className = className.replaceAll("\\$[0-9]+", "\\$");
        return className;
    }

    /**
     * returns by excluding inner class name.
     */
    private static String simpleClassName(String name) {
        name = name.replaceAll("\\$[0-9]+", "\\$");
        // we want the index of the inner class
        int start = name.lastIndexOf('$');

        // if this isn't an inner class, just find the start of the
        // top level class name.
        if (start == -1) {
            return name;
        }
        return name.substring(0, start);
    }
}
