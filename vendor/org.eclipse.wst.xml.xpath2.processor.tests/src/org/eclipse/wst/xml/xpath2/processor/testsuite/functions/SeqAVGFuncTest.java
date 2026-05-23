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
      
      
public class SeqAVGFuncTest extends AbstractPsychoPathTest {

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:int(lower bound).
   public void test_fn_avgint1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgint1args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgint1args-1.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:int(mid range).
   public void test_fn_avgint1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgint1args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgint1args-2.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:int(upper bound).
   public void test_fn_avgint1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgint1args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgint1args-3.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:integer(lower bound).
   public void test_fn_avgintg1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgintg1args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgintg1args-1.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:integer(mid range).
   public void test_fn_avgintg1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgintg1args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgintg1args-2.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:integer(upper bound).
   public void test_fn_avgintg1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgintg1args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgintg1args-3.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:decimal(lower bound).
   public void test_fn_avgdec1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgdec1args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgdec1args-1.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:decimal(mid range).
   public void test_fn_avgdec1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgdec1args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgdec1args-2.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:decimal(upper bound).
   public void test_fn_avgdec1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgdec1args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgdec1args-3.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:double(lower bound).
   public void test_fn_avgdbl1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgdbl1args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgdbl1args-1.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:double(mid range).
   public void test_fn_avgdbl1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgdbl1args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgdbl1args-2.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:double(upper bound).
   public void test_fn_avgdbl1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgdbl1args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgdbl1args-3.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:float(lower bound).
   public void test_fn_avgflt1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgflt1args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgflt1args-1.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:float(mid range).
   public void test_fn_avgflt1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgflt1args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgflt1args-2.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:float(upper bound).
   public void test_fn_avgflt1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgflt1args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgflt1args-3.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:long(lower bound).
   public void test_fn_avglng1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avglng1args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avglng1args-1.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:long(mid range).
   public void test_fn_avglng1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avglng1args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avglng1args-2.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:long(upper bound).
   public void test_fn_avglng1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avglng1args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avglng1args-3.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:unsignedShort(lower bound).
   public void test_fn_avgusht1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgusht1args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgusht1args-1.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:unsignedShort(mid range).
   public void test_fn_avgusht1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgusht1args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgusht1args-2.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:unsignedShort(upper bound).
   public void test_fn_avgusht1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgusht1args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgusht1args-3.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:negativeInteger(lower bound).
   public void test_fn_avgnint1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnint1args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnint1args-1.txt";
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
    	 ex.printStackTrace();
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:negativeInteger(mid range).
   public void test_fn_avgnint1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnint1args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnint1args-2.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:negativeInteger(upper bound).
   public void test_fn_avgnint1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnint1args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnint1args-3.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:positiveInteger(lower bound).
   public void test_fn_avgpint1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgpint1args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgpint1args-1.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:positiveInteger(mid range).
   public void test_fn_avgpint1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgpint1args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgpint1args-2.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:positiveInteger(upper bound).
   public void test_fn_avgpint1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgpint1args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgpint1args-3.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:unsignedLong(lower bound).
   public void test_fn_avgulng1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgulng1args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgulng1args-1.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:unsignedLong(mid range).
   public void test_fn_avgulng1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgulng1args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgulng1args-2.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:unsignedLong(upper bound).
   public void test_fn_avgulng1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgulng1args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgulng1args-3.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:nonPositiveInteger(lower bound).
   public void test_fn_avgnpi1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnpi1args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnpi1args-1.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:nonPositiveInteger(mid range).
   public void test_fn_avgnpi1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnpi1args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnpi1args-2.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:nonPositiveInteger(upper bound).
   public void test_fn_avgnpi1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnpi1args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnpi1args-3.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:nonNegativeInteger(lower bound).
   public void test_fn_avgnni1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnni1args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnni1args-1.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:nonNegativeInteger(mid range).
   public void test_fn_avgnni1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnni1args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnni1args-2.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:nonNegativeInteger(upper bound).
   public void test_fn_avgnni1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnni1args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnni1args-3.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:short(lower bound).
   public void test_fn_avgsht1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgsht1args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgsht1args-1.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:short(mid range).
   public void test_fn_avgsht1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgsht1args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgsht1args-2.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg = xs:short(upper bound).
   public void test_fn_avgsht1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgsht1args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgsht1args-3.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:int(lower bound) $arg2 = xs:int(lower bound).
   public void test_fn_avgint2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgint2args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgint2args-1.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:int(mid range) $arg2 = xs:int(lower bound).
   public void test_fn_avgint2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgint2args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgint2args-2.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:int(upper bound) $arg2 = xs:int(lower bound).
   public void test_fn_avgint2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgint2args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgint2args-3.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:int(lower bound) $arg2 = xs:int(mid range).
   public void test_fn_avgint2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgint2args-4.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgint2args-4.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:int(lower bound) $arg2 = xs:int(upper bound).
   public void test_fn_avgint2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgint2args-5.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgint2args-5.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:integer(lower bound) $arg2 = xs:integer(lower bound).
   public void test_fn_avgintg2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgintg2args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgintg2args-1.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:integer(mid range) $arg2 = xs:integer(lower bound).
   public void test_fn_avgintg2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgintg2args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgintg2args-2.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:integer(upper bound) $arg2 = xs:integer(lower bound).
   public void test_fn_avgintg2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgintg2args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgintg2args-3.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:integer(lower bound) $arg2 = xs:integer(mid range).
   public void test_fn_avgintg2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgintg2args-4.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgintg2args-4.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:integer(lower bound) $arg2 = xs:integer(upper bound).
   public void test_fn_avgintg2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgintg2args-5.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgintg2args-5.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:decimal(lower bound) $arg2 = xs:decimal(lower bound).
   public void test_fn_avgdec2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgdec2args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgdec2args-1.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:decimal(mid range) $arg2 = xs:decimal(lower bound).
   public void test_fn_avgdec2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgdec2args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgdec2args-2.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:decimal(upper bound) $arg2 = xs:decimal(lower bound).
   public void test_fn_avgdec2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgdec2args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgdec2args-3.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:decimal(lower bound) $arg2 = xs:decimal(mid range).
   public void test_fn_avgdec2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgdec2args-4.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgdec2args-4.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:decimal(lower bound) $arg2 = xs:decimal(upper bound).
   public void test_fn_avgdec2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgdec2args-5.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgdec2args-5.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:double(lower bound) $arg2 = xs:double(lower bound).
   public void test_fn_avgdbl2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgdbl2args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/truevalue.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:double(mid range) $arg2 = xs:double(lower bound).
   public void test_fn_avgdbl2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgdbl2args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/truevalue.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:double(upper bound) $arg2 = xs:double(lower bound).
   public void test_fn_avgdbl2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgdbl2args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgdbl2args-3.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:double(lower bound) $arg2 = xs:double(mid range).
   public void test_fn_avgdbl2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgdbl2args-4.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/truevalue.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:double(lower bound) $arg2 = xs:double(upper bound).
   public void test_fn_avgdbl2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgdbl2args-5.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgdbl2args-5.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:float(lower bound) $arg2 = xs:float(lower bound).
   public void test_fn_avgflt2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgflt2args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgflt2args-1.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:float(mid range) $arg2 = xs:float(lower bound).
   public void test_fn_avgflt2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgflt2args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgflt2args-2.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:float(upper bound) $arg2 = xs:float(lower bound).
   public void test_fn_avgflt2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgflt2args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgflt2args-3.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:float(lower bound) $arg2 = xs:float(mid range).
   public void test_fn_avgflt2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgflt2args-4.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgflt2args-4.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:float(lower bound) $arg2 = xs:float(upper bound).
   public void test_fn_avgflt2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgflt2args-5.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgflt2args-5.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:long(lower bound) $arg2 = xs:long(lower bound).
   public void test_fn_avglng2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avglng2args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avglng2args-1.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:long(mid range) $arg2 = xs:long(lower bound).
   public void test_fn_avglng2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avglng2args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avglng2args-2.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:long(upper bound) $arg2 = xs:long(lower bound).
   public void test_fn_avglng2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avglng2args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avglng2args-3.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:long(lower bound) $arg2 = xs:long(mid range).
   public void test_fn_avglng2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avglng2args-4.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avglng2args-4.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:long(lower bound) $arg2 = xs:long(upper bound).
   public void test_fn_avglng2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avglng2args-5.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avglng2args-5.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:unsignedShort(lower bound) $arg2 = xs:unsignedShort(lower bound).
   public void test_fn_avgusht2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgusht2args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgusht2args-1.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:unsignedShort(mid range) $arg2 = xs:unsignedShort(lower bound).
   public void test_fn_avgusht2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgusht2args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgusht2args-2.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:unsignedShort(upper bound) $arg2 = xs:unsignedShort(lower bound).
   public void test_fn_avgusht2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgusht2args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgusht2args-3.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:unsignedShort(lower bound) $arg2 = xs:unsignedShort(mid range).
   public void test_fn_avgusht2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgusht2args-4.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgusht2args-4.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:unsignedShort(lower bound) $arg2 = xs:unsignedShort(upper bound).
   public void test_fn_avgusht2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgusht2args-5.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgusht2args-5.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:negativeInteger(lower bound) $arg2 = xs:negativeInteger(lower bound).
   public void test_fn_avgnint2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnint2args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnint2args-1.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:negativeInteger(mid range) $arg2 = xs:negativeInteger(lower bound).
   public void test_fn_avgnint2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnint2args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnint2args-2.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:negativeInteger(upper bound) $arg2 = xs:negativeInteger(lower bound).
   public void test_fn_avgnint2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnint2args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnint2args-3.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:negativeInteger(lower bound) $arg2 = xs:negativeInteger(mid range).
   public void test_fn_avgnint2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnint2args-4.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnint2args-4.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:negativeInteger(lower bound) $arg2 = xs:negativeInteger(upper bound).
   public void test_fn_avgnint2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnint2args-5.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnint2args-5.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:positiveInteger(lower bound) $arg2 = xs:positiveInteger(lower bound).
   public void test_fn_avgpint2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgpint2args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgpint2args-1.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:positiveInteger(mid range) $arg2 = xs:positiveInteger(lower bound).
   public void test_fn_avgpint2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgpint2args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgpint2args-2.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:positiveInteger(upper bound) $arg2 = xs:positiveInteger(lower bound).
   public void test_fn_avgpint2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgpint2args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgpint2args-3.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:positiveInteger(lower bound) $arg2 = xs:positiveInteger(mid range).
   public void test_fn_avgpint2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgpint2args-4.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgpint2args-4.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:positiveInteger(lower bound) $arg2 = xs:positiveInteger(upper bound).
   public void test_fn_avgpint2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgpint2args-5.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgpint2args-5.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:unsignedLong(lower bound) $arg2 = xs:unsignedLong(lower bound).
   public void test_fn_avgulng2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgulng2args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgulng2args-1.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:unsignedLong(mid range) $arg2 = xs:unsignedLong(lower bound).
   public void test_fn_avgulng2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgulng2args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgulng2args-2.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:unsignedLong(upper bound) $arg2 = xs:unsignedLong(lower bound).
   public void test_fn_avgulng2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgulng2args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgulng2args-3.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:unsignedLong(lower bound) $arg2 = xs:unsignedLong(mid range).
   public void test_fn_avgulng2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgulng2args-4.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgulng2args-4.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:unsignedLong(lower bound) $arg2 = xs:unsignedLong(upper bound).
   public void test_fn_avgulng2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgulng2args-5.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgulng2args-5.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:nonPositiveInteger(lower bound) $arg2 = xs:nonPositiveInteger(lower bound).
   public void test_fn_avgnpi2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnpi2args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnpi2args-1.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:nonPositiveInteger(mid range) $arg2 = xs:nonPositiveInteger(lower bound).
   public void test_fn_avgnpi2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnpi2args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnpi2args-2.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:nonPositiveInteger(upper bound) $arg2 = xs:nonPositiveInteger(lower bound).
   public void test_fn_avgnpi2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnpi2args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnpi2args-3.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:nonPositiveInteger(lower bound) $arg2 = xs:nonPositiveInteger(mid range).
   public void test_fn_avgnpi2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnpi2args-4.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnpi2args-4.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:nonPositiveInteger(lower bound) $arg2 = xs:nonPositiveInteger(upper bound).
   public void test_fn_avgnpi2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnpi2args-5.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnpi2args-5.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:nonNegativeInteger(lower bound) $arg2 = xs:nonNegativeInteger(lower bound).
   public void test_fn_avgnni2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnni2args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnni2args-1.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:nonNegativeInteger(mid range) $arg2 = xs:nonNegativeInteger(lower bound).
   public void test_fn_avgnni2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnni2args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnni2args-2.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:nonNegativeInteger(upper bound) $arg2 = xs:nonNegativeInteger(lower bound).
   public void test_fn_avgnni2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnni2args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnni2args-3.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:nonNegativeInteger(lower bound) $arg2 = xs:nonNegativeInteger(mid range).
   public void test_fn_avgnni2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnni2args-4.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnni2args-4.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:nonNegativeInteger(lower bound) $arg2 = xs:nonNegativeInteger(upper bound).
   public void test_fn_avgnni2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnni2args-5.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgnni2args-5.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:short(lower bound) $arg2 = xs:short(lower bound).
   public void test_fn_avgsht2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgsht2args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgsht2args-1.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:short(mid range) $arg2 = xs:short(lower bound).
   public void test_fn_avgsht2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgsht2args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgsht2args-2.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:short(upper bound) $arg2 = xs:short(lower bound).
   public void test_fn_avgsht2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgsht2args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgsht2args-3.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:short(lower bound) $arg2 = xs:short(mid range).
   public void test_fn_avgsht2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgsht2args-4.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgsht2args-4.txt";
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

