/*******************************************************************************
 * Copyright (c) 2011, 2017 Jesper Steen Moller and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Jesper Steen Moller  - initial API and implementation
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.test.newapi;

import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import junit.framework.TestCase;

import org.eclipse.wst.xml.xpath2.api.DynamicContext;
import org.eclipse.wst.xml.xpath2.api.StaticContext;
import org.eclipse.wst.xml.xpath2.api.XPath2Expression;
import org.eclipse.wst.xml.xpath2.processor.Engine;
import org.eclipse.wst.xml.xpath2.processor.util.DynamicContextBuilder;
import org.eclipse.wst.xml.xpath2.processor.util.StaticContextBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class FilteringPerformanceTest extends TestCase {

	private Document document;

	public void setUp() throws Exception {
		document = buildBigDocument(6, -1, 5);
		super.setUp();
	}
	
	private Document buildBigDocument(int width, int deltaWidth, int depth)
			throws ParserConfigurationException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		Document newDoc = dbf.newDocumentBuilder().newDocument();
		Element root = newDoc.createElementNS("urn:x-my-ns", "root");
		newDoc.appendChild(root);
		int created = fillElement(root, width, deltaWidth, 0, depth, newDoc);
		root.setAttribute("nodeCount", "" + created);
		return newDoc;
	}

	private int fillElement(Element parent, int width, int deltaWidth,
			int currentDepth, int depthMax, Document owner) {
		int nodesCreated = 3;

		parent.appendChild(owner.createComment("Width : " + width
				+ ", deltaWidth: " + deltaWidth + " currentDepth: "
				+ currentDepth));
		parent.appendChild(owner.createTextNode("\r\n"));
		Element child = owner.createElementNS("urn:x-my-ns", "element"
				+ currentDepth);
		parent.appendChild(child);
		if (currentDepth < depthMax) {
			for (int i = 0; i < width; ++i) {
				child.setAttribute("childNumber", "" + (i + 1));
				nodesCreated += 1 + fillElement(child, width + deltaWidth,
						deltaWidth, currentDepth + 1, depthMax, owner);
			}
		} else {
			child.appendChild(owner.createTextNode("leaf"));
			nodesCreated++;
		}

		return nodesCreated;
	}

	public void tearDown() throws Exception {
		document = null;
		super.tearDown();
	}

	public void testCountAllOperation1() throws ParserConfigurationException, XPathExpressionException {
		Document bigDoc = buildBigDocument(2, 1, 6);
		System.out.println(bigDoc.getDocumentElement().getAttribute("nodeCount"));
		String control = evalXPath1("count(//node())", bigDoc);
		String evaluated = evalXPath2("count(//node())", bigDoc, BigDecimal.class).toString();
		assertEquals(control, evaluated);
	}
	
	public void testCountAllOperationWithFilter() throws ParserConfigurationException, XPathExpressionException {
		Document bigDoc = buildBigDocument(2, 1, 5);
		System.out.println(bigDoc.getDocumentElement().getAttribute("nodeCount"));
		String control = evalXPath1("count(//node()[count(ancestor-or-self::*)>4])", bigDoc);
		String evaluated = evalXPath2("count(//node()[count(ancestor-or-self::*)>4])", bigDoc, BigDecimal.class).toString();
		assertEquals(control, evaluated);
	}
	
	public void testCountAllOperationBig() throws ParserConfigurationException, XPathExpressionException {
		Document bigDoc = buildBigDocument(2, 1, 7);
		System.out.println(bigDoc.getDocumentElement().getAttribute("nodeCount"));
		String control = evalXPath1("count(//node())", bigDoc);
		String evaluated = evalXPath2("count(//node())", bigDoc, BigDecimal.class).toString();
		assertEquals(control, evaluated);
	}
	
	protected Object evalXPath2(String xpath, Node doc, Class resultClass) {
		StaticContext sc = new StaticContextBuilder();
		XPath2Expression path = new Engine().parseExpression(xpath, sc);
		DynamicContext dynamicContext = new DynamicContextBuilder(sc);
		long before = System.nanoTime();
//		path.evaluate(dynamicContext, doc != null ? new Object[] { doc } : new Object[0]);
//		path.evaluate(dynamicContext, doc != null ? new Object[] { doc } : new Object[0]);
		org.eclipse.wst.xml.xpath2.api.ResultSequence rs = path.evaluate(dynamicContext, doc != null ? new Object[] { doc } : new Object[0]);
		assertEquals("Expected single result from \'" + xpath + "\'", 1, rs.size());
		Object result = rs.value(0);
		long after = System.nanoTime();
		System.out.println("XPath2 " + xpath + " evaluated to " + result + " in " + (after-before)/1000 + " μs");
		assertTrue("Exected XPath result instanceof class " + resultClass.getSimpleName() + " from \'" + xpath + "\', got " + result.getClass(), resultClass.isInstance(result));
		return resultClass.cast(result);
	}

	protected String evalXPath1(String xpath, Node doc) throws XPathExpressionException {
		XPathExpression expression = XPathFactory.newInstance().newXPath().compile(xpath);
		long before = System.nanoTime();
//		expression.evaluate(doc);
//		expression.evaluate(doc);
		String result = expression.evaluate(doc);
		long after = System.nanoTime();
		System.out.println("XPath1 " + xpath + " evaluated to " + result + " in " + (after-before)/1000 + " μs");
		return result;
	}

	public static void elementToStream(Element element, Writer writer) {
		try {
			DOMSource source = new DOMSource(element);
			StreamResult result = new StreamResult(writer);
			TransformerFactory transFactory = TransformerFactory.newInstance();
			Transformer transformer = transFactory.newTransformer();
			transformer.transform(source, result);
		} catch (Exception ex) {
		}
	}

	public static String documentToString(Document doc) {
		StringWriter sw = new StringWriter();
		elementToStream(doc.getDocumentElement(), sw);
		return sw.toString();
	}

}
