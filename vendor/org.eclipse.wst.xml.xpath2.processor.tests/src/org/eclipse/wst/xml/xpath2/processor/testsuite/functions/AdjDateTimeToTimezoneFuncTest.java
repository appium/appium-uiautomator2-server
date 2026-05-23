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
      
      
public class AdjDateTimeToTimezoneFuncTest extends AbstractPsychoPathTest {

   //Evaluates the "adjust-dateTime-to-timezone" function with the arguments set as follows: $arg = xs:dateTime(lower bound).
   public void test_fn_adjust_dateTime_to_timezone1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone1args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone1args-1.txt";
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

   //Evaluates the "adjust-dateTime-to-timezone" function with the arguments set as follows: $arg = xs:dateTime(mid range).
   public void test_fn_adjust_dateTime_to_timezone1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone1args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone1args-2.txt";
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

   //Evaluates the "adjust-dateTime-to-timezone" function with the arguments set as follows: $arg = xs:dateTime(upper bound).
   public void test_fn_adjust_dateTime_to_timezone1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone1args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone1args-3.txt";
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

   //Evaluates the "fn:adjust-dateTime-to-timezone" function as per example 1 for this function from the F and O specs.
   public void test_fn_adjust_dateTime_to_timezone_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-1.txt";
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

   //Evaluates the "fn:adjust-dateTime-to-timezone" function as per example 2 for this function from the F and O specs.
   public void test_fn_adjust_dateTime_to_timezone_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-2.txt";
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

   //Evaluates the "fn:adjust-dateTime-to-timezone" function as per example 5 for this function from the F and O specs.
   public void test_fn_adjust_dateTime_to_timezone_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-5.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-5.txt";
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

   //Evaluates the "fn:adjust-dateTime-to-timezone" function as per example 6 for this function from the F and O specs.
   public void test_fn_adjust_dateTime_to_timezone_6() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-6.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-6.txt";
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

   //Evaluates the "fn:adjust-dateTime-to-timezone" function as per example 7 for this function from the F and O specs.
   public void test_fn_adjust_dateTime_to_timezone_7() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-7.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-7.txt";
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

   //Evaluates the "fn:adjust-dateTime-to-timezone" function as per example 8 for this function from the F and O specs.
   public void test_fn_adjust_dateTime_to_timezone_8() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-8.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-8.txt";
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

   //Evaluates the "fn:adjust-dateTime-to-timezone" function as part of expression that yields a negative number (two adjust-dateTime-to-timezone functions used).
   public void test_fn_adjust_dateTime_to_timezone_9() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-9.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-9.txt";
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

   //Evaluates the "fn:adjust-dateTime-to-timezone" function as part of subtraction expression.
   public void test_fn_adjust_dateTime_to_timezone_10() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-10.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-10.txt";
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

   //Evaluates the "fn:adjust-dateTime-to-timezone" function as an argument to the "fn:string" function.
   public void test_fn_adjust_dateTime_to_timezone_11() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-11.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-11.txt";
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

   //Evaluates the "fn:adjust-dateTime-to-timezone" function as an argument to the "fn:boolean" function.
   public void test_fn_adjust_dateTime_to_timezone_12() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-12.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-12.txt";
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

   //Evaluates the "fn:adjust-dateTime-to-timezone" function as an argument to the "fn:not" function.
   public void test_fn_adjust_dateTime_to_timezone_13() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-13.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-13.txt";
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

   //Evaluates the "fn:adjust-dateTime-to-timezone" function as part of a boolean (or) expression and the "fn:true" function.
   public void test_fn_adjust_dateTime_to_timezone_14() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-14.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-14.txt";
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

   //Evaluates the "fn:adjust-dateTime-to-timezone" function as part of a boolean (or) expression and the "fn:false" function.
   public void test_fn_adjust_dateTime_to_timezone_15() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-15.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-15.txt";
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

   //Evaluates the "fn:adjust-dateTime-to-timezone" function as part of a boolean (and) expression and the "fn:true" function.
   public void test_fn_adjust_dateTime_to_timezone_16() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-16.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-16.txt";
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

   //Evaluates the "fn:adjust-dateTime-to-timezone" function as part of a boolean (and) expression and the "fn:false" function.
   public void test_fn_adjust_dateTime_to_timezone_17() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-17.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-17.txt";
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

   //Evaluates the "fn:adjust-dateTime-to-timezone" function as part of a subtraction expression that yields a negative number (an adjust-dateTime-to-timezone function and an xs:dateTime constructor used).
   public void test_fn_adjust_dateTime_to_timezone_18() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-18.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-18.txt";
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

   //Evaluates the "fn:adjust-dateTime-to-timezone" function where an xs:dateTime value is subtracted.
   public void test_fn_adjust_dateTime_to_timezone_19() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-19.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-19.txt";
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

   //Evaluates the "fn:adjust-dateTime-to-timezone" function as part of a comparison expression (ge operator).
   public void test_fn_adjust_dateTime_to_timezone_20() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-20.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-20.txt";
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

   //Evaluates the "fn:adjust-dateTime-to-timezone" function where teh first argument is the empty sequence.
   public void test_fn_adjust_dateTime_to_timezone_21() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-21.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-21.txt";
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

   //Evaluates the "fn:adjust-dateTime-to-timezone" with the value of $timezone is less than -PT14H.
   public void test_fn_adjust_dateTime_to_timezone_22() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-22.xq";
      String expectedResult = "FODT0003";
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

   //Evaluates the "fn:adjust-dateTime-to-timezone" with the value of $timezone is greater than PT14H.
   public void test_fn_adjust_dateTime_to_timezone_23() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjDateTimeToTimezoneFunc/fn-adjust-dateTime-to-timezone-23.xq";
      String expectedResult = "FODT0003";
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
      