
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
      
      
public class ReturnExprTest extends AbstractPsychoPathTest {

   //FLWOR expression returns selected element nodes.
   public void test_ReturnExpr005() throws Exception {
      String inputFile = "/TestSources/fsx.xml";
      String xqFile = "/Queries/XQuery/Expressions/FLWORExpr/ReturnExpr/ReturnExpr005.xq";
      String resultFile = "/ExpectedTestResults/Expressions/FLWORExpr/ReturnExpr/ReturnExpr005.xml";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildXMLResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //FLWOR expression returns selected values.
   public void test_ReturnExpr006() throws Exception {
      String inputFile = "/TestSources/fsx.xml";
      String xqFile = "/Queries/XQuery/Expressions/FLWORExpr/ReturnExpr/ReturnExpr006.xq";
      String resultFile = "/ExpectedTestResults/Expressions/FLWORExpr/ReturnExpr/ReturnExpr006.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //FLWOR expression return parent of select nodes.
   public void test_ReturnExpr007() throws Exception {
      String inputFile = "/TestSources/fsx.xml";
      String xqFile = "/Queries/XQuery/Expressions/FLWORExpr/ReturnExpr/ReturnExpr007.xq";
      String resultFile = "/ExpectedTestResults/Expressions/FLWORExpr/ReturnExpr/ReturnExpr007.xml";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildXMLResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //FLWOR expression returns constant value, independent of input bindings.
   public void test_ReturnExpr008() throws Exception {
      String inputFile = "/TestSources/fsx.xml";
      String xqFile = "/Queries/XQuery/Expressions/FLWORExpr/ReturnExpr/ReturnExpr008.xq";
      String resultFile = "/ExpectedTestResults/Expressions/FLWORExpr/ReturnExpr/ReturnExpr008.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //FLWOR expression returns node from document, independent of input bindings.
   public void test_ReturnExpr009() throws Exception {
      String inputFile = "/TestSources/fsx.xml";
      String xqFile = "/Queries/XQuery/Expressions/FLWORExpr/ReturnExpr/ReturnExpr009.xq";
      String resultFile = "/ExpectedTestResults/Expressions/FLWORExpr/ReturnExpr/ReturnExpr009.xml";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildXMLResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //FLWOR expression returns empty sequence literal.
   public void test_ReturnExpr010() throws Exception {
      String inputFile = "/TestSources/fsx.xml";
      String xqFile = "/Queries/XQuery/Expressions/FLWORExpr/ReturnExpr/ReturnExpr010.xq";
      String resultFile = "/ExpectedTestResults/Expressions/FLWORExpr/ReturnExpr/ReturnExpr010.xml";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "for $file in ($input-context//Folder)[1]/File return ()";
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = "<empty>" + buildXMLResultString(rs) + "</empty>";
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertXMLEqual("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //FLWOR expression returns a constructed sequence.
   public void test_ReturnExpr011() throws Exception {
      String inputFile = "/TestSources/fsx.xml";
      String xqFile = "/Queries/XQuery/Expressions/FLWORExpr/ReturnExpr/ReturnExpr011.xq";
      String resultFile = "/ExpectedTestResults/Expressions/FLWORExpr/ReturnExpr/ReturnExpr011.xml";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildXMLResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Multiple return statements.
   public void test_ReturnExpr012() throws Exception {
      String inputFile = "/TestSources/fsx.xml";
      String xqFile = "/Queries/XQuery/Expressions/FLWORExpr/ReturnExpr/ReturnExpr012.xq";
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

   //Missing 'return' statement in FLWOR expression.
   public void test_ReturnExpr013() throws Exception {
      String inputFile = "/TestSources/fsx.xml";
      String xqFile = "/Queries/XQuery/Expressions/FLWORExpr/ReturnExpr/ReturnExpr013.xq";
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

   //FLWOR expression return statement depends on undefined variable.
   public void test_ReturnExpr014() throws Exception {
      String inputFile = "/TestSources/fsx.xml";
      String xqFile = "/Queries/XQuery/Expressions/FLWORExpr/ReturnExpr/ReturnExpr014.xq";
      String expectedResult = "XPST0008";
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Variable bound to value from return statement.
   public void test_ReturnExpr015() throws Exception {
      String inputFile = "/TestSources/fsx.xml";
      String xqFile = "/Queries/XQuery/Expressions/FLWORExpr/ReturnExpr/ReturnExpr015.xq";
      String resultFile = "/ExpectedTestResults/Expressions/FLWORExpr/ReturnExpr/ReturnExpr015.xml";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildXMLResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Apply arithmetic operator inside 'return' statement.
   public void test_ReturnExpr017() throws Exception {
      String inputFile = "/TestSources/fsx.xml";
      String xqFile = "/Queries/XQuery/Expressions/FLWORExpr/ReturnExpr/ReturnExpr017.xq";
      String resultFile = "/ExpectedTestResults/Expressions/FLWORExpr/ReturnExpr/ReturnExpr017.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "for $file in ($input-context//Folder)[1]/File return ($file/Stream/StreamSize)[1] + 1";
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 ex.printStackTrace();
    	 actual = ex.code();
      } catch (StaticError ex) {
    	  ex.printStackTrace();
         actual = ex.code();
      } catch (DynamicError ex) {
    	  ex.printStackTrace();
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Apply comparison operator inside 'return' statement.
   public void test_ReturnExpr018() throws Exception {
      String inputFile = "/TestSources/fsx.xml";
      String xqFile = "/Queries/XQuery/Expressions/FLWORExpr/ReturnExpr/ReturnExpr018.xq";
      String resultFile = "/ExpectedTestResults/Expressions/FLWORExpr/ReturnExpr/ReturnExpr018.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 ex.printStackTrace();
    	 actual = ex.code();
      } catch (StaticError ex) {
    	 ex.printStackTrace();
         actual = ex.code();
      } catch (DynamicError ex) {
    	 ex.printStackTrace();
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Multiple 'return' statements.
   public void test_ReturnExpr019() throws Exception {
      String inputFile = "/TestSources/fsx.xml";
      String xqFile = "/Queries/XQuery/Expressions/FLWORExpr/ReturnExpr/ReturnExpr019.xq";
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

   //'return' expression containing a typed value constructor function.
   public void test_ReturnExpr020() throws Exception {
      String inputFile = "/TestSources/fsx.xml";
      String xqFile = "/Queries/XQuery/Expressions/FLWORExpr/ReturnExpr/ReturnExpr020.xq";
      String resultFile = "/ExpectedTestResults/Expressions/FLWORExpr/ReturnExpr/ReturnExpr020.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
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
      