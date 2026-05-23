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
 *     Jesper S Moller - bug 286452 - expect arity error in test_fn_current_dateTime_8
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.testsuite.functions;

import java.net.URL;

import org.apache.xerces.xs.XSModel;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.StaticError;
import org.eclipse.wst.xml.xpath2.processor.XPathParserException;
import org.eclipse.wst.xml.xpath2.processor.test.AbstractPsychoPathTest;
      
      
public class ContextCurrentDatetimeFuncTest extends AbstractPsychoPathTest {

   //Evaluation simple call to "fn:current-dateTime" function.
   public void test_fn_current_dateTime_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentDatetimeFunc/fn-current-dateTime-1.xq";
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
    	 fail("Returned:" + actual);

      } catch (StaticError ex) {
         actual = ex.code();
    	 fail("Returned:" + actual);

      } catch (DynamicError ex) {
         actual = ex.code();
    	 fail("Returned:" + actual);

      }

//      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of "fn:current-dateTime" function as argument to year-from-date function.
   public void test_fn_current_dateTime_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentDatetimeFunc/fn-current-dateTime-2.xq";
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
    	 fail("Returned:" + actual);

      } catch (StaticError ex) {
         actual = ex.code();
    	 fail("Returned:" + actual);

      } catch (DynamicError ex) {
         actual = ex.code();
    	 fail("Returned:" + actual);

      }

//      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of "fn:current-dateTime" function as argument to month-from-date function.
   public void test_fn_current_dateTime_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentDatetimeFunc/fn-current-dateTime-3.xq";
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
    	 fail("Returned:" + actual);

      } catch (StaticError ex) {
         actual = ex.code();
    	 fail("Returned:" + actual);

      } catch (DynamicError ex) {
         actual = ex.code();
    	 fail("Returned:" + actual);

      }

//      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of "fn:current-dateTime" function as argument to day-from-date function.
   public void test_fn_current_dateTime_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentDatetimeFunc/fn-current-dateTime-4.xq";
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
    	 fail("Returned:" + actual);

      } catch (StaticError ex) {
         actual = ex.code();
    	 fail("Returned:" + actual);

      } catch (DynamicError ex) {
         actual = ex.code();
    	 fail("Returned:" + actual);

      }

//      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of "fn:current-time" function as part of a subtraction operation.
   public void test_fn_current_dateTime_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentDatetimeFunc/fn-current-dateTime-5.xq";
      String resultFile = "/ExpectedTestResults/Functions/ContextFunc/ContextCurrentDatetimeFunc/fn-current-dateTime-5.txt";
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
    	 fail("Returned:" + actual);

      } catch (StaticError ex) {
         actual = ex.code();
    	 fail("Returned:" + actual);

      } catch (DynamicError ex) {
         actual = ex.code();
    	 fail("Returned:" + actual);

      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of "fn:current-dateTime" function as part of an addition operation and a dayTimeDuration.
//   public void test_fn_current_dateTime_6() throws Exception {
//      String inputFile = "/TestSources/emptydoc.xml";
//      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentDatetimeFunc/fn-current-dateTime-6.xq";
//      String resultFile = "/ExpectedTestResults/Functions/ContextFunc/ContextCurrentDatetimeFunc/fn-current-dateTime-6.txt";
//      String expectedResult = getExpectedResult(resultFile);
//      URL fileURL = bundle.getEntry(inputFile);
//      loadDOMDocument(fileURL);
//      
//      // Get XML Schema Information for the Document
//      XSModel schema = getGrammar();
//
//      setupDynamicContext(schema);
//
//      String xpath = extractXPathExpression(xqFile, inputFile);
//      String actual = null;
//      try {
//	   	  XPath path = compileXPath(xpath);
//	
//	      Evaluator eval = new DefaultEvaluator(dc, domDoc);
//	      ResultSequence rs = eval.evaluate(path);
//         
//          actual = buildResultString(rs);
//	
//      } catch (XPathParserException ex) {
//    	 actual = ex.code();
//      } catch (StaticError ex) {
//         actual = ex.code();
//      } catch (DynamicError ex) {
//         actual = ex.code();
//      }
//
//      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
//        
//
//   }

   //Evaluation of "fn:current-dateTime" function as part of a subtraction operation and a dayTimeDuration.
   public void test_fn_current_datetime_7() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentDatetimeFunc/fn-current-datetime-7.xq";
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
    	 fail("Returned:" + actual);

      } catch (StaticError ex) {
         actual = ex.code();
    	 fail("Returned:" + actual);

      } catch (DynamicError ex) {
         actual = ex.code();
    	 fail("Returned:" + actual);

      }

//      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of "fn:current-dateTime" function with incorrect arity.
   public void test_fn_current_dateTime_8() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentDatetimeFunc/fn-current-dateTime-8.xq";

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

   //Evaluation of "fn:current-dateTime" function as part of a subtration operation both operands are equal to current-time function.
   public void test_fn_current_dateTime_9() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentDatetimeFunc/fn-current-dateTime-9.xq";
      String resultFile = "/ExpectedTestResults/Functions/ContextFunc/ContextCurrentDatetimeFunc/fn-current-dateTime-9.txt";
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

   //Evaluation of "fn:current-dateTime" function as an argument to the xs:string function.
   public void test_fn_current_dateTime_10() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentDatetimeFunc/fn-current-dateTime-10.xq";
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
    	 fail("Returned:" + actual);

      } catch (StaticError ex) {
         actual = ex.code();
    	 fail("Returned:" + actual);

      } catch (DynamicError ex) {
         actual = ex.code();
    	 fail("Returned:" + actual);

      }

