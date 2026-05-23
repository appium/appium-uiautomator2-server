
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
      
      
public class NumericGTTest extends AbstractPsychoPathTest {

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:int(lower bound) $arg2 = xs:int(lower bound).
   public void test_op_numeric_greater_thanint2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanint2args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanint2args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:int(mid range) $arg2 = xs:int(lower bound).
   public void test_op_numeric_greater_thanint2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanint2args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanint2args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:int(upper bound) $arg2 = xs:int(lower bound).
   public void test_op_numeric_greater_thanint2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanint2args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanint2args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:int(lower bound) $arg2 = xs:int(mid range).
   public void test_op_numeric_greater_thanint2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanint2args-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanint2args-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:int(lower bound) $arg2 = xs:int(upper bound).
   public void test_op_numeric_greater_thanint2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanint2args-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanint2args-5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:integer(lower bound) $arg2 = xs:integer(lower bound).
   public void test_op_numeric_greater_thanintg2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanintg2args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanintg2args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:integer(mid range) $arg2 = xs:integer(lower bound).
   public void test_op_numeric_greater_thanintg2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanintg2args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanintg2args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:integer(upper bound) $arg2 = xs:integer(lower bound).
   public void test_op_numeric_greater_thanintg2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanintg2args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanintg2args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:integer(lower bound) $arg2 = xs:integer(mid range).
   public void test_op_numeric_greater_thanintg2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanintg2args-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanintg2args-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:integer(lower bound) $arg2 = xs:integer(upper bound).
   public void test_op_numeric_greater_thanintg2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanintg2args-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanintg2args-5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:decimal(lower bound) $arg2 = xs:decimal(lower bound).
   public void test_op_numeric_greater_thandec2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thandec2args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thandec2args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:decimal(mid range) $arg2 = xs:decimal(lower bound).
   public void test_op_numeric_greater_thandec2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thandec2args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thandec2args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:decimal(upper bound) $arg2 = xs:decimal(lower bound).
   public void test_op_numeric_greater_thandec2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thandec2args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thandec2args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:decimal(lower bound) $arg2 = xs:decimal(mid range).
   public void test_op_numeric_greater_thandec2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thandec2args-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thandec2args-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:decimal(lower bound) $arg2 = xs:decimal(upper bound).
   public void test_op_numeric_greater_thandec2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thandec2args-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thandec2args-5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:double(lower bound) $arg2 = xs:double(lower bound).
   public void test_op_numeric_greater_thandbl2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thandbl2args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thandbl2args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:double(mid range) $arg2 = xs:double(lower bound).
   public void test_op_numeric_greater_thandbl2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thandbl2args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thandbl2args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:double(upper bound) $arg2 = xs:double(lower bound).
   public void test_op_numeric_greater_thandbl2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thandbl2args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thandbl2args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:double(lower bound) $arg2 = xs:double(mid range).
   public void test_op_numeric_greater_thandbl2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thandbl2args-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thandbl2args-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:double(lower bound) $arg2 = xs:double(upper bound).
   public void test_op_numeric_greater_thandbl2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thandbl2args-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thandbl2args-5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:float(lower bound) $arg2 = xs:float(lower bound).
   public void test_op_numeric_greater_thanflt2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanflt2args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanflt2args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:float(mid range) $arg2 = xs:float(lower bound).
   public void test_op_numeric_greater_thanflt2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanflt2args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanflt2args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:float(upper bound) $arg2 = xs:float(lower bound).
   public void test_op_numeric_greater_thanflt2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanflt2args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanflt2args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:float(lower bound) $arg2 = xs:float(mid range).
   public void test_op_numeric_greater_thanflt2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanflt2args-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanflt2args-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:float(lower bound) $arg2 = xs:float(upper bound).
   public void test_op_numeric_greater_thanflt2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanflt2args-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanflt2args-5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:long(lower bound) $arg2 = xs:long(lower bound).
   public void test_op_numeric_greater_thanlng2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanlng2args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanlng2args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:long(mid range) $arg2 = xs:long(lower bound).
   public void test_op_numeric_greater_thanlng2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanlng2args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanlng2args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:long(upper bound) $arg2 = xs:long(lower bound).
   public void test_op_numeric_greater_thanlng2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanlng2args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanlng2args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:long(lower bound) $arg2 = xs:long(mid range).
   public void test_op_numeric_greater_thanlng2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanlng2args-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanlng2args-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:long(lower bound) $arg2 = xs:long(upper bound).
   public void test_op_numeric_greater_thanlng2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanlng2args-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanlng2args-5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:unsignedShort(lower bound) $arg2 = xs:unsignedShort(lower bound).
   public void test_op_numeric_greater_thanusht2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanusht2args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanusht2args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:unsignedShort(mid range) $arg2 = xs:unsignedShort(lower bound).
   public void test_op_numeric_greater_thanusht2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanusht2args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanusht2args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:unsignedShort(upper bound) $arg2 = xs:unsignedShort(lower bound).
   public void test_op_numeric_greater_thanusht2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanusht2args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanusht2args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:unsignedShort(lower bound) $arg2 = xs:unsignedShort(mid range).
   public void test_op_numeric_greater_thanusht2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanusht2args-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanusht2args-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:unsignedShort(lower bound) $arg2 = xs:unsignedShort(upper bound).
   public void test_op_numeric_greater_thanusht2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanusht2args-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanusht2args-5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:negativeInteger(lower bound) $arg2 = xs:negativeInteger(lower bound).
   public void test_op_numeric_greater_thannint2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thannint2args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thannint2args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:negativeInteger(mid range) $arg2 = xs:negativeInteger(lower bound).
   public void test_op_numeric_greater_thannint2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thannint2args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thannint2args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:negativeInteger(upper bound) $arg2 = xs:negativeInteger(lower bound).
   public void test_op_numeric_greater_thannint2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thannint2args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thannint2args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:negativeInteger(lower bound) $arg2 = xs:negativeInteger(mid range).
   public void test_op_numeric_greater_thannint2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thannint2args-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thannint2args-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:negativeInteger(lower bound) $arg2 = xs:negativeInteger(upper bound).
   public void test_op_numeric_greater_thannint2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thannint2args-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thannint2args-5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:positiveInteger(lower bound) $arg2 = xs:positiveInteger(lower bound).
   public void test_op_numeric_greater_thanpint2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanpint2args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanpint2args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:positiveInteger(mid range) $arg2 = xs:positiveInteger(lower bound).
   public void test_op_numeric_greater_thanpint2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanpint2args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanpint2args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:positiveInteger(upper bound) $arg2 = xs:positiveInteger(lower bound).
   public void test_op_numeric_greater_thanpint2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanpint2args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanpint2args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:positiveInteger(lower bound) $arg2 = xs:positiveInteger(mid range).
   public void test_op_numeric_greater_thanpint2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanpint2args-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanpint2args-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:positiveInteger(lower bound) $arg2 = xs:positiveInteger(upper bound).
   public void test_op_numeric_greater_thanpint2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanpint2args-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanpint2args-5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:unsignedLong(lower bound) $arg2 = xs:unsignedLong(lower bound).
   public void test_op_numeric_greater_thanulng2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanulng2args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanulng2args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:unsignedLong(mid range) $arg2 = xs:unsignedLong(lower bound).
   public void test_op_numeric_greater_thanulng2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanulng2args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanulng2args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:unsignedLong(upper bound) $arg2 = xs:unsignedLong(lower bound).
   public void test_op_numeric_greater_thanulng2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanulng2args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanulng2args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:unsignedLong(lower bound) $arg2 = xs:unsignedLong(mid range).
   public void test_op_numeric_greater_thanulng2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanulng2args-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanulng2args-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:unsignedLong(lower bound) $arg2 = xs:unsignedLong(upper bound).
   public void test_op_numeric_greater_thanulng2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanulng2args-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thanulng2args-5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:nonPositiveInteger(lower bound) $arg2 = xs:nonPositiveInteger(lower bound).
   public void test_op_numeric_greater_thannpi2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thannpi2args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thannpi2args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:nonPositiveInteger(mid range) $arg2 = xs:nonPositiveInteger(lower bound).
   public void test_op_numeric_greater_thannpi2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thannpi2args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thannpi2args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:nonPositiveInteger(upper bound) $arg2 = xs:nonPositiveInteger(lower bound).
   public void test_op_numeric_greater_thannpi2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thannpi2args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thannpi2args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:nonPositiveInteger(lower bound) $arg2 = xs:nonPositiveInteger(mid range).
   public void test_op_numeric_greater_thannpi2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thannpi2args-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thannpi2args-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:nonPositiveInteger(lower bound) $arg2 = xs:nonPositiveInteger(upper bound).
   public void test_op_numeric_greater_thannpi2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thannpi2args-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thannpi2args-5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:nonNegativeInteger(lower bound) $arg2 = xs:nonNegativeInteger(lower bound).
   public void test_op_numeric_greater_thannni2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thannni2args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thannni2args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:nonNegativeInteger(mid range) $arg2 = xs:nonNegativeInteger(lower bound).
   public void test_op_numeric_greater_thannni2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thannni2args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thannni2args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:nonNegativeInteger(upper bound) $arg2 = xs:nonNegativeInteger(lower bound).
   public void test_op_numeric_greater_thannni2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thannni2args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thannni2args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:nonNegativeInteger(lower bound) $arg2 = xs:nonNegativeInteger(mid range).
   public void test_op_numeric_greater_thannni2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thannni2args-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thannni2args-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:nonNegativeInteger(lower bound) $arg2 = xs:nonNegativeInteger(upper bound).
   public void test_op_numeric_greater_thannni2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thannni2args-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thannni2args-5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:short(lower bound) $arg2 = xs:short(lower bound).
   public void test_op_numeric_greater_thansht2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thansht2args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thansht2args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:short(mid range) $arg2 = xs:short(lower bound).
   public void test_op_numeric_greater_thansht2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thansht2args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thansht2args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:short(upper bound) $arg2 = xs:short(lower bound).
   public void test_op_numeric_greater_thansht2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thansht2args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thansht2args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:short(lower bound) $arg2 = xs:short(mid range).
   public void test_op_numeric_greater_thansht2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thansht2args-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thansht2args-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-greater-than" operator with the arguments set as follows: $arg1 = xs:short(lower bound) $arg2 = xs:short(upper bound).
   public void test_op_numeric_greater_thansht2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thansht2args-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/ValComp/NumericComp/NumericGT/op-numeric-greater-thansht2args-5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
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
      