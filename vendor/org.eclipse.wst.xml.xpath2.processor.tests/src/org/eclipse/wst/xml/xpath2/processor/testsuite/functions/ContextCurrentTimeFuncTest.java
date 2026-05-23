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
 *     Jesper S Moller - bug 286452 - expect arity error in test_fn_current_time_8
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.testsuite.functions;

import java.net.URL;

import org.apache.xerces.xs.XSModel;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.StaticError;
import org.eclipse.wst.xml.xpath2.processor.XPathParserException;
import org.eclipse.wst.xml.xpath2.processor.test.AbstractPsychoPathTest;
      
      
public class ContextCurrentTimeFuncTest extends AbstractPsychoPathTest {

   //Evaluation simple call to "fn:current-time" function.
   public void test_fn_current_time_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentTimeFunc/fn-current-time-1.xq";
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
    	 fail("Return code:" + actual);
      } catch (StaticError ex) {
         actual = ex.code();
    	 fail("Return code:" + actual);

      } catch (DynamicError ex) {
         actual = ex.code();
    	 fail("Return code:" + actual);

      }

//      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of "fn:current-time" function as argument to hours-from-time function.
   public void test_fn_current_time_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentTimeFunc/fn-current-time-2.xq";
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
    	 fail("Return code:" + actual);

      } catch (StaticError ex) {
         actual = ex.code();
    	 fail("Return code:" + actual);

      } catch (DynamicError ex) {
         actual = ex.code();
    	 fail("Return code:" + actual);

      }

//      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of "fn:current-time" function as argument to minutes-from-time function.
   public void test_fn_current_time_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentTimeFunc/fn-current-time-3.xq";
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
    	 fail("Return code:" + actual);

      } catch (StaticError ex) {
         actual = ex.code();
    	 fail("Return code:" + actual);

      } catch (DynamicError ex) {
         actual = ex.code();
    	 fail("Return code:" + actual);

      }

//      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of "fn:current-time" function as argument to fn-seconds-from time function.
   public void test_fn_current_time_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentTimeFunc/fn-current-time-4.xq";
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
    	 fail("Return code:" + actual);

      } catch (StaticError ex) {
         actual = ex.code();
    	 fail("Return code:" + actual);

      } catch (DynamicError ex) {
         actual = ex.code();
    	 fail("Return code:" + actual);

      }

//      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of "fn:current-time" function as part of a subtraction operation.
   public void test_fn_current_time_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentTimeFunc/fn-current-time-5.xq";
      String resultFile = "/ExpectedTestResults/Functions/ContextFunc/ContextCurrentTimeFunc/fn-current-time-5.txt";
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
    	 fail("Return code:" + actual);

      } catch (StaticError ex) {
         actual = ex.code();
    	 fail("Return code:" + actual);

      } catch (DynamicError ex) {
         actual = ex.code();
    	 fail("Return code:" + actual);

      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of "fn:current-time" function as part of an addition operation and a dayTimeDuration.
   public void test_fn_current_time_6() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentTimeFunc/fn-current-time-6.xq";
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
    	 fail("Return code:" + actual);

      } catch (StaticError ex) {
         actual = ex.code();
    	 fail("Return code:" + actual);

      } catch (DynamicError ex) {
         actual = ex.code();
    	 fail("Return code:" + actual);

      }

//      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of "fn:current-time" function as part of a subtraction operation and a dayTimeDuration.
   public void test_fn_current_time_7() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentTimeFunc/fn-current-time-7.xq";
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
    	 fail("Return code:" + actual);

      } catch (StaticError ex) {
         actual = ex.code();
    	 fail("Return code:" + actual);

      } catch (DynamicError ex) {
         actual = ex.code();
    	 fail("Return code:" + actual);

      }

