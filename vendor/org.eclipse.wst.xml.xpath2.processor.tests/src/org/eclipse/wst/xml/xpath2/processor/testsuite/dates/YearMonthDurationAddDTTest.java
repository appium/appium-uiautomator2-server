
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
package org.eclipse.wst.xml.xpath2.processor.testsuite.dates;

import java.net.URL;

import org.apache.xerces.xs.XSModel;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.StaticError;
import org.eclipse.wst.xml.xpath2.processor.XPathParserException;
import org.eclipse.wst.xml.xpath2.processor.test.AbstractPsychoPathTest;
      
      
public class YearMonthDurationAddDTTest extends AbstractPsychoPathTest {

   //Evaluates the "op:add-yearMonthDuration-to-dateTime" operator with the arguments set as follows: $arg1 = xs:dateTime(lower bound) $arg2 = xs:yearMonthDuration(lower bound).
   public void test_op_add_yearMonthDuration_to_dateTime2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime2args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime2args-1.txt";
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

   //Evaluates the "op:add-yearMonthDuration-to-dateTime" operator with the arguments set as follows: $arg1 = xs:dateTime(mid range) $arg2 = xs:yearMonthDuration(lower bound).
   public void test_op_add_yearMonthDuration_to_dateTime2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime2args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime2args-2.txt";
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

   //Evaluates the "op:add-yearMonthDuration-to-dateTime" operator with the arguments set as follows: $arg1 = xs:dateTime(upper bound) $arg2 = xs:yearMonthDuration(lower bound).
   public void test_op_add_yearMonthDuration_to_dateTime2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime2args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime2args-3.txt";
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

   //Evaluates the "op:add-yearMonthDuration-to-dateTime" operator with the arguments set as follows: $arg1 = xs:dateTime(lower bound) $arg2 = xs:yearMonthDuration(mid range).
   public void test_op_add_yearMonthDuration_to_dateTime2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime2args-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime2args-4.txt";
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

   //Evaluates the "op:add-yearMonthDuration-to-dateTime" operator with the arguments set as follows: $arg1 = xs:dateTime(lower bound) $arg2 = xs:yearMonthDuration(upper bound).
   public void test_op_add_yearMonthDuration_to_dateTime2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime2args-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime2args-5.txt";
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

   //Evaluates the "op:add-yearMonthDuration-to-dateTime" operator as per example 1 (for this function) of the Functions and Operators spec.
   public void test_op_add_yearMonthDuration_to_dateTime_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime-1.txt";
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

   //Evaluates the "op:add-yearMonthDuration-to-dateTime" operator as part of a boolean expression (and operator) and the "fn:false" function.
   public void test_op_add_yearMonthDuration_to_dateTime_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime-2.txt";
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

   //Evaluates the "op:add-yearMonthDuration-to-dateTime" operator as part of a boolean expression (or operator) and the "fn:false" function.
   public void test_op_add_yearMonthDuration_to_dateTime_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime-3.txt";
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

   //Evaluates the "op:add-yearMonthDuration-to-dateTime" operator used in conjunction with the "fn:not" function.
   public void test_op_add_yearMonthDuration_to_dateTime_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime-4.txt";
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

   //Evaluates the "op:add-yearMonthDuration-to-dateTime" operator as an argument to the "fn:boolean" function.
   public void test_op_add_yearMonthDuration_to_dateTimealt_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTimealt-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTimealt-5.txt";
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

   //Evaluates the "op:add-yearMonthDuration-to-dateTime" operator as an argument to the "fn:number" function.
   public void test_op_add_yearMonthDuration_to_dateTime_6() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime-6.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime-6.txt";
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

   //Evaluates the "op:add-yearMonthDuration-to-dateTime" operator as an argument to the "fn:string" function.
   public void test_op_add_yearMonthDuration_to_dateTime_7() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime-7.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime-7.txt";
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

   //Evaluates the "op:add-yearMonthDuration-to-dateTime" operator that returns a negative value.
   public void test_op_add_yearMonthDuration_to_dateTime_8() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime-8.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime-8.txt";
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

   //Evaluates the "op:add-yearMonthDuration-to-dateTime" operator, which is part of an "and" expression.
   public void test_op_add_yearMonthDuration_to_dateTime_9() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime-9.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime-9.txt";
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

   //Evaluates the "op:add-yearMonthDuration-to-dateTime" operator as part of an "or" expression.
   public void test_op_add_yearMonthDuration_to_dateTime_10() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime-10.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime-10.txt";
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

   //Evaluates the "op:add-yearMonthDuration-to-dateTime" operator used in conjunction with a boolean expression and the "fn:true" function.
   public void test_op_add_yearMonthDuration_to_dateTime_12() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime-12.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime-12.txt";
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

   //Evaluates the "op:add-yearMonthDuration-to-dateTime" operator, which is part of a numeric-equal expression (eq operator).
   public void test_op_add_yearMonthDuration_to_dateTime_13() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime-13.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime-13.txt";
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

   //Evaluates the "op:add-yearMonthDuration-to-dateTime" operator, which is part of a numeric-equal expression (ne operator).
   public void test_op_add_yearMonthDuration_to_dateTime_14() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime-14.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime-14.txt";
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

   //Evaluates the "op:add-yearMonthDuration-to-dateTime" operator, which is part of a numeric-equal expression (le operator).
   public void test_op_add_yearMonthDuration_to_dateTime_15() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime-15.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime-15.txt";
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

   //Evaluates the "op:add-yearMonthDuration-to-dateTime" operator, which is part of a numeric-equal expression (ge operator).
   public void test_op_add_yearMonthDuration_to_dateTime_16() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime-16.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/DurationArith/YearMonthDurationAddDT/op-add-yearMonthDuration-to-dateTime-16.txt";
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
      