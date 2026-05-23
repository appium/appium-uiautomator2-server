
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
      
      
public class GenCompGTTest extends AbstractPsychoPathTest {

   //Evaluation of a General expression with the operands/operator set with the following format: Empty sequence > Empty sequence.
   public void test_generalexpression397() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression397.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression397.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Empty sequence > Atomic Value.
   public void test_generalexpression398() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression398.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression398.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Empty sequence > Sequence of single atomic value.
   public void test_generalexpression399() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression399.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression399.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Empty sequence > Sequence of single atomic values.
   public void test_generalexpression400() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression400.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression400.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Empty sequence > Sequence of single element nodes.
   public void test_generalexpression404() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression404.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression404.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Empty sequence > Sequence of multiple element nodes (single source).
   public void test_generalexpression405() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression405.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression405.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Empty sequence > Sequence of multiple element nodes (multiple sources).
   public void test_generalexpression406() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression406.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression406.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Atomic Value > Empty sequence.
   public void test_generalexpression407() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression407.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression407.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Atomic Value > Sequence of single atomic value.
   public void test_generalexpression408() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression408.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression408.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Atomic Value > Sequence of single atomic values.
   public void test_generalexpression409() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression409.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression409.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Atomic Value > Sequence of single element nodes.
   public void test_generalexpression413() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression413.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression413.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Atomic Value > Sequence of multiple element nodes (single source).
   public void test_generalexpression414() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression414.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression414.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Atomic Value > Sequence of multiple element nodes (multiple sources).
   public void test_generalexpression415() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression415.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression415.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single atomic value > Empty sequence.
   public void test_generalexpression416() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression416.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression416.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single atomic value > Atomic Value.
   public void test_generalexpression417() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression417.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression417.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single atomic value > Sequence of single atomic value.
   public void test_generalexpression418() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression418.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression418.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single atomic value > Sequence of single atomic values.
   public void test_generalexpression419() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression419.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression419.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single atomic value > Sequence of single element nodes.
   public void test_generalexpression423() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression423.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression423.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single atomic value > Sequence of multiple element nodes (single source).
   public void test_generalexpression424() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression424.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression424.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single atomic value > Sequence of multiple element nodes (multiple sources).
   public void test_generalexpression425() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression425.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression425.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single atomic values > Empty sequence.
   public void test_generalexpression426() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression426.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression426.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single atomic values > Atomic Value.
   public void test_generalexpression427() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression427.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression427.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single atomic values > Sequence of single atomic value.
   public void test_generalexpression428() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression428.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression428.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single atomic values > Sequence of single atomic values.
   public void test_generalexpression429() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression429.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression429.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single atomic values > Sequence of single element nodes.
   public void test_generalexpression433() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression433.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression433.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single atomic values > Sequence of multiple element nodes (single source).
   public void test_generalexpression434() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression434.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression434.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single atomic values > Sequence of multiple element nodes (multiple sources).
   public void test_generalexpression435() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression435.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression435.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single element nodes > Empty sequence.
   public void test_generalexpression466() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression466.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression466.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single element nodes > Atomic Value.
   public void test_generalexpression467() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression467.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression467.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single element nodes > Sequence of single atomic value.
   public void test_generalexpression468() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression468.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression468.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single element nodes > Sequence of single atomic values.
   public void test_generalexpression469() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression469.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression469.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single element nodes > Sequence of single element nodes.
   public void test_generalexpression473() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression473.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression473.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single element nodes > Sequence of multiple element nodes (single source).
   public void test_generalexpression474() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression474.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression474.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of single element nodes > Sequence of multiple element nodes (multiple sources).
   public void test_generalexpression475() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression475.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression475.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of multiple element nodes (single source) > Empty sequence.
   public void test_generalexpression476() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression476.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression476.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of multiple element nodes (single source) > Atomic Value.
   public void test_generalexpression477() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression477.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression477.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of multiple element nodes (single source) > Sequence of single atomic value.
   public void test_generalexpression478() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression478.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression478.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of multiple element nodes (single source) > Sequence of single atomic values.
   public void test_generalexpression479() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression479.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression479.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of multiple element nodes (single source) > Sequence of single element nodes.
   public void test_generalexpression483() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression483.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression483.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of multiple element nodes (single source) > Sequence of multiple element nodes (single source).
   public void test_generalexpression484() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression484.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression484.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of multiple element nodes (single source) > Sequence of multiple element nodes (multiple sources).
   public void test_generalexpression485() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression485.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression485.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of multiple element nodes (multiple sources) > Empty sequence.
   public void test_generalexpression486() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression486.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression486.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of multiple element nodes (multiple sources) > Atomic Value.
   public void test_generalexpression487() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression487.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression487.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of multiple element nodes (multiple sources) > Sequence of single atomic value.
   public void test_generalexpression488() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression488.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression488.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of multiple element nodes (multiple sources) > Sequence of single atomic values.
   public void test_generalexpression489() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression489.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression489.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of multiple element nodes (multiple sources) > Sequence of single element nodes.
   public void test_generalexpression493() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression493.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression493.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a General expression with the operands/operator set with the following format: Sequence of multiple element nodes (multiple sources) > Sequence of multiple element nodes (single source).
   public void test_generalexpression494() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression494.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/generalexpression494.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of "gt" operator on xs:anyURI data type.
   public void test_greaterthanonanyuri_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/greaterthanonanyuri-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/CompExpr/GenComprsn/GenCompGT/false.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
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
      