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

import android.content.Context;
import android.graphics.Point;
import android.os.SystemClock;
import android.util.SparseArray;
import android.util.Xml;
import android.view.Display;
import android.view.accessibility.AccessibilityNodeInfo;

import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.JXPathException;
import org.apache.commons.jxpath.xml.DocumentContainer;
import org.apache.commons.lang.StringUtils;
import org.xmlpull.v1.XmlSerializer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.Semaphore;

import androidx.annotation.Nullable;
import io.appium.uiautomator2.common.exceptions.InvalidSelectorException;
import io.appium.uiautomator2.common.exceptions.UiAutomator2Exception;
import io.appium.uiautomator2.model.NotificationListener;
import io.appium.uiautomator2.model.UiElement;
import io.appium.uiautomator2.utils.Attribute;
import io.appium.uiautomator2.utils.Logger;
import io.appium.uiautomator2.utils.NodeInfoList;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
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
    private static final String XML_ENCODING = "UTF-8";
    private final Semaphore RESOURCES_GUARD = new Semaphore(1);
    private String tmpXmlName;

    @Nullable
    private final AccessibilityNodeInfo root;
    @Nullable
    private SparseArray<UiElement<?, ?>> uiElementsMapping = null;
    private boolean shouldAddDisplayInfo;
    private XmlSerializer serializer;

    public AccessibilityNodeInfoDumper() {
        this(null);
    }

    public AccessibilityNodeInfoDumper(@Nullable AccessibilityNodeInfo root) {
        this.root = root;
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

    private File toFile() throws IOException {
        tmpXmlName = String.format("%s.xml", UUID.randomUUID().toString());
        final long startTime = SystemClock.uptimeMillis();
        try (OutputStream outputStream = getApplicationContext().openFileOutput(tmpXmlName, Context.MODE_PRIVATE)) {
            serializer = Xml.newSerializer();
            shouldAddDisplayInfo = root == null;
            serializer.setOutput(outputStream, XML_ENCODING);
            serializer.startDocument(XML_ENCODING, true);
            serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
            final UiElement<?, ?> xpathRoot = root == null
                    ? rebuildForNewRoot(currentActiveWindowRoot(), NotificationListener.getInstance().getToastMessage())
                    : rebuildForNewRoot(root, null);
            serializeUiElement(xpathRoot, 0);
            serializer.endDocument();
        }
        File resultXml = getApplicationContext().getFileStreamPath(tmpXmlName);
        Logger.info(String.format("The source XML tree (%s bytes) has been fetched in %sms", resultXml.length(),
                SystemClock.uptimeMillis() - startTime));
        return resultXml;
    }

    private void performCleanup() {
        uiElementsMapping = null;
        if (tmpXmlName != null) {
            getApplicationContext().deleteFile(tmpXmlName);
            tmpXmlName = null;
        }
    }

    public String dumpToXml() {
        try {
            RESOURCES_GUARD.acquire();
        } catch (InterruptedException e) {
            throw new UiAutomator2Exception(e);
        }
        try {
            StringBuilder sb = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(toFile()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            }
            return sb.toString();
        } catch (IOException e) {
            throw new UiAutomator2Exception(e);
        } finally {
            performCleanup();
            RESOURCES_GUARD.release();
        }
    }

    public NodeInfoList findNodes(String xpathSelector, boolean multiple) {
        try {
             JXPathContext.compile(xpathSelector);
        } catch (JXPathException e) {
            throw new InvalidSelectorException(e);
        }

        try {
            RESOURCES_GUARD.acquire();
        } catch (InterruptedException e) {
            throw new UiAutomator2Exception(e);
        }
        try {
            uiElementsMapping = new SparseArray<>();
            final DocumentContainer documentContainer = new DocumentContainer(toFile().toURI().toURL());
            final JXPathContext ctx = JXPathContext.newContext(documentContainer);
            final Iterator matchedElementIndexes = ctx.iterate(String.format("(%s)/@%s", xpathSelector, UI_ELEMENT_INDEX));
            final NodeInfoList matchesList = new NodeInfoList();
            while (matchedElementIndexes.hasNext()) {
                final UiElement uiElement = uiElementsMapping.get(Integer.parseInt((String) matchedElementIndexes.next()));
                if (uiElement == null || uiElement.getNode() == null) {
                    continue;
                }

                matchesList.addToList(uiElement.getNode());
                if (!multiple) {
                    break;
                }
            }
            return matchesList;
        } catch (IOException e) {
            throw new UiAutomator2Exception(e);
        } finally {
            performCleanup();
            RESOURCES_GUARD.release();
        }
    }
}