   //Evaluates the "avg" function with the arguments set as follows: $arg1 = xs:short(lower bound) $arg2 = xs:short(upper bound).
   public void test_fn_avgsht2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgsht2args-5.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avgsht2args-5.txt";
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

   //Evaluates the "avg" function with the argument set to a sequence of strings. Should raise a type error.
   public void test_fn_avg_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-1.xq";
      String expectedResult = "FORG0006";
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

   //Evaluates the "avg" function with the argument set to the empty sequence.
   public void test_fn_avg_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-2.txt";
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

   //Arg: seq of integer.
   public void test_fn_avg_mix_args_001() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-001.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-001.txt";
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

   //Arg: yearMonthDuration.
   public void test_fn_avg_mix_args_002() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-002.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-002.txt";
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

   //Arg: empty seq.
   public void test_fn_avg_mix_args_003() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-003.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-003.txt";
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

   //Arg: float.
   public void test_fn_avg_mix_args_004() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-004.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-004.txt";
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

   //Arg: seq of integer, float.
   public void test_fn_avg_mix_args_005() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-005.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-005.txt";
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
    	 ex.printStackTrace();
         actual = ex.code();
         
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Arg: seq of integer.
   public void test_fn_avg_mix_args_006() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-006.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-006.txt";
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

   //Arg: seq of integer.
   public void test_fn_avg_mix_args_007() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-007.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-007.txt";
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