//      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of "fn:current-dateTime" function as an argument to the timezone-from-time function.
   public void test_fn_current_dateTime_11() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentDatetimeFunc/fn-current-dateTime-11.xq";
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
    	 fail("Returned:" + actual);

      } catch (StaticError ex) {
         actual = ex.code();
    	 fail("Returned:" + actual);

      } catch (DynamicError ex) {
         actual = ex.code();
    	 fail("Returned:" + actual);

      }

//      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of "fn:current-dateTime" function (string value) as part of an equal comparison (eq operator).
   public void test_fn_current_dateTime_12() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentDatetimeFunc/fn-current-dateTime-12.xq";
      String resultFile = "/ExpectedTestResults/Functions/ContextFunc/ContextCurrentDatetimeFunc/fn-current-dateTime-12.txt";
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

   //Evaluation of "fn:current-dateTime" function (string value) as part of an equal comparison (ne operator).
   public void test_fn_current_dateTime_13() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentDatetimeFunc/fn-current-dateTime-13.xq";
      String resultFile = "/ExpectedTestResults/Functions/ContextFunc/ContextCurrentDatetimeFunc/fn-current-dateTime-13.txt";
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

   //Evaluation of "fn:current-dateTime" function (string value) as part of an equal comparison (le operator).
   public void test_fn_current_dateTime_14() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentDatetimeFunc/fn-current-dateTime-14.xq";
      String resultFile = "/ExpectedTestResults/Functions/ContextFunc/ContextCurrentDatetimeFunc/fn-current-dateTime-14.txt";
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

   //Evaluation of "fn:current-dateTime" function (string value) as part of an equal comparison (ge operator).
   public void test_fn_current_dateTime_15() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentDatetimeFunc/fn-current-dateTime-15.xq";
      String resultFile = "/ExpectedTestResults/Functions/ContextFunc/ContextCurrentDatetimeFunc/fn-current-dateTime-15.txt";
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

   //Evaluation of "fn:current-dateTime" function (string value) as part of a boolean expression ("and" operator and fn:true function).
   public void test_fn_current_dateTime_16() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentDatetimeFunc/fn-current-dateTime-16.xq";
      String resultFile = "/ExpectedTestResults/Functions/ContextFunc/ContextCurrentDatetimeFunc/fn-current-dateTime-16.txt";
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

   //Evaluation of "fn:current-dateTime" function (string value) as part of a boolean expression ("and" operator and fn:false function).
   public void test_fn_current_dateTime_17() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentDatetimeFunc/fn-current-dateTime-17.xq";
      String resultFile = "/ExpectedTestResults/Functions/ContextFunc/ContextCurrentDatetimeFunc/fn-current-dateTime-17.txt";
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

   //Evaluation of "fn:current-dateTime" function (string value) as part of a boolean expression ("or" operator and fn:true function).
   public void test_fn_current_dateTime_18() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentDatetimeFunc/fn-current-dateTime-18.xq";
      String resultFile = "/ExpectedTestResults/Functions/ContextFunc/ContextCurrentDatetimeFunc/fn-current-dateTime-18.txt";
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

   //Evaluation of "fn:current-dateTime" function (string value) as part of a boolean expression ("or" operator and fn:false function).
   public void test_fn_current_dateTime_19() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentDatetimeFunc/fn-current-dateTime-19.xq";
      String resultFile = "/ExpectedTestResults/Functions/ContextFunc/ContextCurrentDatetimeFunc/fn-current-dateTime-19.txt";
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

   //Evaluation of "fn:current-dateTime" function (string value) as an argument to the fn:not function.
   public void test_fn_current_dateTime_20() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentDatetimeFunc/fn-current-dateTime-20.xq";
      String resultFile = "/ExpectedTestResults/Functions/ContextFunc/ContextCurrentDatetimeFunc/fn-current-dateTime-20.txt";
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

   //Evaluation of "fn:current-dateTime" function as part of a subtraction operation and a yearMonthDuration type.
   public void test_fn_current_dateTime_21() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentDatetimeFunc/fn-current-dateTime-21.xq";
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
    	 fail("Returned:" + actual);

      } catch (StaticError ex) {
         actual = ex.code();
    	 fail("Returned:" + actual);

      } catch (DynamicError ex) {
         actual = ex.code();
    	 fail("Returned:" + actual);

      }

//      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of "fn:current-dateTime" function as argument to hours-from-date function.
   public void test_fn_current_dateTime_22() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentDatetimeFunc/fn-current-dateTime-22.xq";
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
    	 fail("Returned:" + actual);

      } catch (StaticError ex) {
         actual = ex.code();
    	 fail("Returned:" + actual);

      } catch (DynamicError ex) {
         actual = ex.code();
    	 fail("Returned:" + actual);

      }

//      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of "fn:current-dateTime" function as argument to minutes-from-date function.
   public void test_fn_current_dateTime_23() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentDatetimeFunc/fn-current-dateTime-23.xq";
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
    	 fail("Returned:" + actual);

      } catch (StaticError ex) {
         actual = ex.code();
    	 fail("Returned:" + actual);

      } catch (DynamicError ex) {
         actual = ex.code();
    	 fail("Returned:" + actual);

      }

//      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of "fn:current-dateTime" function as argument to seconds-from-date function.
   public void test_fn_current_dateTime_24() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentDatetimeFunc/fn-current-dateTime-24.xq";
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
    	 fail("Returned:" + actual);

      } catch (StaticError ex) {
         actual = ex.code();
    	 fail("Returned:" + actual);

      } catch (DynamicError ex) {
         actual = ex.code();
    	 fail("Returned:" + actual);

      }

      //assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

}
      