
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
      
      
public class AxesTest extends AbstractPsychoPathTest {

   //No children for child::*.
   public void test_Axes001_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes001.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes001-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //1 child for child::*.
   public void test_Axes001_2() throws Exception {
      String inputFile = "/TestSources/Tree1Child.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes001.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes001-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Several children for child::*.
   public void test_Axes001_3() throws Exception {
      String inputFile = "/TestSources/TreeRepeat.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes001.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes001-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //No children, child::name gets none.
   public void test_Axes002_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes002.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes002-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //child::name gets none, none of that name exist.
   public void test_Axes002_2() throws Exception {
      String inputFile = "/TestSources/Tree1Child.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes002.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes002-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //child::name gets 1 child.
   public void test_Axes002_3() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes002.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes002-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Several children for child::name.
   public void test_Axes002_4() throws Exception {
      String inputFile = "/TestSources/TreeRepeat.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes002.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes002-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //No children of any kind, child::node() gets none.
   public void test_Axes003_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes003.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes003-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //child::node() gets 1 text node.
   public void test_Axes003_2() throws Exception {
      String inputFile = "/TestSources/Tree1Text.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes003.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes003-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //child::node() gets 1 child element.
   public void test_Axes003_3() throws Exception {
      String inputFile = "/TestSources/Tree1Child.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes003.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes003-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Several nodes for child::node().
   public void test_Axes003_4() throws Exception {
      String inputFile = "/TestSources/TreeRepeat.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes003.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes003-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //No children for *.
   public void test_Axes004_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes004.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes004-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //1 child for *.
   public void test_Axes004_2() throws Exception {
      String inputFile = "/TestSources/Tree1Child.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes004.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes004-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Several children for *.
   public void test_Axes004_3() throws Exception {
      String inputFile = "/TestSources/TreeRepeat.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes004.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes004-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //No children, implied child with name gets none.
   public void test_Axes005_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes005.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes005-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Implied child with name gets none, none of that name exist.
   public void test_Axes005_2() throws Exception {
      String inputFile = "/TestSources/Tree1Child.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes005.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes005-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Implied child with name gets 1 child.
   public void test_Axes005_3() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes005.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes005-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Several children for implied child with name.
   public void test_Axes005_4() throws Exception {
      String inputFile = "/TestSources/TreeRepeat.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes005.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes005-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //No children, implied child with node() gets none.
   public void test_Axes006_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes006.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes006-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Implied child with node() gets 1 text node.
   public void test_Axes006_2() throws Exception {
      String inputFile = "/TestSources/Tree1Text.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes006.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes006-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Implied child with node() gets 1 child element.
   public void test_Axes006_3() throws Exception {
      String inputFile = "/TestSources/Tree1Child.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes006.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes006-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Several nodes for implied child with node().
   public void test_Axes006_4() throws Exception {
      String inputFile = "/TestSources/TreeRepeat.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes006.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes006-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //No attributes for attribute::*.
   public void test_Axes007_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes007.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes007-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //1 attribute for attribute::*.
   public void test_Axes007_2() throws Exception {
      String inputFile = "/TestSources/Tree1Child.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes007.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes007-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Several attributes for attribute::*.
   public void test_Axes007_3() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes007.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes007-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //No attributes, attribute::name gets none.
   public void test_Axes008_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes008.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes008-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //No attribute satisfies attribute::name.
   public void test_Axes008_2() throws Exception {
      String inputFile = "/TestSources/Tree1Child.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes008.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes008-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //1 attribute for attribute::name.
   public void test_Axes008_3() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes008.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes008-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //No attributes for attribute::node().
   public void test_Axes009_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes009.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes009-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //1 attribute for attribute::node().
   public void test_Axes009_2() throws Exception {
      String inputFile = "/TestSources/Tree1Child.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes009.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes009-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Several attributes for attribute::node().
   public void test_Axes009_3() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes009.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes009-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //No attributes for @*.
   public void test_Axes010_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes010.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes010-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //1 attribute for @*.
   public void test_Axes010_2() throws Exception {
      String inputFile = "/TestSources/Tree1Child.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes010.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes010-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Several attributes for @*.
   public void test_Axes010_3() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes010.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes010-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //No attributes, @name gets none.
   public void test_Axes011_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes011.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes011-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //No attribute satisfies @name.
   public void test_Axes011_2() throws Exception {
      String inputFile = "/TestSources/Tree1Child.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes011.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes011-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //1 attribute for @name.
   public void test_Axes011_3() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes011.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes011-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Get root node from / alone.
   public void test_Axes012_1() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes012.xq";
      String expectedResult = "XPST0003";
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Get 1 element from parent::*.
   public void test_Axes013_1() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes013.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes013-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Get no nodes from parent::* at top of tree.
   public void test_Axes014_1() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes014.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes014-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Get 1 element from parent::name.
   public void test_Axes015_1() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes015.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes015-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Get no nodes from parent::name when name is not parent's name.
   public void test_Axes016_1() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes016.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes016-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Get 1 element from parent::node().
   public void test_Axes017_1() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes017.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes017-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Get root node from parent::node() at top of tree.
   public void test_Axes018_1() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes018.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes018-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Get 1 element from.
   public void test_Axes019_1() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes019.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes019-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Get 1 element from self::*.
   public void test_Axes020_1() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes020.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes020-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Get 1 element from self::name.
   public void test_Axes021_1() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes021.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes021-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Get 1 element from self::node().
   public void test_Axes023_1() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes023.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes023-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Get 1 attribute from self::node().
   public void test_Axes027_1() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes027.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes027-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //text()/self::node() gets no nodes when no such text node exists.
   public void test_Axes030_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes030.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes030-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Get 1 text node from self::node().
   public void test_Axes030_2() throws Exception {
      String inputFile = "/TestSources/Tree1Text.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes030.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes030-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //No subtree, descendant::* gets none.
   public void test_Axes031_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes031.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes031-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //descendant::* gets none when there are no element children.
   public void test_Axes031_2() throws Exception {
      String inputFile = "/TestSources/Tree1Text.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes031.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes031-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //descendant::* gets the 1 child.
   public void test_Axes031_3() throws Exception {
      String inputFile = "/TestSources/Tree1Child.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes031.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes031-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Several descendant elements found by descendant::*.
   public void test_Axes031_4() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes031.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes031-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //No subtree, descendant::name gets nothing.
   public void test_Axes032_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes032.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes032-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //descendant::name gets none when no elements have that name.
   public void test_Axes032_2() throws Exception {
      String inputFile = "/TestSources/Tree1Child.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes032.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes032-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //descendant::name gets the 1 child.
   public void test_Axes032_3() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes032.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes032-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Several children for descendant::name.
   public void test_Axes032_4() throws Exception {
      String inputFile = "/TestSources/TreeStack.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes032.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes032-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //No subtree, descendant::node() gets none.
   public void test_Axes033_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes033.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes033-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //descendant::node() gets 1 text child.
   public void test_Axes033_2() throws Exception {
      String inputFile = "/TestSources/Tree1Text.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes033.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes033-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //descendant::node() gets 1 element child.
   public void test_Axes033_3() throws Exception {
      String inputFile = "/TestSources/Tree1Child.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes033.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes033-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Several descendant elements found by descendant::node().
   public void test_Axes033_4() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes033.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes033-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //descendant-or-self::* from an element gets 1 element (self).
   public void test_Axes034_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes034.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes034-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //descendant-or-self::* from an element gets several elements.
   public void test_Axes034_2() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes034.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes034-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //descendant-or-self::name gets nothing when no sub-tree and self has different name.
   public void test_Axes035_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes035.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes035-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //descendant-or-self::name gets none when no elements have that name.
   public void test_Axes035_2() throws Exception {
      String inputFile = "/TestSources/Tree1Child.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes035.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes035-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //descendant-or-self::name gets the 1 descendant of that name.
   public void test_Axes035_3() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes035.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes035-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //descendant-or-self::name gets several elements.
   public void test_Axes035_4() throws Exception {
      String inputFile = "/TestSources/TreeStack.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes035.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes035-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //descendant-or-self::name gets self of that name.
   public void test_Axes036_1() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes036.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes036-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //descendant-or-self::name gets several elements, including self.
   public void test_Axes036_2() throws Exception {
      String inputFile = "/TestSources/TreeRepeat.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes036.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes036-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //No descendants, descendant-or-self::node() gets self.
   public void test_Axes037_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes037.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes037-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //descendant-or-self::node() gets several nodes.
   public void test_Axes037_2() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes037.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes037-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //descendant-or-self::node() from an attribute gets the attribute.
   public void test_Axes041_1() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes041.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes041-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //text()/descendant-or-self::node() gets no nodes when no such text node exists.
   public void test_Axes043_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes043.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes043-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //descendant-or-self::node() from a text node gets that node.
   public void test_Axes043_2() throws Exception {
      String inputFile = "/TestSources/Tree1Text.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes043.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes043-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ///child::* on typical tree gets just 1 (document) element.
   public void test_Axes044_1() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes044.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes044-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ///child::* on tree with top-level nodes gets just 1 (document) element.
   public void test_Axes044_2() throws Exception {
      String inputFile = "/TestSources/TopMany.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes044.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes044-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ///child::name on tree with top-level non-element nodes gets nothing.
   public void test_Axes045_1() throws Exception {
      String inputFile = "/TestSources/TreeEmpty.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes045.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes045-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ///child::name on typical tree gets 1 (document) element.
   public void test_Axes045_2() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes045.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes045-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ///child::node() on typical tree gets 1 (document) element.
   public void test_Axes046_1() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes046.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes046-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ///child::node() on tree with top-level nodes gets many nodes.
   public void test_Axes046_2() throws Exception {
      String inputFile = "/TestSources/TopMany.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes046.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes046-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ///* on typical tree gets just 1 (document) element.
   public void test_Axes047_1() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes047.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes047-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ///* on tree with top-level nodes gets just 1 (document) element.
   public void test_Axes047_2() throws Exception {
      String inputFile = "/TestSources/TopMany.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes047.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes047-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ///name on tree with top-level non-element nodes gets nothing.
   public void test_Axes048_1() throws Exception {
      String inputFile = "/TestSources/TreeEmpty.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes048.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes048-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ///name on typical tree gets 1 (document) element.
   public void test_Axes048_2() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes048.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes048-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ///node() on typical tree gets 1 (document) element.
   public void test_Axes049_1() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes049.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes049-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ///node() on tree with top-level nodes gets many nodes.
   public void test_Axes049_2() throws Exception {
      String inputFile = "/TestSources/TopMany.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes049.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes049-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ///self::node() gets root node.
   public void test_Axes055_1() throws Exception {
      String inputFile = "/TestSources/TopMany.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes055.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes055-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ///descendant::* gets just the document element from a 1-element tree.
   public void test_Axes056_1() throws Exception {
      String inputFile = "/TestSources/TreeEmpty.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes056.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes056-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ///descendant::* gets many nodes from a typical tree.
   public void test_Axes056_2() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes056.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes056-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ///descendant::* gets many nodes from a tree with top-level nodes.
   public void test_Axes056_3() throws Exception {
      String inputFile = "/TestSources/TopMany.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes056.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes056-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ///descendant::name gets nothing when no element of that name.
   public void test_Axes057_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes057.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes057-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ///descendant::name gets 1 top-level element of that name.
   public void test_Axes057_2() throws Exception {
      String inputFile = "/TestSources/TreeEmpty.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes057.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes057-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ///descendant::name gets the 1 descendant of that name.
   public void test_Axes057_3() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes057.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes057-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ///descendant::name gets several elements.
   public void test_Axes057_4() throws Exception {
      String inputFile = "/TestSources/TreeStack.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes057.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes057-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ///descendant::node() gets 1 top-level element.
   public void test_Axes058_1() throws Exception {
      String inputFile = "/TestSources/TreeEmpty.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes058.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes058-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ///descendant::node() on typical tree gets many nodes.
   public void test_Axes058_2() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes058.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes058-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ///descendant::node() gets many nodes from a tree with top-level nodes.
   public void test_Axes058_3() throws Exception {
      String inputFile = "/TestSources/TopMany.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes058.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes058-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ///descendant-or-self::* gets 1 element from a 1-element tree.
   public void test_Axes059_1() throws Exception {
      String inputFile = "/TestSources/TreeEmpty.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes059.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes059-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ///descendant-or-self::* on typical tree gets many nodes.
   public void test_Axes059_2() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes059.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes059-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ///descendant-or-self::name gets nothing when no element of that name.
   public void test_Axes060_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes060.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes060-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ///descendant-or-self::name gets 1 top-level element of that name.
   public void test_Axes060_2() throws Exception {
      String inputFile = "/TestSources/TreeEmpty.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes060.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes060-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ///descendant-or-self::name gets the 1 descendant of that name.
   public void test_Axes060_3() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes060.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes060-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ///descendant-or-self::name gets several elements.
   public void test_Axes060_4() throws Exception {
      String inputFile = "/TestSources/TreeStack.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes060.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes060-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ///descendant-or-self::node() on typical tree gets many nodes.
   public void test_Axes061_1() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes061.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes061-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ///descendant-or-self::node() gets many nodes from a tree with top-level nodes.
   public void test_Axes061_2() throws Exception {
      String inputFile = "/TestSources/TopMany.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes061.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes061-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ////child::* gets 1 element from a 1-element tree.
   public void test_Axes062_1() throws Exception {
      String inputFile = "/TestSources/TreeEmpty.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes062.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes062-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ////child::* on typical tree gets many nodes.
   public void test_Axes062_2() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes062.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes062-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ////child::name gets nothing when no element of that name.
   public void test_Axes063_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes063.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes063-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ////child::name gets 1 top-level element of that name.
   public void test_Axes063_2() throws Exception {
      String inputFile = "/TestSources/TreeEmpty.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes063.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes063-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ////child::name gets the 1 descendant of that name.
   public void test_Axes063_3() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes063.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes063-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ////child::name gets several elements.
   public void test_Axes063_4() throws Exception {
      String inputFile = "/TestSources/TreeStack.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes063.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes063-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ////child::node() gets 1 element from a 1-element tree.
   public void test_Axes064_1() throws Exception {
      String inputFile = "/TestSources/TreeEmpty.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes064.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes064-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ////child::node() on typical tree gets many nodes.
   public void test_Axes064_2() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes064.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes064-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ////child::node() gets many nodes from a tree with top-level nodes.
   public void test_Axes064_3() throws Exception {
      String inputFile = "/TestSources/TopMany.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes064.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes064-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ////* gets 1 element from a 1-element tree.
   public void test_Axes065_1() throws Exception {
      String inputFile = "/TestSources/TreeEmpty.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes065.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes065-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ////* on typical tree gets many nodes.
   public void test_Axes065_2() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes065.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes065-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ////name gets nothing when no element of that name.
   public void test_Axes066_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes066.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes066-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ////name gets 1 top-level element of that name.
   public void test_Axes066_2() throws Exception {
      String inputFile = "/TestSources/TreeEmpty.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes066.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes066-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ////name gets the 1 descendant of that name.
   public void test_Axes066_3() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes066.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes066-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ////name gets several elements.
   public void test_Axes066_4() throws Exception {
      String inputFile = "/TestSources/TreeStack.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes066.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes066-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ////node() gets 1 element from a 1-element tree.
   public void test_Axes067_1() throws Exception {
      String inputFile = "/TestSources/TreeEmpty.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes067.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes067-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ////node() on typical tree gets many nodes.
   public void test_Axes067_2() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes067.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes067-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ////node() gets many nodes from a tree with top-level nodes.
   public void test_Axes067_3() throws Exception {
      String inputFile = "/TestSources/TopMany.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes067.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes067-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ////attribute::* gets nothing when no attributes.
   public void test_Axes068_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes068.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes068-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ////attribute::* gets 1 attribute from document element.
   public void test_Axes068_2() throws Exception {
      String inputFile = "/TestSources/TreeEmpty.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes068.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes068-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ////attribute::* gets many attributes.
   public void test_Axes068_3() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes068.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes068-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ////attribute::name gets nothing when no attributes.
   public void test_Axes069_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes069.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes069-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ////attribute::name gets 1 attribute from document element.
   public void test_Axes069_2() throws Exception {
      String inputFile = "/TestSources/TreeEmpty.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes069.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes069-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ////attribute::name gets many attributes.
   public void test_Axes069_3() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes069.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes069-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ////@* gets nothing when no attributes.
   public void test_Axes070_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes070.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes070-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ////@* gets 1 attribute from document element.
   public void test_Axes070_2() throws Exception {
      String inputFile = "/TestSources/TreeEmpty.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes070.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes070-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ////@* gets many attributes.
   public void test_Axes070_3() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes070.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes070-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ////@name gets nothing when no attributes.
   public void test_Axes071_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes071.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes071-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ////@name gets 1 attribute from document element.
   public void test_Axes071_2() throws Exception {
      String inputFile = "/TestSources/TreeEmpty.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes071.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes071-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ////@name gets many attributes.
   public void test_Axes071_3() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes071.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes071-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ////self::* gets 1 element from a 1-element tree.
   public void test_Axes072_1() throws Exception {
      String inputFile = "/TestSources/TreeEmpty.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes072.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes072-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ////self::* on typical tree gets many nodes.
   public void test_Axes072_2() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes072.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes072-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ////self::node() on typical tree gets many nodes.
   public void test_Axes073_1() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes073.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes073-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ////self::node() gets many nodes from a tree with top-level nodes.
   public void test_Axes073_2() throws Exception {
      String inputFile = "/TestSources/TopMany.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes073.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes073-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //No children of any kind, elem//child::* gets none.
   public void test_Axes074_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes074.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes074-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //elem//child::* gets nothing when no element children.
   public void test_Axes074_2() throws Exception {
      String inputFile = "/TestSources/Tree1Text.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes074.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes074-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //elem//child::* gets 1 child element.
   public void test_Axes074_3() throws Exception {
      String inputFile = "/TestSources/Tree1Child.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes074.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes074-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //elem//child::* gets several nodes.
   public void test_Axes074_4() throws Exception {
      String inputFile = "/TestSources/TreeRepeat.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes074.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes074-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //elem//child::name gets nothing when no sub-tree.
   public void test_Axes075_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes075.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes075-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //elem//child::name gets none when no elements have that name.
   public void test_Axes075_2() throws Exception {
      String inputFile = "/TestSources/Tree1Child.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes075.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes075-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //elem//child::name gets the 1 descendant of that name.
   public void test_Axes075_3() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes075.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes075-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //elem//child::name gets several elements.
   public void test_Axes075_4() throws Exception {
      String inputFile = "/TestSources/TreeStack.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes075.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes075-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //No children, elem//child::node() gets none.
   public void test_Axes076_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes076.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes076-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //elem//child::node() gets 1 child element.
   public void test_Axes076_2() throws Exception {
      String inputFile = "/TestSources/Tree1Child.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes076.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes076-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //elem//child::node() gets 1 child text node.
   public void test_Axes076_3() throws Exception {
      String inputFile = "/TestSources/Tree1Text.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes076.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes076-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Several children for elem//child::node().
   public void test_Axes076_4() throws Exception {
      String inputFile = "/TestSources/TreeRepeat.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes076.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes076-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //No children for elem//*.
   public void test_Axes077_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes077.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes077-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //1 child for elem//*.
   public void test_Axes077_2() throws Exception {
      String inputFile = "/TestSources/Tree1Child.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes077.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes077-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Many children for elem//*.
   public void test_Axes077_3() throws Exception {
      String inputFile = "/TestSources/TreeRepeat.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes077.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes077-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //elem//name gets none when there are no children.
   public void test_Axes078_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes078.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes078-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //elem//name gets none when no elements have that name.
   public void test_Axes078_2() throws Exception {
      String inputFile = "/TestSources/Tree1Child.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes078.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes078-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //elem//name gets the 1 descendant of that name.
   public void test_Axes078_3() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes078.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes078-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //elem//name gets several elements.
   public void test_Axes078_4() throws Exception {
      String inputFile = "/TestSources/TreeStack.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes078.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes078-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //No children, elem//node() gets none.
   public void test_Axes079_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes079.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes079-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //elem//node() gets 1 child element.
   public void test_Axes079_2() throws Exception {
      String inputFile = "/TestSources/Tree1Child.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes079.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes079-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //elem//node() gets 1 child text node.
   public void test_Axes079_3() throws Exception {
      String inputFile = "/TestSources/Tree1Text.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes079.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes079-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Several children for elem//node().
   public void test_Axes079_4() throws Exception {
      String inputFile = "/TestSources/TreeRepeat.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes079.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes079-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //No attributes for elem//attribute::*.
   public void test_Axes080_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes080.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes080-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //1 attribute for elem//attribute::*.
   public void test_Axes080_2() throws Exception {
      String inputFile = "/TestSources/Tree1Child.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes080.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes080-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Several attributes for elem//attribute::*.
   public void test_Axes080_3() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes080.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes080-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //No attributes for elem//attribute::name, due to none existing at all.
   public void test_Axes081_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes081.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes081-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //No attributes for elem//attribute::name, none of that name exist.
   public void test_Axes081_2() throws Exception {
      String inputFile = "/TestSources/Tree1Child.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes081.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes081-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //1 attribute for elem//attribute::name.
   public void test_Axes081_3() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes081.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes081-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Several attributes for elem//attribute::name.
   public void test_Axes081_4() throws Exception {
      String inputFile = "/TestSources/TreeRepeat.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes081.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes081-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //No attributes for elem//attribute::node().
   public void test_Axes082_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes082.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes082-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //1 attribute for elem//attribute::node().
   public void test_Axes082_2() throws Exception {
      String inputFile = "/TestSources/Tree1Child.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes082.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes082-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Several attributes for elem//attribute::node().
   public void test_Axes082_3() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes082.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes082-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //No attributes for elem//@*.
   public void test_Axes083_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes083.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes083-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //1 attribute for elem//@*.
   public void test_Axes083_2() throws Exception {
      String inputFile = "/TestSources/Tree1Child.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes083.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes083-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Several attributes for elem//@*.
   public void test_Axes083_3() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes083.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes083-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //No attributes for elem//@name, due to none existing at all.
   public void test_Axes084_1() throws Exception {
      String inputFile = "/TestSources/TreeTrunc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes084.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes084-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //No attributes for elem//@name, none of that name exist.
   public void test_Axes084_2() throws Exception {
      String inputFile = "/TestSources/Tree1Child.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes084.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes084-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //1 attribute for elem//@name.
   public void test_Axes084_3() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes084.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes084-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Several attributes for elem//@name.
   public void test_Axes084_4() throws Exception {
      String inputFile = "/TestSources/TreeRepeat.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes084.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes084-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Use of // to get all elements of a given name.
   // Not a valid XPath 2 script
//   public void test_Axes085() throws Exception {
//      String inputFile = "/TestSources/nw_Customers.xml";
//      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes085.xq";
//      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes085.xml";
//      String expectedResult = getExpectedResult(resultFile);
//      URL fileURL = bundle.getEntry(inputFile);
//      loadDOMDocument(fileURL);
//      
//      // Get XML Schema Information for the Document
//      XSModel schema = getGrammar();
//
//      setupDynamicContext(schema);
//
//      String xpath = extractXPathExpression(xqFile, inputFile);
//      String actual = null;
//      try {
//	   	  XPath path = compileXPath(xpath);
//	
//	      Evaluator eval = new DefaultEvaluator(dc, domDoc);
//	      ResultSequence rs = eval.evaluate(path);
//         
//          actual = buildResultString(rs);
//	
//      } catch (XPathParserException ex) {
//    	 actual = ex.code();
//      } catch (StaticError ex) {
//         actual = ex.code();
//      } catch (DynamicError ex) {
//         actual = ex.code();
//      }
//
//      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
//        
//
//   }

   //Parent of attribute node.
//   public void test_Axes086() throws Exception {
//      String inputFile = "/TestSources/Tree1Text.xml";
//      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes086.xq";
//      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes086.xml";
//      String expectedResult = getExpectedResult(resultFile);
//      URL fileURL = bundle.getEntry(inputFile);
//      loadDOMDocument(fileURL);
//      
//      // Get XML Schema Information for the Document
//      XSModel schema = getGrammar();
//
//      setupDynamicContext(schema);
//
//      String xpath = extractXPathExpression(xqFile, inputFile);
//      String actual = null;
//      try {
//	   	  XPath path = compileXPath(xpath);
//	
//	      Evaluator eval = new DefaultEvaluator(dc, domDoc);
//	      ResultSequence rs = eval.evaluate(path);
//         
//          actual = buildResultString(rs);
//	
//      } catch (XPathParserException ex) {
//    	 actual = ex.code();
//      } catch (StaticError ex) {
//         actual = ex.code();
//      } catch (DynamicError ex) {
//         actual = ex.code();
//      }
//
//      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
//        
//
//   }

   //Parent of text nodes.
//   public void test_Axes087() throws Exception {
//      String inputFile = "/TestSources/xq311B.xml";
//      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes087.xq";
//      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/Axes/Axes087.xml";
//      String expectedResult = getExpectedResult(resultFile);
//      URL fileURL = bundle.getEntry(inputFile);
//      loadDOMDocument(fileURL);
//      
//      // Get XML Schema Information for the Document
//      XSModel schema = getGrammar();
//
//      setupDynamicContext(schema);
//
//      String xpath = extractXPathExpression(xqFile, inputFile);
//      String actual = null;
//      try {
//	   	  XPath path = compileXPath(xpath);
//	
//	      Evaluator eval = new DefaultEvaluator(dc, domDoc);
//	      ResultSequence rs = eval.evaluate(path);
//         
//          actual = buildResultString(rs);
//	
//      } catch (XPathParserException ex) {
//    	 actual = ex.code();
//      } catch (StaticError ex) {
//         actual = ex.code();
//      } catch (DynamicError ex) {
//         actual = ex.code();
//      }
//
//      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
//        
//
//   }

   //Empty step should result in parse error.
   public void test_Axes088() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/Axes088.xq";
      String expectedResult = "XPST0003";
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a step axis, which operates on a non node context item.
   public void test_axis_err_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/Axes/axis-err-1.xq";
      String expectedResult = "XPTY0020";
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      xpath = "20[child::text()]";
      
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
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
      