   //Arg: seq of integer.
   public void test_fn_avg_mix_args_008() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-008.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-008.txt";
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

   //Arg: seq of integer,decimal.
   public void test_fn_avg_mix_args_009() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-009.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-009.txt";
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

   //Arg: seq of integer,decimal.
   public void test_fn_avg_mix_args_010() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-010.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-010.txt";
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

   //Arg: seq of integer,float,empty seq.
   public void test_fn_avg_mix_args_011() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-011.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-011.txt";
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

   //Arg: seq of float,decimal.
   public void test_fn_avg_mix_args_012() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-012.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-012.txt";
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

   //Arg: seq of float,decimal.
   public void test_fn_avg_mix_args_013() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-013.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-013.txt";
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

   //Arg: seq of float,decimal.
   public void test_fn_avg_mix_args_014() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-014.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-014.txt";
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

   //Arg: seq of float,decimal.
   public void test_fn_avg_mix_args_015() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-015.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-015.txt";
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

   //Arg: seq of float,decimal.
   public void test_fn_avg_mix_args_016() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-016.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-016.txt";
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

   //Arg: seq of float,decimal.
   public void test_fn_avg_mix_args_017() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-017.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-017.txt";
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

   //Arg: seq of float.
   public void test_fn_avg_mix_args_018() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-018.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-018.txt";
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

