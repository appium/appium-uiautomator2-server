/*******************************************************************************
 *Copyright (c) 2009, 2017 Standards for Technology in Automotive Retail and others.
 *All rights reserved. This program and the accompanying materials
 *are made available under the terms of the Eclipse Public License 2.0
 *which accompanies this distribution, and is available at
 https://www.eclipse.org/legal/epl-2.0/
 *
 *SPDX-License-Identifier: EPL-2.0
 *
 *Contributors:
 *    David Carver (STAR) - initial API and implementation
 *    Mukul Gandhi - bug 273760 - wrong namespace for functions and data types
 *    Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/
package org.eclipse.wst.xml.xpath2.processor.test;

import java.net.URL;

import org.apache.xerces.xs.XSModel;
import org.eclipse.wst.xml.xpath2.processor.DefaultDynamicContext;
import org.eclipse.wst.xml.xpath2.processor.DynamicContext;
import org.eclipse.wst.xml.xpath2.processor.JFlexCupParser;
import org.eclipse.wst.xml.xpath2.processor.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.XPathParser;
import org.eclipse.wst.xml.xpath2.processor.XPathParserException;
import org.eclipse.wst.xml.xpath2.processor.function.FnFunctionLibrary;
import org.eclipse.wst.xml.xpath2.processor.function.XSCtrLibrary;
import org.eclipse.wst.xml.xpath2.processor.internal.types.ElementType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XPathDecimalFormat;

public class TestXPath20 extends AbstractPsychoPathTest {

	protected void setUp() throws Exception {
		super.setUp();
		URL fileURL = bundle.getEntry("/TestSources/acme_corp.xml");
		loadDOMDocument(fileURL);
	}

	public void testLoadXML() throws Exception {
		assertNotNull(domDoc);
	}

	public void testSetupNullContenxt() throws Exception {
		DynamicContext dc = new DefaultDynamicContext(null, null);
		dc.add_namespace("xsd", "http://www.w3.org/2001/XMLSchema");
	}

	public void testAddLibraries() throws Exception {
		DynamicContext dc = new DefaultDynamicContext(null, domDoc);
		dc.add_namespace("xsd", "http://www.w3.org/2001/XMLSchema");

		dc.add_function_library(new FnFunctionLibrary());
		dc.add_function_library(new XSCtrLibrary());
	}

	public void testParseInvalidXPathExpression() throws Exception {
		try {
			XPathParser xpp = new JFlexCupParser();
			String xpath = "for  in /order/item return $x/price * $x/quantity";
			xpp.parse(xpath);
			fail("XPath parsing suceeded when it should have failed.");
		} catch (XPathParserException ex) {

		}
	}

	public void testParseValidXPathExpression() throws Exception {
		XPathParser xpp = new JFlexCupParser();
		String xpath = "some $x in /students/student/name satisfies $x = \"Fred\"";
		xpp.parse(xpath);
	}

	public void testProcessSimpleXpath() throws Exception {
		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		String xpath = "/employees/employee[1]/location";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		ElementType result = (ElementType) rs.first();
		String resultValue = result.node_value().getTextContent();

		assertEquals("Unexpected value returned", "Boston", resultValue);
	}

	public void testProcessSimpleXpathVariable() throws Exception {
		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		String xpath = "$input-context/employees/employee[1]/location";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		ElementType result = (ElementType) rs.first();
		String resultValue = result.node_value().getTextContent();

		assertEquals("Unexpected value returned", "Boston", resultValue);
	}
	
	public void testFloatFormat() throws Exception {
		Float value = new Float(1.0f);
		XPathDecimalFormat format = new XPathDecimalFormat("0.#######E0");
		String result = format.xpathFormat(value); 
		assertEquals("1", result);
	}
}
