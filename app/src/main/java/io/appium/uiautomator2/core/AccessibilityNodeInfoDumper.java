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
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;

import androidx.annotation.Nullable;

import org.apache.commons.io.IOUtils;
import org.apache.xerces.jaxp.DocumentBuilderFactoryImpl;
import org.eclipse.wst.xml.xpath2.api.Item;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.api.XPath2Expression;
import org.eclipse.wst.xml.xpath2.processor.Engine;
import org.eclipse.wst.xml.xpath2.processor.XPathParserException;
import org.eclipse.wst.xml.xpath2.processor.util.DynamicContextBuilder;
import org.eclipse.wst.xml.xpath2.processor.util.StaticContextBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlSerializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Semaphore;

import io.appium.uiautomator2.common.exceptions.InvalidSelectorException;
import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.model.NotificationListener;
import io.appium.uiautomator2.model.UiElement;
import io.appium.uiautomator2.model.UiElementSnapshot;
import io.appium.uiautomator2.model.settings.NormalizeTagNames;
import io.appium.uiautomator2.model.settings.Settings;
import io.appium.uiautomator2.utils.Attribute;
import io.appium.uiautomator2.utils.Logger;
import io.appium.uiautomator2.utils.NodeInfoList;
import io.appium.uiautomator2.utils.StringHelpers;