   //Arg: seq of float,decimal.
   public void test_fn_avg_mix_args_019() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-019.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-019.txt";
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

   //Arg: seq of double,integer.
   public void test_fn_avg_mix_args_020() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-020.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-020.txt";
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

   //Arg: seq of double,integer.
   public void test_fn_avg_mix_args_021() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-021.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-021.txt";
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

   //Arg: seq of double,integer.
   public void test_fn_avg_mix_args_022() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-022.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-022.txt";
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

   //Arg: seq of double,integer.
   public void test_fn_avg_mix_args_023() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-023.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-023.txt";
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

   //Arg: seq of double,integer.
   public void test_fn_avg_mix_args_024() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-024.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-024.txt";
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

   //Arg: seq of double,integer.
   public void test_fn_avg_mix_args_025() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-025.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-025.txt";
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

   //Arg: seq of double,float.
   public void test_fn_avg_mix_args_026() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-026.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-026.txt";
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

   //Arg: seq of double,integer.
   public void test_fn_avg_mix_args_027() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-027.xq";
      String resultFile = "/ExpectedTestResults/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-027.txt";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_028() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-028.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_029() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-029.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_030() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-030.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_031() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-031.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_032() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-032.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_033() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-033.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_034() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-034.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_035() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-035.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_036() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-036.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_037() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-037.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_038() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-038.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_039() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-039.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_040() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-040.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_041() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-041.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_042() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-042.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_043() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-043.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_044() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-044.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_045() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-045.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_046() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-046.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_047() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-047.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_048() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-048.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_049() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-049.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_050() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-050.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_051() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-051.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_052() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-052.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_053() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-053.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_054() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-054.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_055() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-055.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_056() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-056.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_057() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-057.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_058() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-058.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_059() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-059.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_060() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-060.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_061() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-061.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_062() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-062.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_063() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-063.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_064() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-064.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0006.
   public void test_fn_avg_mix_args_065() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-065.xq";
      String expectedResult = "FORG0006";
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

   //Negative test gives FORG0001.
   public void test_fn_avg_mix_args_066() throws Exception {
      String inputFile = "/TestSources/bib2.xml";
      String xqFile = "/Queries/XQuery/Functions/SeqFunc/AggregateSeqFunc/SeqAVGFunc/fn-avg-mix-args-066.xq";
      String expectedResult = "FORG0001";
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
      