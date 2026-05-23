/*******************************************************************************
 * Copyright (c) 2009, 2017 Standards for Technology in Automotive Retail and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     David Carver - STAR - initial api and implementation bug 262765 
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.testsuite.functions;

import java.net.URL;

import org.apache.xerces.xs.XSModel;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.StaticError;
import org.eclipse.wst.xml.xpath2.processor.XPathParserException;
import org.eclipse.wst.xml.xpath2.processor.test.AbstractPsychoPathTest;

public class InScopePrefixesFuncTest extends AbstractPsychoPathTest {

	// Evaluation of "in-scope-prefixes" function with incorrect argument type.
	public void test_fn_in_scope_prefixes_2() throws Exception {
		String inputFile = "/TestSources/emptydoc.xml";
		String xqFile = "/Queries/XQuery/Functions/NodeFunc/InScopePrefixesFunc/fn-in-scope-prefixes-2.xq";
		String expectedResult = "XPTY0004";
		URL fileURL = bundle.getEntry(inputFile);
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		String xpath = extractXPathExpression(xqFile, inputFile);
		String actual = null;
		try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


			actual = buildResultString(rs);

		} catch (XPathParserException ex) {
			actual = ex.code();
		} catch (StaticError ex) {
			actual = ex.code();
		} catch (DynamicError ex) {
			actual = ex.code();
		}

		assertEquals("XPath Result Error " + xqFile + ":", expectedResult,
				actual);
	}

	public void testInScopePrefixesRoot() throws Exception {
		// Test for the fix, for xpathDefaultNamespace
		String inputFile = "/TestSources/fsx_NS.xml";
		URL fileURL = bundle.getEntry(inputFile);
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		// set up XPath default namespace in Dynamic Context
		setupDynamicContext(schema);
		addNamespace("fsx", "http://www.example.com/filesystem");

		String xpath = "fn:in-scope-prefixes(./fsx:MyComputer)";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

		
		String actual = buildResultString(rs);
		
		assertEquals("XPath Result Error: ", "fs", actual);

	}

	public void testInScopePrefixesChildNode() throws Exception {
		// Test for the fix, for xpathDefaultNamespace
		String inputFile = "/TestSources/fsx_NS.xml";
		URL fileURL = bundle.getEntry(inputFile);
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		// set up XPath default namespace in Dynamic Context
		setupDynamicContext(schema);
		addNamespace("fsx", "http://www.example.com/filesystem");

		String xpath = "fn:in-scope-prefixes(./fsx:MyComputer/fsx:Drive[1])";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

		
		String actual = buildResultString(rs);
		
		assertEquals("XPath Result Error: ", "fs", actual);

	}
}
