/*******************************************************************************
 * Copyright (c) 2009, 2017 Standards for Technology in Automotive Retail and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     David Carver (STAR) - bug 288886 - add unit tests and fix fn:resolve-qname function
 *******************************************************************************/
package org.eclipse.wst.xml.xpath2.processor.testsuite.functions;

import java.net.URL;

import org.apache.xerces.xs.XSModel;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.StaticError;
import org.eclipse.wst.xml.xpath2.processor.XPathParserException;
import org.eclipse.wst.xml.xpath2.processor.test.AbstractPsychoPathTest;

public class ResolveQNameFuncTest extends AbstractPsychoPathTest {

	public void test_fn_resolve_qname_1() throws Exception {
		String inputFile = "/TestSources/emptydoc.xml";
		String xqFile = "/Queries/XQuery/Functions/URIFunc/ResolveURIFunc/fn-resolve-uri-1.xq";
		String expectedResult = "FOCA0002";
		URL fileURL = bundle.getEntry(inputFile);
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		String xpath = "fn:resolve-QName(\"aName::\", /doc)";

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
	
	public void test_fn_resolve_qname_2() throws Exception {
		String inputFile = "/TestSources/emptydoc.xml";
		String xqFile = "/Queries/XQuery/Functions/URIFunc/ResolveURIFunc/fn-resolve-uri-1.xq";
		String expectedResult = "FONS0004";
		URL fileURL = bundle.getEntry(inputFile);
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		String xpath = "fn:resolve-QName(\"p1:doc\", /doc)";

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
	
	public void test_fn_resolve_qname_3() throws Exception {
		String inputFile = "/TestSources/emptydoc.xml";
		String xqFile = "/Queries/XQuery/Functions/URIFunc/ResolveURIFunc/fn-resolve-uri-1.xq";
		String expectedResult = "";
		URL fileURL = bundle.getEntry(inputFile);
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		String xpath = "fn:resolve-QName((), /doc)";

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
	
	public void test_fn_resolve_qname_4() throws Exception {
		String inputFile = "/TestSources/MixNS.xml";
		String xqFile = "/Queries/XQuery/Functions/URIFunc/ResolveURIFunc/fn-resolve-uri-1.xq";
		String expectedResult = "c";
		URL fileURL = bundle.getEntry(inputFile);
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);
		addNamespace("cm", "http://www.example.com");
		addNamespace("ed", "http://www.example.edu");

		String xpath = "fn:string(fn:local-name-from-QName(fn:resolve-QName(\"cm:c\", /a/b/cm:c)))";

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
	
	public void test_fn_resolve_qname_5() throws Exception {
		String inputFile = "/TestSources/MixNS.xml";
		String xqFile = "/Queries/XQuery/Functions/URIFunc/ResolveURIFunc/fn-resolve-uri-1.xq";
		String expectedResult = "http://www.example.com";
		URL fileURL = bundle.getEntry(inputFile);
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);
		addNamespace("cm", "http://www.example.com");
		addNamespace("ed", "http://www.example.edu");

		String xpath = "fn:string(fn:namespace-uri-from-QName(fn:resolve-QName(\"cm:c\", /a/b/cm:c)))";

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
	
	public void test_fn_resolve_qname_6() throws Exception {
		String inputFile = "/TestSources/MixNS.xml";
		String xqFile = "/Queries/XQuery/Functions/URIFunc/ResolveURIFunc/fn-resolve-uri-1.xq";
		String expectedResult = "c";
		URL fileURL = bundle.getEntry(inputFile);
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);
		addNamespace("cm", "http://www.example.com");
		addNamespace("ed", "http://www.example.edu");

		String xpath = "fn:string(fn:local-name-from-QName(fn:resolve-QName(\"ed:c\", /a/b/ed:c)))";

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
	
	public void test_fn_resolve_qname_8() throws Exception {
		String inputFile = "/TestSources/MixNS.xml";
		String xqFile = "/Queries/XQuery/Functions/URIFunc/ResolveURIFunc/fn-resolve-uri-1.xq";
		String expectedResult = "c";
		URL fileURL = bundle.getEntry(inputFile);
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);
		addNamespace("cm", "http://www.example.com");
		addNamespace("ed", "http://www.example.edu");

		String xpath = "fn:string(fn:local-name-from-QName(fn:resolve-QName(\"c\", /a/b/cm:c)))";

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
	
	public void test_fn_resolve_qname_9() throws Exception {
		String inputFile = "/TestSources/examples.xml";
		String xqFile = "/Queries/XQuery/Functions/URIFunc/ResolveURIFunc/fn-resolve-uri-1.xq";
		String expectedResult = "http://www.w3.org/XQueryTest/someExamples";
		URL fileURL = bundle.getEntry(inputFile);
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);
		addNamespace(null, "http://www.w3.org/XQueryTest/someExamples");

		String xpath = "fn:string(fn:namespace-uri-from-QName(fn:resolve-QName(\"E6-Root\", /E6-Root)))";

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
	
	public void test_K_ResolveQnameConstructFunc_1() throws Exception {
		String inputFile = "/TestSources/emptydoc.xml";
		String xqFile = "/Queries/XQuery/Functions/URIFunc/ResolveURIFunc/fn-resolve-uri-1.xq";
		String expectedResult = "XPST0017";
		URL fileURL = bundle.getEntry(inputFile);
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		String xpath = "fn:resolve-QName()";

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
	
	public void test_K_ResolveQnameConstructFunc_2() throws Exception {
		String inputFile = "/TestSources/emptydoc.xml";
		String xqFile = "/Queries/XQuery/Functions/URIFunc/ResolveURIFunc/fn-resolve-uri-1.xq";
		String expectedResult = "XPST0017";
		URL fileURL = bundle.getEntry(inputFile);
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		String xpath = "fn:resolve-QName(\"wrongparam\")";

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
	
	public void test_K_ResolveQnameConstructFunc_3() throws Exception {
		String inputFile = "/TestSources/emptydoc.xml";
		String xqFile = "/Queries/XQuery/Functions/URIFunc/ResolveURIFunc/fn-resolve-uri-1.xq";
		String expectedResult = "XPST0017";
		URL fileURL = bundle.getEntry(inputFile);
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		String xpath = "fn:resolve-QName(\"wrongparam\", \"takes a node\", \"wrong\")";

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
	
	public void test_K_ResolveQnameConstructFunc_4() throws Exception {
		String inputFile = "/TestSources/emptydoc.xml";
		String xqFile = "Queries/XQuery/ResolveQNameConstructFunc/K-ResolveQNameConstructFunc-4.xq";
		String expectedResult = "true";
		URL fileURL = bundle.getEntry(inputFile);
		loadDOMDocument(fileURL);

		// Get XML Schema Information for the Document
		XSModel schema = getGrammar();

		setupDynamicContext(schema);

		String xpath = "fn:empty(fn:resolve-QName((), \"a string\"))";

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
}