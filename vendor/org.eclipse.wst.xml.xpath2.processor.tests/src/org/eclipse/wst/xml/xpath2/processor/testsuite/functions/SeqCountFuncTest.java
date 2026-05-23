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
      
      
public class SeqCountFuncTest extends AbstractPsychoPathTest {

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:int(lower bound).
   public void test_fn_countint1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countint1args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countint1args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:int(mid range).
   public void test_fn_countint1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countint1args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countint1args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:int(upper bound).
   public void test_fn_countint1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countint1args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countint1args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:integer(lower bound).
   public void test_fn_countintg1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countintg1args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countintg1args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:integer(mid range).
   public void test_fn_countintg1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countintg1args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countintg1args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:integer(upper bound).
   public void test_fn_countintg1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countintg1args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countintg1args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:decimal(lower bound).
   public void test_fn_countdec1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countdec1args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countdec1args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:decimal(mid range).
   public void test_fn_countdec1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countdec1args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countdec1args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:decimal(upper bound).
   public void test_fn_countdec1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countdec1args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countdec1args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:double(lower bound).
   public void test_fn_countdbl1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countdbl1args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countdbl1args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:double(mid range).
   public void test_fn_countdbl1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countdbl1args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countdbl1args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:double(upper bound).
   public void test_fn_countdbl1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countdbl1args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countdbl1args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:float(lower bound).
   public void test_fn_countflt1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countflt1args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countflt1args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:float(mid range).
   public void test_fn_countflt1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countflt1args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countflt1args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:float(upper bound).
   public void test_fn_countflt1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countflt1args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countflt1args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:long(lower bound).
   public void test_fn_countlng1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countlng1args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countlng1args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:long(mid range).
   public void test_fn_countlng1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countlng1args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countlng1args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:long(upper bound).
   public void test_fn_countlng1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countlng1args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countlng1args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:unsignedShort(lower bound).
   public void test_fn_countusht1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countusht1args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countusht1args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:unsignedShort(mid range).
   public void test_fn_countusht1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countusht1args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countusht1args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:unsignedShort(upper bound).
   public void test_fn_countusht1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countusht1args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countusht1args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:negativeInteger(lower bound).
   public void test_fn_countnint1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countnint1args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countnint1args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:negativeInteger(mid range).
   public void test_fn_countnint1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countnint1args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countnint1args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:negativeInteger(upper bound).
   public void test_fn_countnint1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countnint1args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countnint1args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:positiveInteger(lower bound).
   public void test_fn_countpint1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countpint1args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countpint1args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:positiveInteger(mid range).
   public void test_fn_countpint1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countpint1args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countpint1args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:positiveInteger(upper bound).
   public void test_fn_countpint1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countpint1args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countpint1args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:unsignedLong(lower bound).
   public void test_fn_countulng1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countulng1args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countulng1args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:unsignedLong(mid range).
   public void test_fn_countulng1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countulng1args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countulng1args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:unsignedLong(upper bound).
   public void test_fn_countulng1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countulng1args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countulng1args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:nonPositiveInteger(lower bound).
   public void test_fn_countnpi1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countnpi1args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countnpi1args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:nonPositiveInteger(mid range).
   public void test_fn_countnpi1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countnpi1args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countnpi1args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:nonPositiveInteger(upper bound).
   public void test_fn_countnpi1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countnpi1args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countnpi1args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:nonNegativeInteger(lower bound).
   public void test_fn_countnni1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countnni1args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countnni1args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:nonNegativeInteger(mid range).
   public void test_fn_countnni1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countnni1args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countnni1args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:nonNegativeInteger(upper bound).
   public void test_fn_countnni1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countnni1args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countnni1args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:short(lower bound).
   public void test_fn_countsht1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countsht1args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countsht1args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:short(mid range).
   public void test_fn_countsht1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countsht1args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countsht1args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
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

   //Evaluates the "count" function with the arguments set as follows: $arg = xs:short(upper bound).
   public void test_fn_countsht1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countsht1args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqCountFunc/fn-countsht1args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
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

}
      