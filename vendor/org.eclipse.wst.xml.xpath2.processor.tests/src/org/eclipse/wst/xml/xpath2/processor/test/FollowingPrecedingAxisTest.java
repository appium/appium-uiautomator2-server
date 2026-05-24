/*******************************************************************************
 * Copyright (c) 2025 Appium Contributors.
 * SPDX-License-Identifier: Apache-2.0
 *******************************************************************************/
package org.eclipse.wst.xml.xpath2.processor.test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

import junit.framework.TestCase;

import org.eclipse.wst.xml.xpath2.api.Item;
import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.api.XPath2Expression;
import org.eclipse.wst.xml.xpath2.processor.DOMLoader;
import org.eclipse.wst.xml.xpath2.processor.Engine;
import org.eclipse.wst.xml.xpath2.processor.XercesLoader;
import org.eclipse.wst.xml.xpath2.processor.internal.FollowingAxis;
import org.eclipse.wst.xml.xpath2.processor.internal.PrecedingAxis;
import org.eclipse.wst.xml.xpath2.processor.internal.types.NodeType;
import org.eclipse.wst.xml.xpath2.processor.util.DynamicContextBuilder;
import org.eclipse.wst.xml.xpath2.processor.util.StaticContextBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FollowingPrecedingAxisTest extends TestCase {

    private static final String XML =
            "<?xml version='1.0' encoding='UTF-8'?>\n"
                    + "<root>\n"
                    + "  <section>\n"
                    + "    <android.widget.TextView text=\"anchor\"/>\n"
                    + "    <android.widget.LinearLayout>\n"
                    + "      <nested/>\n"
                    + "    </android.widget.LinearLayout>\n"
                    + "    <android.widget.ImageButton id=\"target\"/>\n"
                    + "  </section>\n"
                    + "  <other>\n"
                    + "    <android.widget.ImageButton id=\"later\"/>\n"
                    + "  </other>\n"
                    + "</root>";

    private Document loadDocument(String xml) throws Exception {
        try (InputStream in = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8))) {
            DOMLoader loader = new XercesLoader();
            loader.set_validating(false);
            return loader.load(in);
        }
    }

    private int countElementNodes(String xpath, Document doc) throws Exception {
        StaticContextBuilder scb = new StaticContextBuilder();
        XPath2Expression expr = new Engine().parseExpression(xpath, scb);
        ResultSequence rs = expr.evaluate(new DynamicContextBuilder(scb), new Object[] { doc });
        int count = 0;
        Iterator<Item> iterator = rs.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            Object nativeValue = item.getNativeValue();
            if (nativeValue instanceof Node
                    && ((Node) nativeValue).getNodeType() == Node.ELEMENT_NODE) {
                count++;
            }
        }
        return count;
    }

    private Node findFirstElementByLocalName(Document doc, String localName) {
        NodeList nodes = doc.getElementsByTagName(localName);
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                return node;
            }
        }
        return null;
    }

    public void testFollowingAxisIterateAddsNodeTypes() throws Exception {
        Document doc = loadDocument(XML);
        Node anchor = findFirstElementByLocalName(doc, "android.widget.TextView");
        assertNotNull(anchor);

        NodeType anchorType = NodeType.dom_to_xpath(anchor, null);
        ResultBuffer result = new ResultBuffer();
        new FollowingAxis().iterate(anchorType, result, null);

        assertTrue("following axis should return at least one node", result.size() > 0);
        for (int i = 0; i < result.size(); i++) {
            assertTrue(
                    "following axis must add NodeType items, not iterators",
                    result.item(i) instanceof NodeType);
        }
    }

    public void testPrecedingAxisIterateAddsNodeTypes() throws Exception {
        Document doc = loadDocument(XML);
        Node marker = findFirstElementByLocalName(doc, "android.widget.ImageButton");
        assertNotNull(marker);

        NodeType markerType = NodeType.dom_to_xpath(marker, null);
        ResultBuffer result = new ResultBuffer();
        new PrecedingAxis().iterate(markerType, result, null);

        assertTrue("preceding axis should return at least one node", result.size() > 0);
        for (int i = 0; i < result.size(); i++) {
            assertTrue(
                    "preceding axis must add NodeType items, not iterators",
                    result.item(i) instanceof NodeType);
        }
    }

    public void testFollowingAxisStepSelectsFirstMatch() throws Exception {
        Document doc = loadDocument(XML);
        String xpath =
                "(//android.widget.TextView[@text='anchor']/following::android.widget.ImageButton)[1]";
        assertEquals(1, countElementNodes(xpath, doc));
    }

    public void testPrecedingAxisStepSelectsNodes() throws Exception {
        Document doc = loadDocument(XML);
        String xpath =
                "//android.widget.ImageButton[@id='later']/preceding::android.widget.TextView[@text='anchor']";
        assertEquals(1, countElementNodes(xpath, doc));
    }
}
