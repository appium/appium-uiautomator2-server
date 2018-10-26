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

import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.accessibility.AccessibilityNodeInfo;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import io.appium.uiautomator2.common.exceptions.InvalidSelectorException;
import io.appium.uiautomator2.core.AccessibilityNodeInfoDumper;
import io.appium.uiautomator2.utils.NodeInfoList;

import static android.support.test.internal.util.Checks.checkNotNull;

/**
 * Find matching UiElement by XPath.
 */
public class XPathFinder {
    private static final XPath XPATH_COMPILER = XPathFactory.newInstance().newXPath();
    private static final String UI_ELEMENT_INDEX = "uiElementIndex";
    private final String xPathString;

    public XPathFinder(String xPathString) {
        this.xPathString = checkNotNull(xPathString);
    }

    @Override
    public String toString() {
        return xPathString;
    }

    public NodeInfoList find(@Nullable AccessibilityNodeInfo context) {
        final SparseArray<UiElement<?, ?>> uiElementsMapping = new SparseArray<>();
        final Document document = AccessibilityNodeInfoDumper.asXmlDocument(context, uiElementsMapping);
        final NodeList nodes;
        final NodeInfoList matchesList = new NodeInfoList();
        try {
            nodes = (NodeList) XPATH_COMPILER
                    .compile(xPathString)
                    .evaluate(document, XPathConstants.NODESET);
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
            final UiElement uiElement = uiElementsMapping.get(
                    Integer.parseInt(uiElementIndexAttribute.getNodeValue()));
            if (uiElement == null || uiElement.getNode() == null) {
                continue;
            }

            matchesList.addToList(uiElement.getNode());
        }
        return matchesList;
    }
}
