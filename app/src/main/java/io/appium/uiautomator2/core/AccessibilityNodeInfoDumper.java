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
import org.jdom2.Document;
import org.jdom2.filter.Filters;
import org.jdom2.input.JDOMParseException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.concurrent.Semaphore;

import io.appium.uiautomator2.common.exceptions.InvalidSelectorException;
import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.model.NotificationListener;
import io.appium.uiautomator2.model.UiElementSnapshot;
import io.appium.uiautomator2.model.UiElement;
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

public class AccessibilityNodeInfoDumper {
    // https://github.com/appium/appium/issues/10204
    private static final int MAX_DEPTH = 70;
    private static final String UI_ELEMENT_INDEX = "uiElementIndex";
    private static final String NON_XML_CHAR_REPLACEMENT = "?";
    private static final String NAMESPACE = "";
    private static final String DEFAULT_VIEW_CLASS_NAME = View.class.getName();
    private static final String XML_ENCODING = "UTF-8";
    private static final XPathFactory XPATH = XPathFactory.instance();
    private static final SAXBuilder SAX_BUILDER = new SAXBuilder();
    private final Semaphore RESOURCES_GUARD = new Semaphore(1);

    @Nullable
    private final AccessibilityNodeInfo root;
    @Nullable
    private SparseArray<UiElement<?, ?>> uiElementsMapping = null;
    @Nullable
    private final Set<Attribute> includedAttributes;
    private boolean shouldAddDisplayInfo;
    private XmlSerializer serializer;

    public AccessibilityNodeInfoDumper() {
        this(null, null);
    }

    public AccessibilityNodeInfoDumper(@Nullable AccessibilityNodeInfo root,
                                       @Nullable Set<Attribute> includedAttributes) {
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

        if (((NormalizeTagNames) Settings.NORMALIZE_TAG_NAMES.getSetting()).getValue()) {
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

    private void serializeUiElement(UiElement<?, ?> uiElement, final int depth) throws IOException {
        final String className = uiElement.getClassName();
        final String nodeName = toXmlNodeName(className);
        serializer.startTag(NAMESPACE, nodeName);

        for (Attribute attr : uiElement.attributeKeys()) {
            if (!attr.isExposableToXml()
                    || includedAttributes != null && !includedAttributes.contains(attr)) {
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

    private InputStream toStream() throws IOException {
        final long startTime = SystemClock.uptimeMillis();
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            serializer = Xml.newSerializer();
            shouldAddDisplayInfo = root == null;
            serializer.setOutput(outputStream, XML_ENCODING);
            serializer.startDocument(XML_ENCODING, true);
            serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
            final UiElement<?, ?> xpathRoot = root == null
                    ? UiElementSnapshot.take(getCachedWindowRoots(), NotificationListener.getInstance().getToastMessage())
                    : UiElementSnapshot.take(root);
            serializeUiElement(xpathRoot, 0);
            serializer.endDocument();
            Logger.debug(String.format("The source XML tree (%s bytes) has been fetched in %sms",
                    outputStream.size(), SystemClock.uptimeMillis() - startTime));
            return new ByteArrayInputStream(outputStream.toByteArray());
        }
    }

    private void performCleanup() {
        uiElementsMapping = null;
    }

    public String dumpToXml() {
        try {
            RESOURCES_GUARD.acquire();
        } catch (InterruptedException e) {
            throw new UiAutomator2Exception(e);
        }
        try (InputStream xmlStream = toStream()) {
            return IOUtils.toString(xmlStream, XML_ENCODING);
        } catch (IOException e) {
            throw new UiAutomator2Exception(e);
        } finally {
            performCleanup();
            RESOURCES_GUARD.release();
        }
    }

    public NodeInfoList findNodes(String xpathSelector, boolean multiple) {
        try {
            XPATH.compile(xpathSelector, Filters.element());
        } catch (IllegalArgumentException e) {
            throw new InvalidSelectorException(e);
        }

        try {
            RESOURCES_GUARD.acquire();
        } catch (InterruptedException e) {
            throw new UiAutomator2Exception(e);
        }
        uiElementsMapping = new SparseArray<>();
        try (InputStream xmlStream = toStream()) {
            final Document document = SAX_BUILDER.build(xmlStream);
            final XPathExpression<org.jdom2.Attribute> expr = XPATH
                    .compile(String.format("(%s)/@%s", xpathSelector, UI_ELEMENT_INDEX), Filters.attribute());
            final NodeInfoList matchedNodes = new NodeInfoList();
            final long timeStarted = SystemClock.uptimeMillis();
            for (org.jdom2.Attribute uiElementId : expr.evaluate(document)) {
                UiElement<?, ?> uiElement = uiElementsMapping.get(uiElementId.getIntValue());
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
        } catch (JDOMParseException e) {
            throw new UiAutomator2Exception(String.format("%s. " +
                            "Try changing the '%s' driver setting to 'true' in order to workaround the problem.",
                    e.getMessage(), Settings.NORMALIZE_TAG_NAMES.toString()), e);
        } catch (Exception e) {
            throw new UiAutomator2Exception(e);
        } finally {
            performCleanup();
            RESOURCES_GUARD.release();
        }
    }
}
