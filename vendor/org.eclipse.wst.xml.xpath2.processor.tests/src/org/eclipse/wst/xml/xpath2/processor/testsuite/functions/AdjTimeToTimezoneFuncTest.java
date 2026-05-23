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
      
      
public class AdjTimeToTimezoneFuncTest extends AbstractPsychoPathTest {

   //Evaluates the "adjust-time-to-timezone" function with the arguments set as follows: $arg = xs:time(lower bound).
   public void test_fn_adjust_time_to_timezone1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone1args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone1args-1.txt";
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

   //Evaluates the "adjust-time-to-timezone" function with the arguments set as follows: $arg = xs:time(mid range).
   public void test_fn_adjust_time_to_timezone1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone1args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone1args-2.txt";
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

   //Evaluates the "adjust-time-to-timezone" function with the arguments set as follows: $arg = xs:time(upper bound).
   public void test_fn_adjust_time_to_timezone1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone1args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone1args-3.txt";
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

   //Evaluates the "fn:adjust-time-to-timezone" function as per example 1 for this function from the F and O specs.
   public void test_fn_adjust_time_to_timezone_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone-1.txt";
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

   //Evaluates the "fn:adjust-time-to-timezone" function as per example 2 for this function from the F and O specs.
   public void test_fn_adjust_time_to_timezone_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone-2.txt";
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

   //Evaluates the "fn:adjust-time-to-timezone" function as per example 5 for this function from the F and O specs.
   public void test_fn_adjust_time_to_timezone_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone-5.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone-5.txt";
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

   //Evaluates the "fn:adjust-time-to-timezone" function as per example 6 for this function from the F and O specs.
   public void test_fn_adjust_time_to_timezone_6() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone-6.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone-6.txt";
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

   //Evaluates the "fn:adjust-time-to-timezone" function as per example 7 for this function from the F and O specs.
   public void test_fn_adjust_time_to_timezone_7() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone-7.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone-7.txt";
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

   //Evaluates the "fn:adjust-time-to-timezone" function using the empty sequence as the first argument to the function.
   public void test_fn_adjust_time_to_timezone_8() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone-8.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone-8.txt";
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

   //Evaluates the "fn:adjust-time-to-timezone" function as part of expression that yields a negative number (two adjust-time-to-timezone functions used).
   public void test_fn_adjust_time_to_timezone_9() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone-9.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone-9.txt";
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

   //Evaluates the "fn:adjust-time-to-timezone" function as part of subtraction expression.
   public void test_fn_adjust_time_to_timezone_10() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone-10.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone-10.txt";
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

   //Evaluates the "fn:adjust-time-to-timezone" function as an argument to the "fn:string" function.
   public void test_fn_adjust_time_to_timezone_11() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone-11.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone-11.txt";
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

   //Evaluates the "fn:adjust-time-to-timezone" function as an argument to the "fn:boolean" function.
   public void test_fn_adjust_time_to_timezone_12() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone-12.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone-12.txt";
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

   //Evaluates the "fn:adjust-time-to-timezone" function as an argument to the "fn:not" function.
   public void test_fn_adjust_time_to_timezone_13() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone-13.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone-13.txt";
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

   //Evaluates the "fn:adjust-time-to-timezone" function as part of a boolean (or) expression and the "fn:true" function.
   public void test_fn_adjust_time_to_timezone_14() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone-14.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone-14.txt";
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

   //Evaluates the "fn:adjust-time-to-timezone" function as part of a boolean (or) expression and the "fn:false" function.
   public void test_fn_adjust_time_to_timezone_15() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone-15.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone-15.txt";
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

   //Evaluates the "fn:adjust-time-to-timezone" function as part of a boolean (and) expression and the "fn:true" function.
   public void test_fn_adjust_time_to_timezone_16() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone-16.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone-16.txt";
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

   //Evaluates the "fn:adjust-time-to-timezone" function as part of a boolean (and) expression and the "fn:false" function.
   public void test_fn_adjust_time_to_timezone_17() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone-17.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone-17.txt";
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

   //Evaluates the "fn:adjust-time-to-timezone" function as part of a comparison expression (ge operator).
   public void test_fn_adjust_time_to_timezone_20() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone-20.xq";
      String resultFile = "/ExpectedTestResults/Functions/DurationDateTimeFunc/TimezoneFunction/AdjTimeToTimezoneFunc/fn-adjust-time-to-timezone-20.txt";
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
      