import static io.appium.uiautomator2.utils.AXWindowHelpers.getCachedWindowRoots;
import static io.appium.uiautomator2.utils.XMLHelpers.toNodeName;
import static io.appium.uiautomator2.utils.XMLHelpers.toSafeString;
import static net.gcardone.junidecode.Junidecode.unidecode;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class AccessibilityNodeInfoDumper {
    private static final String UI_ELEMENT_INDEX = "uiElementIndex";
    private static final String NON_XML_CHAR_REPLACEMENT = "?";
    private static final String NAMESPACE = "";
    private static final String DEFAULT_VIEW_CLASS_NAME = View.class.getName();
    private static final String XML_ENCODING = "UTF-8";
    private final Semaphore RESOURCES_GUARD = new Semaphore(1);

    @Nullable
    private final AccessibilityNodeInfo root;
    private final SparseArray<UiElement<?, ?>> uiElementsMapping = new SparseArray<>();
    private final Set<Attribute> includedAttributes;
    private boolean shouldAddDisplayInfo;
    private XmlSerializer serializer;

    public AccessibilityNodeInfoDumper(@Nullable AccessibilityNodeInfo root,
                                       Set<Attribute> includedAttributes) {
        this.root = root;
        this.includedAttributes = includedAttributes;
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
        if (StringHelpers.isBlank(className)) {
            return DEFAULT_VIEW_CLASS_NAME;
        }

        String fixedName = className
                .replaceAll("[$@#&]", ".")
                .replaceAll("\\.+", ".")
                .replaceAll("(^\\.|\\.$)", "");

        if (Settings.get(NormalizeTagNames.class).getValue()) {
            // A workaround for the Apache Harmony bug described in https://github.com/appium/appium/issues/11854
            // The buggy implementation: https://android.googlesource.com/platform/dalvik/+/21d27c095fee51fd6eac6a68d50b79df4dc97d85/libcore/xml/src/main/java/org/apache/harmony/xml/dom/DocumentImpl.java#84
            fixedName = unidecode(fixedName).replaceAll("[^A-Za-z0-9\\-._]", "_");
        }

        fixedName = toNodeName(fixedName);
        if (StringHelpers.isBlank(fixedName)) {
            fixedName = DEFAULT_VIEW_CLASS_NAME;
        }
        if (!fixedName.equals(className)) {
            Logger.info(String.format("Rewrote class name '%s' to XML node name '%s'", className, fixedName));
        }
        return fixedName;
    }

    private void serializeUiElement(UiElement<?, ?> uiElement, boolean isIndexed) throws IOException {
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

        final int uiElementIndex = uiElementsMapping.size();
        uiElementsMapping.put(uiElementIndex, uiElement);
        if (isIndexed) {
            serializer.attribute(NAMESPACE, UI_ELEMENT_INDEX, Integer.toString(uiElementIndex));
        }

        for (UiElement<?, ?> child : uiElement.getChildren()) {
            serializeUiElement(child, isIndexed);
        }
        serializer.endTag(NAMESPACE, nodeName);
    }

    private InputStream toStream(boolean isIndexed) throws IOException {
        final long startTime = SystemClock.uptimeMillis();
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            serializer = Xml.newSerializer();
            shouldAddDisplayInfo = root == null;
            serializer.setOutput(outputStream, XML_ENCODING);
            serializer.startDocument(XML_ENCODING, true);
            serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
            final UiElement<?, ?> uiRootElement = root == null
                    ? UiElementSnapshot.take(getCachedWindowRoots(), NotificationListener.getInstance().getToastMessage(), includedAttributes)
                    : UiElementSnapshot.take(root, includedAttributes);
            serializeUiElement(uiRootElement, isIndexed);
            serializer.endDocument();
            Logger.debug(String.format("The source XML tree (%s bytes) has been fetched in %sms",
                    outputStream.size(), SystemClock.uptimeMillis() - startTime));
            return new ByteArrayInputStream(outputStream.toByteArray());
        }
    }

    public String dumpToXml() {
        try {
            RESOURCES_GUARD.acquire();
        } catch (InterruptedException e) {
            throw new UiAutomator2Exception(e);
        }
        try (InputStream xmlStream = toStream(false)) {
            return IOUtils.toString(xmlStream, XML_ENCODING);
        } catch (IOException e) {
            throw new UiAutomator2Exception(e);
        } finally {
            uiElementsMapping.clear();
            RESOURCES_GUARD.release();
        }
    }

    private static Document loadDocument(InputStream in) {
        DocumentBuilderFactory factory = new DocumentBuilderFactoryImpl();
        factory.setNamespaceAware(true);
        factory.setValidating(false);
        try {
            return factory.newDocumentBuilder().parse(in);
        } catch (SAXException | ParserConfigurationException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public NodeInfoList findNodes(String xpathSelector, boolean multiple) {
        StaticContextBuilder scb = new StaticContextBuilder();
        final XPath2Expression expr;
        try {
            expr = new Engine().parseExpression(xpathSelector, scb);
        } catch (XPathParserException e) {
            throw new InvalidSelectorException(e);
        }

        try {
            RESOURCES_GUARD.acquire();
        } catch (InterruptedException e) {
            throw new UiAutomator2Exception(e);
        }
        try (InputStream xmlStream = toStream(true)) {
            Document doc = loadDocument(xmlStream);
            ResultSequence rs = expr.evaluate(new DynamicContextBuilder(scb), new Object[] { doc });
            NodeInfoList matchedNodes = new NodeInfoList();
            Iterator<Item> iterator = rs.iterator();
            final long timeStarted = SystemClock.uptimeMillis();
            while (iterator.hasNext()) {
                Item item = iterator.next();
                if (!(item.getNativeValue() instanceof Node)) {
                    continue;
                }
                Node node = (Node) item.getNativeValue();
                if (node.getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }

                NamedNodeMap attributes = node.getAttributes();
                Node uiElementIndexAttr = attributes.getNamedItem(UI_ELEMENT_INDEX);
                if (uiElementIndexAttr == null) {
                    continue;
                }
                UiElement<?, ?> uiElement = uiElementsMapping.get(
                        Integer.parseInt(uiElementIndexAttr.getNodeValue()));
                if (uiElement == null || uiElement.getNode() == null) {
                    continue;
                }
                matchedNodes.add(uiElement.getNode());
                if (!multiple) {
                    break;
                }
            }
            Logger.info(String.format("Took %sms to retrieve %s matches for '%s' XPath query",
                    SystemClock.uptimeMillis() - timeStarted, matchedNodes.size(), xpathSelector));
            return matchedNodes;
        } catch (Exception e) {
            e.printStackTrace();
            throw new UiAutomator2Exception(e);
        } finally {
            uiElementsMapping.clear();
            RESOURCES_GUARD.release();
        }
    }
}