//      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of "fn:current-time" function with incorrect arity.
   public void test_fn_current_time_8() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentTimeFunc/fn-current-time-8.xq";

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

   //Evaluation of "fn:current-time" function as part of a subtration operation both operands are equal to current-time function.
   public void test_fn_current_time_9() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentTimeFunc/fn-current-time-9.xq";
      String resultFile = "/ExpectedTestResults/Functions/ContextFunc/ContextCurrentTimeFunc/fn-current-time-9.txt";
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

   //Evaluation of "fn:current-time" function as an argument to the xs:string function.
   public void test_fn_current_time_10() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentTimeFunc/fn-current-time-10.xq";
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
    	 fail("Return code:" + actual);

      } catch (StaticError ex) {
         actual = ex.code();
    	 fail("Return code:" + actual);

      } catch (DynamicError ex) {
         actual = ex.code();
    	 fail("Return code:" + actual);

      }

//      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of "fn:current-time" function as an argument to the timezone-from-time function.
   public void test_fn_current_time_11() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentTimeFunc/fn-current-time-11.xq";
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
    	 fail("Return code:" + actual);

      } catch (StaticError ex) {
         actual = ex.code();
    	 fail("Return code:" + actual);

      } catch (DynamicError ex) {
         actual = ex.code();
    	 fail("Return code:" + actual);

      }

//      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of "fn:current-time" function (string value) as part of an equal comparison (eq operator).
   public void test_fn_current_time_12() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentTimeFunc/fn-current-time-12.xq";
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
    	 fail("Return code:" + actual);

      } catch (StaticError ex) {
         actual = ex.code();
    	 fail("Return code:" + actual);

      } catch (DynamicError ex) {
         actual = ex.code();
    	 fail("Return code:" + actual);

      }

//      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of "fn:current-time" function (string value) as part of an equal comparison (ne operator).
   public void test_fn_current_time_13() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentTimeFunc/fn-current-time-13.xq";
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
    	 fail("Return code:" + actual);

      } catch (StaticError ex) {
         actual = ex.code();
    	 fail("Return code:" + actual);

      } catch (DynamicError ex) {
         actual = ex.code();
    	 fail("Return code:" + actual);

      }

//      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of "fn:current-time" function (string value) as part of an equal comparison (le operator).
   public void test_fn_current_time_14() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentTimeFunc/fn-current-time-14.xq";
      String resultFile = "/ExpectedTestResults/Functions/ContextFunc/ContextCurrentTimeFunc/fn-current-time-14.txt";
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

   //Evaluation of "fn:current-time" function (string value) as part of an equal comparison (ge operator).
   public void test_fn_current_time_15() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentTimeFunc/fn-current-time-15.xq";
      String resultFile = "/ExpectedTestResults/Functions/ContextFunc/ContextCurrentTimeFunc/fn-current-time-15.txt";
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

   //Evaluation of fn:current-time" function (string value) as part of a boolean expression ("and" operator and fn:true function).
   public void test_fn_current_time_16() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentTimeFunc/fn-current-time-16.xq";
      String resultFile = "/ExpectedTestResults/Functions/ContextFunc/ContextCurrentTimeFunc/fn-current-time-16.txt";
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

   //Evaluation of "fn:current-time" function (string value) as part of a boolean expression ("and" operator and fn:false function).
   public void test_fn_current_time_17() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentTimeFunc/fn-current-time-17.xq";
      String resultFile = "/ExpectedTestResults/Functions/ContextFunc/ContextCurrentTimeFunc/fn-current-time-17.txt";
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

   //Evaluation of "fn:current-time" function (string value) as part of a boolean expression ("or" operator and fn:true function).
   public void test_fn_current_time_18() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentTimeFunc/fn-current-time-18.xq";
      String resultFile = "/ExpectedTestResults/Functions/ContextFunc/ContextCurrentTimeFunc/fn-current-time-18.txt";
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

   //Evaluation of "fn:current-time" function (string value) as part of a boolean expression ("or" operator and fn:false function).
   public void test_fn_current_time_19() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentTimeFunc/fn-current-time-19.xq";
      String resultFile = "/ExpectedTestResults/Functions/ContextFunc/ContextCurrentTimeFunc/fn-current-time-19.txt";
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

   //Evaluation of "fn:current-time" function (string value) as an argument to the fn:not function.
   public void test_fn_current_time_20() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/ContextFunc/ContextCurrentTimeFunc/fn-current-time-20.xq";
      String resultFile = "/ExpectedTestResults/Functions/ContextFunc/ContextCurrentTimeFunc/fn-current-time-20.txt";
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
      