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
import android.view.accessibility.AccessibilityNodeInfo;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.lang.IllegalStateException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import io.appium.uiautomator2.common.exceptions.ElementNotFoundException;
import io.appium.uiautomator2.common.exceptions.InvalidSelectorException;
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
  private static final XPath XPATH_COMPILER = XPathFactory.newInstance().newXPath();
  // document needs to be static so that when buildDomNode is called recursively
  // on children they are in the same document to be appended.
  private static Document document;
  // The two maps should be kept in sync
  private static final Map<UiElement<?, ?>, Element> TO_DOM_MAP =
      new HashMap<UiElement<?, ?>, Element>();
  private static final Map<Element, UiElement<?, ?>> FROM_DOM_MAP =
      new HashMap<Element, UiElement<?, ?>>();

  public static void clearData() {
    TO_DOM_MAP.clear();
    FROM_DOM_MAP.clear();
    document = null;
  }

  private final String xPathString;
  private final XPathExpression xPathExpression;
  private static UiAutomationElement rootElement;

  @Override
  public String toString() {
    return xPathString;
  }

  public XPathFinder(String xPathString) {
    this.xPathString = Preconditions.checkNotNull(xPathString);
    try {
      xPathExpression = XPATH_COMPILER.compile(xPathString);
    } catch (XPathExpressionException e) {
      throw new UiAutomator2Exception("xPathString=" + xPathString, e);
    }
  }

  @Override
  public NodeInfoList find(UiElement context) {
    Element domNode = getDomNode((UiElement<?, ?>) context);
    try {
      getDocument().appendChild(domNode);
      NodeList nodes = (NodeList) xPathExpression.evaluate(domNode, XPathConstants.NODESET);
      NodeInfoList list = new NodeInfoList();

      int nodesLength = nodes.getLength();
        for (int i = 0; i < nodesLength; i++) {
          if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE && !FROM_DOM_MAP.get(nodes.item(i)).getClassName().equals("hierarchy")) {
              list.addToList(FROM_DOM_MAP.get(nodes.item(i)).node);
          }
        }
      return list;
    } catch (XPathExpressionException e) {
      throw new ElementNotFoundException( e);
    } finally {
      try {
        getDocument().removeChild(domNode);
      } catch (DOMException e) {
        Logger.error(e, "Failed to clear document");
        document = null; // getDocument will create new
      }
    }
  }

  public static NodeInfoList getNodesList(String xpathExpression,  AccessibilityNodeInfo nodeInfo) throws InvalidSelectorException, ParserConfigurationException, UiAutomator2Exception {
    if(nodeInfo == null) {
      XPathFinder.refreshUiElementTree();
    } else {
      XPathFinder.refreshUiElementTree(nodeInfo);
    }
    XPathFinder finder = new XPathFinder(xpathExpression);
    return finder.find(finder.getRootElement());
  }

  private static Document getDocument() {
    if (document == null) {
      try {
        document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
      } catch (ParserConfigurationException e) {
        throw new UiAutomator2Exception(e);
      }
    }
    return document;
  }

  /**
   * Returns the DOM node representing this UiElement.
   */
  private static Element getDomNode(UiElement<?, ?> uiElement) {
    Element domNode = TO_DOM_MAP.get(uiElement);
    if (domNode == null) {
      domNode = buildDomNode(uiElement);
    }
    return domNode;
  }

  private static void setNodeLocalName(Element element, String className) {
    try {
      Field localName = element.getClass().getDeclaredField("localName");
      localName.setAccessible(true);
      localName.set(element, tag(className));
    } catch (NoSuchFieldException e) {
      Logger.error("Unable to set field localName:"+e.getMessage());
    } catch (IllegalAccessException e) {
      Logger.error("Unable to set field localName:"+e.getMessage());
    }
  }

  private static Element buildDomNode(UiElement<?, ?> uiElement) {
    String className = uiElement.getClassName();
    if (className == null) {
      className = "UNKNOWN";
    }
    Element element = getDocument().createElement(simpleClassName(className));
    TO_DOM_MAP.put(uiElement, element);
    FROM_DOM_MAP.put(element, uiElement);

    /**
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
    element.setAttribute(Attribute.BOUNDS.getName(), uiElement.getBounds()==null ? null : uiElement.getBounds().toShortString());

    for (UiElement<?, ?> child : uiElement.getChildren()) {
      element.appendChild(getDomNode(child));
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

  public UiAutomationElement getRootElement() {
    if (rootElement == null) {
      refreshUiElementTree();
    }
    return rootElement;
  }

  public static void refreshUiElementTree() {
    rootElement = UiAutomationElement.newRootElement(getRootAccessibilityNode(), NotificationListener.getToastMSGs());
  }

  public static void refreshUiElementTree(AccessibilityNodeInfo nodeInfo) {
    rootElement =UiAutomationElement.newRootElement(nodeInfo, null /*Toast Messages*/);
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
          /**
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
   *         this to build XPath instead of String literals.
   */
  public static String tag(String className) {
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
