
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
      
      
public class NumericMultiplyTest extends AbstractPsychoPathTest {

   //Evaluates the "op:numeric-multiply" operator with the arguments set as follows: $arg1 = xs:decimal(lower bound) $arg2 = xs:decimal(lower bound).
   public void test_op_numeric_multiplydec2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplydec2args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplydec2args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-multiply" operator with the arguments set as follows: $arg1 = xs:decimal(mid range) $arg2 = xs:decimal(lower bound).
   public void test_op_numeric_multiplydec2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplydec2args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplydec2args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-multiply" operator with the arguments set as follows: $arg1 = xs:decimal(upper bound) $arg2 = xs:decimal(lower bound).
   public void test_op_numeric_multiplydec2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplydec2args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplydec2args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-multiply" operator with the arguments set as follows: $arg1 = xs:decimal(lower bound) $arg2 = xs:decimal(mid range).
   public void test_op_numeric_multiplydec2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplydec2args-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplydec2args-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-multiply" operator with the arguments set as follows: $arg1 = xs:decimal(lower bound) $arg2 = xs:decimal(upper bound).
   public void test_op_numeric_multiplydec2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplydec2args-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplydec2args-5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-multiply" operator with the arguments set as follows: $arg1 = xs:double(lower bound) $arg2 = xs:double(lower bound).
   public void test_op_numeric_multiplydbl2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplydbl2args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplydbl2args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-multiply" operator with the arguments set as follows: $arg1 = xs:double(mid range) $arg2 = xs:double(lower bound).
   public void test_op_numeric_multiplydbl2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplydbl2args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplydbl2args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-multiply" operator with the arguments set as follows: $arg1 = xs:double(upper bound) $arg2 = xs:double(lower bound).
   public void test_op_numeric_multiplydbl2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplydbl2args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplydbl2args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-multiply" operator with the arguments set as follows: $arg1 = xs:double(lower bound) $arg2 = xs:double(mid range).
   public void test_op_numeric_multiplydbl2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplydbl2args-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplydbl2args-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-multiply" operator with the arguments set as follows: $arg1 = xs:double(lower bound) $arg2 = xs:double(upper bound).
   public void test_op_numeric_multiplydbl2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplydbl2args-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplydbl2args-5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-multiply" operator with the arguments set as follows: $arg1 = xs:float(lower bound) $arg2 = xs:float(lower bound).
   public void test_op_numeric_multiplyflt2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplyflt2args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplyflt2args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-multiply" operator with the arguments set as follows: $arg1 = xs:float(mid range) $arg2 = xs:float(lower bound).
   public void test_op_numeric_multiplyflt2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplyflt2args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplyflt2args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-multiply" operator with the arguments set as follows: $arg1 = xs:float(upper bound) $arg2 = xs:float(lower bound).
   public void test_op_numeric_multiplyflt2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplyflt2args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplyflt2args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-multiply" operator with the arguments set as follows: $arg1 = xs:float(lower bound) $arg2 = xs:float(mid range).
   public void test_op_numeric_multiplyflt2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplyflt2args-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplyflt2args-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-multiply" operator with the arguments set as follows: $arg1 = xs:float(lower bound) $arg2 = xs:float(upper bound).
   public void test_op_numeric_multiplyflt2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplyflt2args-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplyflt2args-5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-multiply" operator with the arguments set as follows: $arg1 = xs:unsignedShort(lower bound) $arg2 = xs:unsignedShort(lower bound).
   public void test_op_numeric_multiplyusht2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplyusht2args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplyusht2args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-multiply" operator with the arguments set as follows: $arg1 = xs:unsignedShort(mid range) $arg2 = xs:unsignedShort(lower bound).
   public void test_op_numeric_multiplyusht2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplyusht2args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplyusht2args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-multiply" operator with the arguments set as follows: $arg1 = xs:unsignedShort(upper bound) $arg2 = xs:unsignedShort(lower bound).
   public void test_op_numeric_multiplyusht2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplyusht2args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplyusht2args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-multiply" operator with the arguments set as follows: $arg1 = xs:unsignedShort(lower bound) $arg2 = xs:unsignedShort(mid range).
   public void test_op_numeric_multiplyusht2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplyusht2args-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplyusht2args-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-multiply" operator with the arguments set as follows: $arg1 = xs:unsignedShort(lower bound) $arg2 = xs:unsignedShort(upper bound).
   public void test_op_numeric_multiplyusht2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplyusht2args-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplyusht2args-5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-multiply" operator with the arguments set as follows: $arg1 = xs:positiveInteger(lower bound) $arg2 = xs:positiveInteger(lower bound).
   public void test_op_numeric_multiplypint2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplypint2args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplypint2args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-multiply" operator with the arguments set as follows: $arg1 = xs:positiveInteger(mid range) $arg2 = xs:positiveInteger(lower bound).
   public void test_op_numeric_multiplypint2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplypint2args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplypint2args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-multiply" operator with the arguments set as follows: $arg1 = xs:positiveInteger(upper bound) $arg2 = xs:positiveInteger(lower bound).
   public void test_op_numeric_multiplypint2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplypint2args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplypint2args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-multiply" operator with the arguments set as follows: $arg1 = xs:positiveInteger(lower bound) $arg2 = xs:positiveInteger(mid range).
   public void test_op_numeric_multiplypint2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplypint2args-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplypint2args-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-multiply" operator with the arguments set as follows: $arg1 = xs:positiveInteger(lower bound) $arg2 = xs:positiveInteger(upper bound).
   public void test_op_numeric_multiplypint2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplypint2args-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplypint2args-5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-multiply" operator with the arguments set as follows: $arg1 = xs:unsignedLong(lower bound) $arg2 = xs:unsignedLong(lower bound).
   public void test_op_numeric_multiplyulng2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplyulng2args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplyulng2args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-multiply" operator with the arguments set as follows: $arg1 = xs:unsignedLong(mid range) $arg2 = xs:unsignedLong(lower bound).
   public void test_op_numeric_multiplyulng2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplyulng2args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplyulng2args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-multiply" operator with the arguments set as follows: $arg1 = xs:unsignedLong(upper bound) $arg2 = xs:unsignedLong(lower bound).
   public void test_op_numeric_multiplyulng2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplyulng2args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplyulng2args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-multiply" operator with the arguments set as follows: $arg1 = xs:unsignedLong(lower bound) $arg2 = xs:unsignedLong(mid range).
   public void test_op_numeric_multiplyulng2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplyulng2args-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplyulng2args-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-multiply" operator with the arguments set as follows: $arg1 = xs:unsignedLong(lower bound) $arg2 = xs:unsignedLong(upper bound).
   public void test_op_numeric_multiplyulng2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplyulng2args-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplyulng2args-5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-multiply" operator with the arguments set as follows: $arg1 = xs:nonPositiveInteger(upper bound) $arg2 = xs:nonPositiveInteger(lower bound).
   public void test_op_numeric_multiplynpi2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplynpi2args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplynpi2args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-multiply" operator with the arguments set as follows: $arg1 = xs:nonPositiveInteger(lower bound) $arg2 = xs:nonPositiveInteger(upper bound).
   public void test_op_numeric_multiplynpi2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplynpi2args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplynpi2args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-multiply" operator with the arguments set as follows: $arg1 = xs:nonNegativeInteger(lower bound) $arg2 = xs:nonNegativeInteger(lower bound).
   public void test_op_numeric_multiplynni2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplynni2args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplynni2args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-multiply" operator with the arguments set as follows: $arg1 = xs:nonNegativeInteger(mid range) $arg2 = xs:nonNegativeInteger(lower bound).
   public void test_op_numeric_multiplynni2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplynni2args-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplynni2args-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-multiply" operator with the arguments set as follows: $arg1 = xs:nonNegativeInteger(upper bound) $arg2 = xs:nonNegativeInteger(lower bound).
   public void test_op_numeric_multiplynni2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplynni2args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplynni2args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-multiply" operator with the arguments set as follows: $arg1 = xs:nonNegativeInteger(lower bound) $arg2 = xs:nonNegativeInteger(mid range).
   public void test_op_numeric_multiplynni2args_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplynni2args-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplynni2args-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates the "op:numeric-multiply" operator with the arguments set as follows: $arg1 = xs:nonNegativeInteger(lower bound) $arg2 = xs:nonNegativeInteger(upper bound).
   public void test_op_numeric_multiplynni2args_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplynni2args-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplynni2args-5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Simple multiplication test with () as one operand should return null.
   public void test_op_numeric_multiplymix2args_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplymix2args-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplymix2args-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Simple multiplication test pass string for second operator.
   public void test_op_numeric_multiplymix2args_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplymix2args-2.xq";
      String expectedResult = "XPTY0004";
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Simple multiplication test, second operator cast string to integer.
   public void test_op_numeric_multiplymix2args_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplymix2args-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/NumericOpr/NumericMultiply/op-numeric-multiplymix2args-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
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
      