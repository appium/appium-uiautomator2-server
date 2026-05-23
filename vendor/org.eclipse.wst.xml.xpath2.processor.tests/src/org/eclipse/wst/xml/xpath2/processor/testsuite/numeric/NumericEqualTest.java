
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
      
      
public class NumericEqualTest extends AbstractPsychoPathTest {

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:int(lower bound) $arg2 = xs:int(lower bound).
   public void test_op_numeric_equalint2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalint2args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalint2args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:int(mid range) $arg2 = xs:int(lower bound).
   public void test_op_numeric_equalint2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalint2args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalint2args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:int(upper bound) $arg2 = xs:int(lower bound).
   public void test_op_numeric_equalint2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalint2args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalint2args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:int(lower bound) $arg2 = xs:int(mid range).
   public void test_op_numeric_equalint2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalint2args-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalint2args-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:int(lower bound) $arg2 = xs:int(upper bound).
   public void test_op_numeric_equalint2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalint2args-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalint2args-5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:int(lower bound) $arg2 = xs:int(lower bound).
   public void test_op_numeric_equalint2args_6() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalint2args-6.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalint2args-6.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:int(mid range) $arg2 = xs:int(lower bound).
   public void test_op_numeric_equalint2args_7() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalint2args-7.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalint2args-7.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:int(upper bound) $arg2 = xs:int(lower bound).
   public void test_op_numeric_equalint2args_8() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalint2args-8.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalint2args-8.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:int(lower bound) $arg2 = xs:int(mid range).
   public void test_op_numeric_equalint2args_9() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalint2args-9.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalint2args-9.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:int(lower bound) $arg2 = xs:int(upper bound).
   public void test_op_numeric_equalint2args_10() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalint2args-10.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalint2args-10.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:integer(lower bound) $arg2 = xs:integer(lower bound).
   public void test_op_numeric_equalintg2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalintg2args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalintg2args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:integer(mid range) $arg2 = xs:integer(lower bound).
   public void test_op_numeric_equalintg2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalintg2args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalintg2args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:integer(upper bound) $arg2 = xs:integer(lower bound).
   public void test_op_numeric_equalintg2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalintg2args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalintg2args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:integer(lower bound) $arg2 = xs:integer(mid range).
   public void test_op_numeric_equalintg2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalintg2args-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalintg2args-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:integer(lower bound) $arg2 = xs:integer(upper bound).
   public void test_op_numeric_equalintg2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalintg2args-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalintg2args-5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:integer(lower bound) $arg2 = xs:integer(lower bound).
   public void test_op_numeric_equalintg2args_6() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalintg2args-6.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalintg2args-6.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:integer(mid range) $arg2 = xs:integer(lower bound).
   public void test_op_numeric_equalintg2args_7() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalintg2args-7.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalintg2args-7.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:integer(upper bound) $arg2 = xs:integer(lower bound).
   public void test_op_numeric_equalintg2args_8() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalintg2args-8.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalintg2args-8.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:integer(lower bound) $arg2 = xs:integer(mid range).
   public void test_op_numeric_equalintg2args_9() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalintg2args-9.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalintg2args-9.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:integer(lower bound) $arg2 = xs:integer(upper bound).
   public void test_op_numeric_equalintg2args_10() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalintg2args-10.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalintg2args-10.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:decimal(lower bound) $arg2 = xs:decimal(lower bound).
   public void test_op_numeric_equaldec2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldec2args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldec2args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:decimal(mid range) $arg2 = xs:decimal(lower bound).
   public void test_op_numeric_equaldec2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldec2args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldec2args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:decimal(upper bound) $arg2 = xs:decimal(lower bound).
   public void test_op_numeric_equaldec2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldec2args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldec2args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:decimal(lower bound) $arg2 = xs:decimal(mid range).
   public void test_op_numeric_equaldec2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldec2args-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldec2args-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:decimal(lower bound) $arg2 = xs:decimal(upper bound).
   public void test_op_numeric_equaldec2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldec2args-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldec2args-5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:decimal(lower bound) $arg2 = xs:decimal(lower bound).
   public void test_op_numeric_equaldec2args_6() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldec2args-6.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldec2args-6.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:decimal(mid range) $arg2 = xs:decimal(lower bound).
   public void test_op_numeric_equaldec2args_7() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldec2args-7.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldec2args-7.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:decimal(upper bound) $arg2 = xs:decimal(lower bound).
   public void test_op_numeric_equaldec2args_8() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldec2args-8.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldec2args-8.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:decimal(lower bound) $arg2 = xs:decimal(mid range).
   public void test_op_numeric_equaldec2args_9() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldec2args-9.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldec2args-9.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:decimal(lower bound) $arg2 = xs:decimal(upper bound).
   public void test_op_numeric_equaldec2args_10() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldec2args-10.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldec2args-10.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:double(lower bound) $arg2 = xs:double(lower bound).
   public void test_op_numeric_equaldbl2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldbl2args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldbl2args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:double(mid range) $arg2 = xs:double(lower bound).
   public void test_op_numeric_equaldbl2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldbl2args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldbl2args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:double(upper bound) $arg2 = xs:double(lower bound).
   public void test_op_numeric_equaldbl2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldbl2args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldbl2args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:double(lower bound) $arg2 = xs:double(mid range).
   public void test_op_numeric_equaldbl2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldbl2args-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldbl2args-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:double(lower bound) $arg2 = xs:double(upper bound).
   public void test_op_numeric_equaldbl2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldbl2args-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldbl2args-5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:double(lower bound) $arg2 = xs:double(lower bound).
   public void test_op_numeric_equaldbl2args_6() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldbl2args-6.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldbl2args-6.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:double(mid range) $arg2 = xs:double(lower bound).
   public void test_op_numeric_equaldbl2args_7() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldbl2args-7.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldbl2args-7.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:double(upper bound) $arg2 = xs:double(lower bound).
   public void test_op_numeric_equaldbl2args_8() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldbl2args-8.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldbl2args-8.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:double(lower bound) $arg2 = xs:double(mid range).
   public void test_op_numeric_equaldbl2args_9() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldbl2args-9.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldbl2args-9.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:double(lower bound) $arg2 = xs:double(upper bound).
   public void test_op_numeric_equaldbl2args_10() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldbl2args-10.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equaldbl2args-10.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:float(lower bound) $arg2 = xs:float(lower bound).
   public void test_op_numeric_equalflt2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalflt2args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalflt2args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:float(mid range) $arg2 = xs:float(lower bound).
   public void test_op_numeric_equalflt2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalflt2args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalflt2args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:float(upper bound) $arg2 = xs:float(lower bound).
   public void test_op_numeric_equalflt2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalflt2args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalflt2args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:float(lower bound) $arg2 = xs:float(mid range).
   public void test_op_numeric_equalflt2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalflt2args-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalflt2args-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:float(lower bound) $arg2 = xs:float(upper bound).
   public void test_op_numeric_equalflt2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalflt2args-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalflt2args-5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:float(lower bound) $arg2 = xs:float(lower bound).
   public void test_op_numeric_equalflt2args_6() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalflt2args-6.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalflt2args-6.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:float(mid range) $arg2 = xs:float(lower bound).
   public void test_op_numeric_equalflt2args_7() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalflt2args-7.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalflt2args-7.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:float(upper bound) $arg2 = xs:float(lower bound).
   public void test_op_numeric_equalflt2args_8() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalflt2args-8.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalflt2args-8.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:float(lower bound) $arg2 = xs:float(mid range).
   public void test_op_numeric_equalflt2args_9() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalflt2args-9.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalflt2args-9.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:float(lower bound) $arg2 = xs:float(upper bound).
   public void test_op_numeric_equalflt2args_10() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalflt2args-10.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalflt2args-10.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:long(lower bound) $arg2 = xs:long(lower bound).
   public void test_op_numeric_equallng2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equallng2args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equallng2args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:long(mid range) $arg2 = xs:long(lower bound).
   public void test_op_numeric_equallng2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equallng2args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equallng2args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:long(upper bound) $arg2 = xs:long(lower bound).
   public void test_op_numeric_equallng2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equallng2args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equallng2args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:long(lower bound) $arg2 = xs:long(mid range).
   public void test_op_numeric_equallng2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equallng2args-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equallng2args-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:long(lower bound) $arg2 = xs:long(upper bound).
   public void test_op_numeric_equallng2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equallng2args-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equallng2args-5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:long(lower bound) $arg2 = xs:long(lower bound).
   public void test_op_numeric_equallng2args_6() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equallng2args-6.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equallng2args-6.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:long(mid range) $arg2 = xs:long(lower bound).
   public void test_op_numeric_equallng2args_7() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equallng2args-7.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equallng2args-7.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:long(upper bound) $arg2 = xs:long(lower bound).
   public void test_op_numeric_equallng2args_8() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equallng2args-8.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equallng2args-8.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:long(lower bound) $arg2 = xs:long(mid range).
   public void test_op_numeric_equallng2args_9() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equallng2args-9.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equallng2args-9.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:long(lower bound) $arg2 = xs:long(upper bound).
   public void test_op_numeric_equallng2args_10() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equallng2args-10.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equallng2args-10.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:unsignedShort(lower bound) $arg2 = xs:unsignedShort(lower bound).
   public void test_op_numeric_equalusht2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalusht2args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalusht2args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:unsignedShort(mid range) $arg2 = xs:unsignedShort(lower bound).
   public void test_op_numeric_equalusht2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalusht2args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalusht2args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:unsignedShort(upper bound) $arg2 = xs:unsignedShort(lower bound).
   public void test_op_numeric_equalusht2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalusht2args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalusht2args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:unsignedShort(lower bound) $arg2 = xs:unsignedShort(mid range).
   public void test_op_numeric_equalusht2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalusht2args-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalusht2args-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:unsignedShort(lower bound) $arg2 = xs:unsignedShort(upper bound).
   public void test_op_numeric_equalusht2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalusht2args-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalusht2args-5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:unsignedShort(lower bound) $arg2 = xs:unsignedShort(lower bound).
   public void test_op_numeric_equalusht2args_6() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalusht2args-6.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalusht2args-6.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:unsignedShort(mid range) $arg2 = xs:unsignedShort(lower bound).
   public void test_op_numeric_equalusht2args_7() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalusht2args-7.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalusht2args-7.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:unsignedShort(upper bound) $arg2 = xs:unsignedShort(lower bound).
   public void test_op_numeric_equalusht2args_8() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalusht2args-8.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalusht2args-8.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:unsignedShort(lower bound) $arg2 = xs:unsignedShort(mid range).
   public void test_op_numeric_equalusht2args_9() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalusht2args-9.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalusht2args-9.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:unsignedShort(lower bound) $arg2 = xs:unsignedShort(upper bound).
   public void test_op_numeric_equalusht2args_10() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalusht2args-10.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalusht2args-10.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:negativeInteger(lower bound) $arg2 = xs:negativeInteger(lower bound).
   public void test_op_numeric_equalnint2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnint2args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnint2args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:negativeInteger(mid range) $arg2 = xs:negativeInteger(lower bound).
   public void test_op_numeric_equalnint2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnint2args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnint2args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:negativeInteger(upper bound) $arg2 = xs:negativeInteger(lower bound).
   public void test_op_numeric_equalnint2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnint2args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnint2args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:negativeInteger(lower bound) $arg2 = xs:negativeInteger(mid range).
   public void test_op_numeric_equalnint2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnint2args-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnint2args-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:negativeInteger(lower bound) $arg2 = xs:negativeInteger(upper bound).
   public void test_op_numeric_equalnint2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnint2args-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnint2args-5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:negativeInteger(lower bound) $arg2 = xs:negativeInteger(lower bound).
   public void test_op_numeric_equalnint2args_6() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnint2args-6.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnint2args-6.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:negativeInteger(mid range) $arg2 = xs:negativeInteger(lower bound).
   public void test_op_numeric_equalnint2args_7() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnint2args-7.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnint2args-7.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:negativeInteger(upper bound) $arg2 = xs:negativeInteger(lower bound).
   public void test_op_numeric_equalnint2args_8() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnint2args-8.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnint2args-8.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:negativeInteger(lower bound) $arg2 = xs:negativeInteger(mid range).
   public void test_op_numeric_equalnint2args_9() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnint2args-9.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnint2args-9.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:negativeInteger(lower bound) $arg2 = xs:negativeInteger(upper bound).
   public void test_op_numeric_equalnint2args_10() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnint2args-10.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnint2args-10.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:positiveInteger(lower bound) $arg2 = xs:positiveInteger(lower bound).
   public void test_op_numeric_equalpint2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalpint2args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalpint2args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:positiveInteger(mid range) $arg2 = xs:positiveInteger(lower bound).
   public void test_op_numeric_equalpint2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalpint2args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalpint2args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:positiveInteger(upper bound) $arg2 = xs:positiveInteger(lower bound).
   public void test_op_numeric_equalpint2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalpint2args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalpint2args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:positiveInteger(lower bound) $arg2 = xs:positiveInteger(mid range).
   public void test_op_numeric_equalpint2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalpint2args-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalpint2args-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:positiveInteger(lower bound) $arg2 = xs:positiveInteger(upper bound).
   public void test_op_numeric_equalpint2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalpint2args-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalpint2args-5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:positiveInteger(lower bound) $arg2 = xs:positiveInteger(lower bound).
   public void test_op_numeric_equalpint2args_6() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalpint2args-6.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalpint2args-6.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:positiveInteger(mid range) $arg2 = xs:positiveInteger(lower bound).
   public void test_op_numeric_equalpint2args_7() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalpint2args-7.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalpint2args-7.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:positiveInteger(upper bound) $arg2 = xs:positiveInteger(lower bound).
   public void test_op_numeric_equalpint2args_8() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalpint2args-8.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalpint2args-8.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:positiveInteger(lower bound) $arg2 = xs:positiveInteger(mid range).
   public void test_op_numeric_equalpint2args_9() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalpint2args-9.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalpint2args-9.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:positiveInteger(lower bound) $arg2 = xs:positiveInteger(upper bound).
   public void test_op_numeric_equalpint2args_10() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalpint2args-10.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalpint2args-10.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:unsignedLong(lower bound) $arg2 = xs:unsignedLong(lower bound).
   public void test_op_numeric_equalulng2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalulng2args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalulng2args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:unsignedLong(mid range) $arg2 = xs:unsignedLong(lower bound).
   public void test_op_numeric_equalulng2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalulng2args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalulng2args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:unsignedLong(upper bound) $arg2 = xs:unsignedLong(lower bound).
   public void test_op_numeric_equalulng2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalulng2args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalulng2args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:unsignedLong(lower bound) $arg2 = xs:unsignedLong(mid range).
   public void test_op_numeric_equalulng2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalulng2args-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalulng2args-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:unsignedLong(lower bound) $arg2 = xs:unsignedLong(upper bound).
   public void test_op_numeric_equalulng2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalulng2args-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalulng2args-5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:unsignedLong(lower bound) $arg2 = xs:unsignedLong(lower bound).
   public void test_op_numeric_equalulng2args_6() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalulng2args-6.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalulng2args-6.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:unsignedLong(mid range) $arg2 = xs:unsignedLong(lower bound).
   public void test_op_numeric_equalulng2args_7() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalulng2args-7.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalulng2args-7.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:unsignedLong(upper bound) $arg2 = xs:unsignedLong(lower bound).
   public void test_op_numeric_equalulng2args_8() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalulng2args-8.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalulng2args-8.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:unsignedLong(lower bound) $arg2 = xs:unsignedLong(mid range).
   public void test_op_numeric_equalulng2args_9() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalulng2args-9.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalulng2args-9.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:unsignedLong(lower bound) $arg2 = xs:unsignedLong(upper bound).
   public void test_op_numeric_equalulng2args_10() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalulng2args-10.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalulng2args-10.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:nonPositiveInteger(lower bound) $arg2 = xs:nonPositiveInteger(lower bound).
   public void test_op_numeric_equalnpi2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnpi2args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnpi2args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:nonPositiveInteger(mid range) $arg2 = xs:nonPositiveInteger(lower bound).
   public void test_op_numeric_equalnpi2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnpi2args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnpi2args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:nonPositiveInteger(upper bound) $arg2 = xs:nonPositiveInteger(lower bound).
   public void test_op_numeric_equalnpi2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnpi2args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnpi2args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:nonPositiveInteger(lower bound) $arg2 = xs:nonPositiveInteger(mid range).
   public void test_op_numeric_equalnpi2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnpi2args-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnpi2args-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:nonPositiveInteger(lower bound) $arg2 = xs:nonPositiveInteger(upper bound).
   public void test_op_numeric_equalnpi2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnpi2args-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnpi2args-5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:nonPositiveInteger(lower bound) $arg2 = xs:nonPositiveInteger(lower bound).
   public void test_op_numeric_equalnpi2args_6() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnpi2args-6.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnpi2args-6.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:nonPositiveInteger(mid range) $arg2 = xs:nonPositiveInteger(lower bound).
   public void test_op_numeric_equalnpi2args_7() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnpi2args-7.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnpi2args-7.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:nonPositiveInteger(upper bound) $arg2 = xs:nonPositiveInteger(lower bound).
   public void test_op_numeric_equalnpi2args_8() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnpi2args-8.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnpi2args-8.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:nonPositiveInteger(lower bound) $arg2 = xs:nonPositiveInteger(mid range).
   public void test_op_numeric_equalnpi2args_9() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnpi2args-9.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnpi2args-9.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:nonPositiveInteger(lower bound) $arg2 = xs:nonPositiveInteger(upper bound).
   public void test_op_numeric_equalnpi2args_10() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnpi2args-10.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnpi2args-10.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:nonNegativeInteger(lower bound) $arg2 = xs:nonNegativeInteger(lower bound).
   public void test_op_numeric_equalnni2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnni2args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnni2args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:nonNegativeInteger(mid range) $arg2 = xs:nonNegativeInteger(lower bound).
   public void test_op_numeric_equalnni2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnni2args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnni2args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:nonNegativeInteger(upper bound) $arg2 = xs:nonNegativeInteger(lower bound).
   public void test_op_numeric_equalnni2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnni2args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnni2args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:nonNegativeInteger(lower bound) $arg2 = xs:nonNegativeInteger(mid range).
   public void test_op_numeric_equalnni2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnni2args-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnni2args-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:nonNegativeInteger(lower bound) $arg2 = xs:nonNegativeInteger(upper bound).
   public void test_op_numeric_equalnni2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnni2args-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnni2args-5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:nonNegativeInteger(lower bound) $arg2 = xs:nonNegativeInteger(lower bound).
   public void test_op_numeric_equalnni2args_6() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnni2args-6.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnni2args-6.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:nonNegativeInteger(mid range) $arg2 = xs:nonNegativeInteger(lower bound).
   public void test_op_numeric_equalnni2args_7() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnni2args-7.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnni2args-7.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:nonNegativeInteger(upper bound) $arg2 = xs:nonNegativeInteger(lower bound).
   public void test_op_numeric_equalnni2args_8() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnni2args-8.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnni2args-8.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:nonNegativeInteger(lower bound) $arg2 = xs:nonNegativeInteger(mid range).
   public void test_op_numeric_equalnni2args_9() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnni2args-9.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnni2args-9.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:nonNegativeInteger(lower bound) $arg2 = xs:nonNegativeInteger(upper bound).
   public void test_op_numeric_equalnni2args_10() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnni2args-10.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalnni2args-10.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:short(lower bound) $arg2 = xs:short(lower bound).
   public void test_op_numeric_equalsht2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalsht2args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalsht2args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:short(mid range) $arg2 = xs:short(lower bound).
   public void test_op_numeric_equalsht2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalsht2args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalsht2args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:short(upper bound) $arg2 = xs:short(lower bound).
   public void test_op_numeric_equalsht2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalsht2args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalsht2args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:short(lower bound) $arg2 = xs:short(mid range).
   public void test_op_numeric_equalsht2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalsht2args-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalsht2args-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:short(lower bound) $arg2 = xs:short(upper bound).
   public void test_op_numeric_equalsht2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalsht2args-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalsht2args-5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:short(lower bound) $arg2 = xs:short(lower bound).
   public void test_op_numeric_equalsht2args_6() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalsht2args-6.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalsht2args-6.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:short(mid range) $arg2 = xs:short(lower bound).
   public void test_op_numeric_equalsht2args_7() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalsht2args-7.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalsht2args-7.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:short(upper bound) $arg2 = xs:short(lower bound).
   public void test_op_numeric_equalsht2args_8() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalsht2args-8.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalsht2args-8.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:short(lower bound) $arg2 = xs:short(mid range).
   public void test_op_numeric_equalsht2args_9() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalsht2args-9.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalsht2args-9.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = xs:short(lower bound) $arg2 = xs:short(upper bound).
   public void test_op_numeric_equalsht2args_10() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalsht2args-10.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equalsht2args-10.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-equal" operator with the arguments set as follows: $arg1 = () $arg2 = ().
   public void test_op_numeric_equal_emptyseq() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equal-emptyseq.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericEqual/op-numeric-equal-emptyseq.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
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
      