
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
package org.eclipse.wst.xml.xpath2.processor.testsuite.numeric;

import java.net.URL;

import org.apache.xerces.xs.XSModel;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.StaticError;
import org.eclipse.wst.xml.xpath2.processor.XPathParserException;
import org.eclipse.wst.xml.xpath2.processor.test.AbstractPsychoPathTest;
      
      
public class NumericUnaryPlusTest extends AbstractPsychoPathTest {

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:int(lower bound).
   public void test_op_numeric_unary_plusint1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusint1args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusint1args-1.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:int(mid range).
   public void test_op_numeric_unary_plusint1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusint1args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusint1args-2.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:int(upper bound).
   public void test_op_numeric_unary_plusint1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusint1args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusint1args-3.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:integer(lower bound).
   public void test_op_numeric_unary_plusintg1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusintg1args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusintg1args-1.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:integer(mid range).
   public void test_op_numeric_unary_plusintg1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusintg1args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusintg1args-2.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:integer(upper bound).
   public void test_op_numeric_unary_plusintg1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusintg1args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusintg1args-3.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:decimal(lower bound).
   public void test_op_numeric_unary_plusdec1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusdec1args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusdec1args-1.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:decimal(mid range).
   public void test_op_numeric_unary_plusdec1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusdec1args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusdec1args-2.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:decimal(upper bound).
   public void test_op_numeric_unary_plusdec1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusdec1args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusdec1args-3.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:double(lower bound).
   public void test_op_numeric_unary_plusdbl1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusdbl1args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusdbl1args-1.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:double(mid range).
   public void test_op_numeric_unary_plusdbl1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusdbl1args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusdbl1args-2.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:double(upper bound).
   public void test_op_numeric_unary_plusdbl1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusdbl1args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusdbl1args-3.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:float(lower bound).
   public void test_op_numeric_unary_plusflt1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusflt1args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusflt1args-1.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:float(mid range).
   public void test_op_numeric_unary_plusflt1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusflt1args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusflt1args-2.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:float(upper bound).
   public void test_op_numeric_unary_plusflt1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusflt1args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusflt1args-3.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:long(lower bound).
   public void test_op_numeric_unary_pluslng1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-pluslng1args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-pluslng1args-1.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:long(mid range).
   public void test_op_numeric_unary_pluslng1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-pluslng1args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-pluslng1args-2.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:long(upper bound).
   public void test_op_numeric_unary_pluslng1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-pluslng1args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-pluslng1args-3.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:unsignedShort(lower bound).
   public void test_op_numeric_unary_plususht1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plususht1args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plususht1args-1.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:unsignedShort(mid range).
   public void test_op_numeric_unary_plususht1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plususht1args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plususht1args-2.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:unsignedShort(upper bound).
   public void test_op_numeric_unary_plususht1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plususht1args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plususht1args-3.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:negativeInteger(lower bound).
   public void test_op_numeric_unary_plusnint1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusnint1args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusnint1args-1.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:negativeInteger(mid range).
   public void test_op_numeric_unary_plusnint1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusnint1args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusnint1args-2.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:negativeInteger(upper bound).
   public void test_op_numeric_unary_plusnint1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusnint1args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusnint1args-3.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:positiveInteger(lower bound).
   public void test_op_numeric_unary_pluspint1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-pluspint1args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-pluspint1args-1.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:positiveInteger(mid range).
   public void test_op_numeric_unary_pluspint1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-pluspint1args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-pluspint1args-2.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:positiveInteger(upper bound).
   public void test_op_numeric_unary_pluspint1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-pluspint1args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-pluspint1args-3.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:unsignedLong(lower bound).
   public void test_op_numeric_unary_plusulng1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusulng1args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusulng1args-1.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:unsignedLong(mid range).
   public void test_op_numeric_unary_plusulng1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusulng1args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusulng1args-2.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:unsignedLong(upper bound).
   public void test_op_numeric_unary_plusulng1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusulng1args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusulng1args-3.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:nonPositiveInteger(lower bound).
   public void test_op_numeric_unary_plusnpi1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusnpi1args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusnpi1args-1.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:nonPositiveInteger(mid range).
   public void test_op_numeric_unary_plusnpi1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusnpi1args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusnpi1args-2.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:nonPositiveInteger(upper bound).
   public void test_op_numeric_unary_plusnpi1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusnpi1args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusnpi1args-3.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:nonNegativeInteger(lower bound).
   public void test_op_numeric_unary_plusnni1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusnni1args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusnni1args-1.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:nonNegativeInteger(mid range).
   public void test_op_numeric_unary_plusnni1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusnni1args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusnni1args-2.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:nonNegativeInteger(upper bound).
   public void test_op_numeric_unary_plusnni1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusnni1args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plusnni1args-3.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:short(lower bound).
   public void test_op_numeric_unary_plussht1args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plussht1args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plussht1args-1.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:short(mid range).
   public void test_op_numeric_unary_plussht1args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plussht1args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plussht1args-2.txt";
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

   //Evaluates the "op:numeric-unary-plus" operator with the arguments set as follows: $arg = xs:short(upper bound).
   public void test_op_numeric_unary_plussht1args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plussht1args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericUnaryPlus/op-numeric-unary-plussht1args-3.txt";
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
      