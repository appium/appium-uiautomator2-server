
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
      
      
public class StaticBaseURIFuncTest extends AbstractPsychoPathTest {

   //Evaluates the "static-base-uri" function with incorrect arity.
   public void test_fn_static_base_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/AccessorFunc/StaticBaseURIFunc/fn-static-base-uri-1.xq";
      String expectedResult = "XPST0017";
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

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "static-base-uri" function using "ftp" flavor for base-uri .
   public void test_fn_static_base_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/AccessorFunc/StaticBaseURIFunc/fn-static-base-uri-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/AccessorFunc/StaticBaseURIFunc/fn-static-base-uri-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      String uri = "ftp://ftp.is.co.za/rfc/somefile.txt";
      setBaseUri(uri);


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

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "static-base-uri" function using "mailto" flavor for base-uri.
   public void test_fn_static_base_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/AccessorFunc/StaticBaseURIFunc/fn-static-base-uri-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/AccessorFunc/StaticBaseURIFunc/fn-static-base-uri-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      String baseuri = "mailto:John.Doe@example.com"; 
      setBaseUri(baseuri);

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

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "static-base-uri" function using "news" flavor for base-uri .
   public void test_fn_static_base_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/AccessorFunc/StaticBaseURIFunc/fn-static-base-uri-4.xq";
      String resultFile = "/ExpectedTestResults/Functions/AccessorFunc/StaticBaseURIFunc/fn-static-base-uri-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      String baseuri = "news:comp.infosystems.www.servers.unix";
      setBaseUri(baseuri);

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

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "static-base-uri" function using "telnet" flavor for base-uri .
   public void test_fn_static_base_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/AccessorFunc/StaticBaseURIFunc/fn-static-base-uri-5.xq";
      String resultFile = "/ExpectedTestResults/Functions/AccessorFunc/StaticBaseURIFunc/fn-static-base-uri-5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      String baseuri = "telnet://192.0.2.16:80/";
      setBaseUri(baseuri);

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

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "static-base-uri" function using "tel" flavor for base-uri .
   public void test_fn_static_base_6() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/AccessorFunc/StaticBaseURIFunc/fn-static-base-uri-6.xq";
      String resultFile = "/ExpectedTestResults/Functions/AccessorFunc/StaticBaseURIFunc/fn-static-base-uri-6.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      String baseuri = "tel:+1-816-555-1212";
      setBaseUri(baseuri);

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

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "static-base-uri" function using "urn" flavor for base-uri .
   public void test_fn_static_base_7() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/AccessorFunc/StaticBaseURIFunc/fn-static-base-uri-7.xq";
      String resultFile = "/ExpectedTestResults/Functions/AccessorFunc/StaticBaseURIFunc/fn-static-base-uri-7.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      String baseuri = "urn:oasis:names:specification:docbook:dtd:xml:4.1.2";
      setBaseUri(baseuri);

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

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "static-base-uri" function used as argument to fn:upper-case.
   public void test_fn_static_base_8() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/AccessorFunc/StaticBaseURIFunc/fn-static-base-uri-8.xq";
      String resultFile = "/ExpectedTestResults/Functions/AccessorFunc/StaticBaseURIFunc/fn-static-base-uri-8.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      String baseuri = "http://www.example.com";
      setBaseUri(baseuri);

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

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "static-base-uri" function used as argument to fn:lower-case.
   public void test_fn_static_base_9() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/AccessorFunc/StaticBaseURIFunc/fn-static-base-uri-9.xq";
      String resultFile = "/ExpectedTestResults/Functions/AccessorFunc/StaticBaseURIFunc/fn-static-base-uri-9.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      setBaseUri("http://www.example.com");

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

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "static-base-uri" function used as argument to fn:concat.
   public void test_fn_static_base_10() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/AccessorFunc/StaticBaseURIFunc/fn-static-base-uri-10.xq";
      String resultFile = "/ExpectedTestResults/Functions/AccessorFunc/StaticBaseURIFunc/fn-static-base-uri-10.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      String baseuri = "http://www.example.com";
      setBaseUri(baseuri);

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

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "static-base-uri" function used as argument to fn:string-join.
   public void test_fn_static_base_11() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/AccessorFunc/StaticBaseURIFunc/fn-static-base-uri-11.xq";
      String resultFile = "/ExpectedTestResults/Functions/AccessorFunc/StaticBaseURIFunc/fn-static-base-uri-11.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      String baseuri = "http://www.example.com";
      setBaseUri(baseuri);

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

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "static-base-uri" function used as argument to fn:string-length.
   public void test_fn_static_base_12() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/AccessorFunc/StaticBaseURIFunc/fn-static-base-uri-12.xq";
      String resultFile = "/ExpectedTestResults/Functions/AccessorFunc/StaticBaseURIFunc/fn-static-base-uri-12.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      String baseuri = "http://www.example.com";
      setBaseUri(baseuri);

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

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "static-base-uri" function used as argument to fn:string-before.
   public void test_fn_static_base_13() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/AccessorFunc/StaticBaseURIFunc/fn-static-base-uri-13.xq";
      String resultFile = "/ExpectedTestResults/Functions/AccessorFunc/StaticBaseURIFunc/fn-static-base-uri-13.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      String baseuri = "http://www.example.com";
      setBaseUri(baseuri);

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

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "static-base-uri" function used as argument to fn:string-before.
   public void test_fn_static_base_14() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/AccessorFunc/StaticBaseURIFunc/fn-static-base-uri-14.xq";
      String resultFile = "/ExpectedTestResults/Functions/AccessorFunc/StaticBaseURIFunc/fn-static-base-uri-14.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      String baseuri = "http://www.example.com";
      setBaseUri(baseuri);

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

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

}
      