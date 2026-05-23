
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
package org.eclipse.wst.xml.xpath2.processor.testsuite.core;

import java.net.URL;

import org.apache.xerces.xs.XSModel;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.StaticError;
import org.eclipse.wst.xml.xpath2.processor.XPathParserException;
import org.eclipse.wst.xml.xpath2.processor.test.AbstractPsychoPathTest;
      
      
public class GenCompLTTest extends AbstractPsychoPathTest {

   //Evaluation of a General expression with the operands/operator set with the following format: Empty sequence < Empty sequence.
   public void test_generalexpression199() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression199.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression199.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Empty sequence < Atomic Value.
   public void test_generalexpression200() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression200.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression200.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Empty sequence < Sequence of single atomic value.
   public void test_generalexpression201() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression201.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression201.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Empty sequence < Sequence of single atomic values.
   public void test_generalexpression202() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression202.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression202.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Empty sequence < Sequence of single element nodes.
   public void test_generalexpression206() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression206.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression206.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Empty sequence < Sequence of multiple element nodes (single source).
   public void test_generalexpression207() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression207.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression207.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Empty sequence < Sequence of multiple element nodes (multiple sources).
   public void test_generalexpression208() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression208.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression208.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Atomic Value < Empty sequence.
   public void test_generalexpression209() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression209.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression209.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Atomic Value < Sequence of single atomic value.
   public void test_generalexpression210() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression210.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression210.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Atomic Value < Sequence of single atomic values.
   public void test_generalexpression211() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression211.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression211.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Atomic Value < Sequence of single element nodes.
   public void test_generalexpression215() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression215.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression215.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Atomic Value < Sequence of multiple element nodes (single source).
   public void test_generalexpression216() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression216.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression216.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Atomic Value < Sequence of multiple element nodes (multiple sources).
   public void test_generalexpression217() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression217.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression217.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single atomic value < Empty sequence.
   public void test_generalexpression218() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression218.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression218.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single atomic value < Atomic Value.
   public void test_generalexpression219() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression219.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression219.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single atomic value < Sequence of single atomic value.
   public void test_generalexpression220() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression220.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression220.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single atomic value < Sequence of single atomic values.
   public void test_generalexpression221() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression221.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression221.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single atomic value < Sequence of single element nodes.
   public void test_generalexpression225() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression225.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression225.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single atomic value < Sequence of multiple element nodes (single source).
   public void test_generalexpression226() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression226.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression226.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single atomic value < Sequence of multiple element nodes (multiple sources).
   public void test_generalexpression227() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression227.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression227.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single atomic values < Empty sequence.
   public void test_generalexpression228() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression228.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression228.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single atomic values < Atomic Value.
   public void test_generalexpression229() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression229.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression229.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single atomic values < Sequence of single atomic value.
   public void test_generalexpression230() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression230.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression230.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single atomic values < Sequence of single atomic values.
   public void test_generalexpression231() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression231.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression231.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single atomic values < Sequence of single element nodes.
   public void test_generalexpression235() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression235.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression235.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single atomic values < Sequence of multiple element nodes (single source).
   public void test_generalexpression236() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression236.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression236.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single atomic values < Sequence of multiple element nodes (multiple sources).
   public void test_generalexpression237() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression237.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression237.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single element nodes < Empty sequence.
   public void test_generalexpression268() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression268.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression268.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single element nodes < Atomic Value.
   public void test_generalexpression269() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression269.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression269.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single element nodes < Sequence of single atomic value.
   public void test_generalexpression270() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression270.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression270.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single element nodes < Sequence of single atomic values.
   public void test_generalexpression271() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression271.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression271.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single element nodes < Sequence of single element nodes.
   public void test_generalexpression275() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression275.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression275.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single element nodes < Sequence of multiple element nodes (single source).
   public void test_generalexpression276() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression276.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression276.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single element nodes < Sequence of multiple element nodes (multiple sources).
   public void test_generalexpression277() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression277.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression277.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of multiple element nodes (single source) < Empty sequence.
   public void test_generalexpression278() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression278.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression278.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of multiple element nodes (single source) < Atomic Value.
   public void test_generalexpression279() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression279.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression279.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of multiple element nodes (single source) < Sequence of single atomic value.
   public void test_generalexpression280() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression280.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression280.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of multiple element nodes (single source) < Sequence of single atomic values.
   public void test_generalexpression281() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression281.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression281.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of multiple element nodes (single source) < Sequence of single element nodes.
   public void test_generalexpression285() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression285.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression285.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of multiple element nodes (single source) < Sequence of multiple element nodes (single source).
   public void test_generalexpression286() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression286.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression286.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of multiple element nodes (single source) < Sequence of multiple element nodes (multiple sources).
   public void test_generalexpression287() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression287.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression287.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of multiple element nodes (multiple sources) < Empty sequence.
   public void test_generalexpression288() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression288.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression288.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of multiple element nodes (multiple sources) < Atomic Value.
   public void test_generalexpression289() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression289.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression289.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of multiple element nodes (multiple sources) < Sequence of single atomic value.
   public void test_generalexpression290() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression290.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression290.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of multiple element nodes (multiple sources) < Sequence of single atomic values.
   public void test_generalexpression291() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression291.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression291.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of multiple element nodes (multiple sources) < Sequence of single element nodes.
   public void test_generalexpression295() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression295.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression295.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of multiple element nodes (multiple sources) < Sequence of multiple element nodes (single source).
   public void test_generalexpression296() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression296.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression296.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of multiple element nodes (multiple sources) < Sequence of multiple element nodes (multiple sources).
   public void test_generalexpression297() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression297.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/generalexpression297.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of "lt" operator on an xs:anyURI datatype.
   public void test_lessthanonanyuri_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/lessthanonanyuri-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompLT/false.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
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
      