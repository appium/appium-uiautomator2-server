
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
      
      
public class ForExprWithoutTest extends AbstractPsychoPathTest {

   //For+Return - to iterate a constant sequence.
   public void test_ForExpr001() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/FLWORExpr/ForExpr/ForExpr001.xq";
      String resultFile = "/ExpectedTestResults/Expressions/FLWORExpr/ForExpr/ForExpr001.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //For+Return - use sequence(security/right) as the predicate.
   public void test_ForExpr005() throws Exception {
      String inputFile = "/TestSources/fsx.xml";
      String xqFile = "/Queries/XQuery/Expressions/FLWORExpr/ForExpr/ForExpr005.xq";
      String resultFile = "/ExpectedTestResults/Expressions/FLWORExpr/ForExpr/ForExpr005.xml";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
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

      assertXMLEqual("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //For+Return - use special character in variable name.
   public void test_ForExpr007() throws Exception {
      String inputFile = "/TestSources/fsx.xml";
      String xqFile = "/Queries/XQuery/Expressions/FLWORExpr/ForExpr/ForExpr007.xq";
      String resultFile = "/ExpectedTestResults/Expressions/FLWORExpr/ForExpr/ForExpr007.xml";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
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

   //For+Return - error, use variable in it's own declaration.
   public void test_ForExpr009() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/FLWORExpr/ForExpr/ForExpr009.xq";
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

   //Nested FLWOR expressions.
   public void test_ForExpr012() throws Exception {
      String inputFile = "/TestSources/fsx.xml";
      String xqFile = "/Queries/XQuery/Expressions/FLWORExpr/ForExpr/ForExpr012.xq";
      String resultFile = "/ExpectedTestResults/Expressions/FLWORExpr/ForExpr/ForExpr012.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Multiple variables based off the same input context.
   public void test_ForExpr013() throws Exception {
      String inputFile = "/TestSources/fsx.xml";
      String xqFile = "/Queries/XQuery/Expressions/FLWORExpr/ForExpr/ForExpr013.xq";
      String resultFile = "/ExpectedTestResults/Expressions/FLWORExpr/ForExpr/ForExpr013.xml";
      String expectedResult = "<result>" + getExpectedResult(resultFile) + "</result>";
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = "<result>" + buildXMLResultString(rs) + "</result>";
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertXMLEqual("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Incorrect syntax for nested loop. Multiple return statements at the same level.
   public void test_ForExpr014() throws Exception {
      String inputFile = "/TestSources/fsx.xml";
      String xqFile = "/Queries/XQuery/Expressions/FLWORExpr/ForExpr/ForExpr014.xq";
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

   //Return expression contains nested for expression.
   public void test_ForExpr015() throws Exception {
      String inputFile = "/TestSources/fsx.xml";
      String xqFile = "/Queries/XQuery/Expressions/FLWORExpr/ForExpr/ForExpr015.xq";
      String resultFile = "/ExpectedTestResults/Expressions/FLWORExpr/ForExpr/ForExpr015.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Interate over nodes in document and constant sequence. Return value based on both.
   public void test_ForExpr016() throws Exception {
      String inputFile = "/TestSources/fsx.xml";
      String xqFile = "/Queries/XQuery/Expressions/FLWORExpr/ForExpr/ForExpr016.xq";
      String resultFile = "/ExpectedTestResults/Expressions/FLWORExpr/ForExpr/ForExpr016.xml";
      String expectedResult = "<result>" + getExpectedResult(resultFile) + "</result>";
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = "<result>" + buildXMLResultString(rs) + "</result>";
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertXMLEqual("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Multiple return statements from single for expression.
   public void test_ForExpr017() throws Exception {
      String inputFile = "/TestSources/fsx.xml";
      String xqFile = "/Queries/XQuery/Expressions/FLWORExpr/ForExpr/ForExpr017.xq";
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

   //Multiple in statements for single variable binding.
   public void test_ForExpr018() throws Exception {
      String inputFile = "/TestSources/fsx.xml";
      String xqFile = "/Queries/XQuery/Expressions/FLWORExpr/ForExpr/ForExpr018.xq";
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

   //Multiple for statements for single variable binding.
   public void test_ForExpr019() throws Exception {
      String inputFile = "/TestSources/fsx.xml";
      String xqFile = "/Queries/XQuery/Expressions/FLWORExpr/ForExpr/ForExpr019.xq";
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

   //Multiple variable bindings followed by a trailing ,.
   public void test_ForExpr020() throws Exception {
      String inputFile = "/TestSources/fsx.xml";
      String xqFile = "/Queries/XQuery/Expressions/FLWORExpr/ForExpr/ForExpr020.xq";
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

   //Test order of iteration between two variables in the same 'for' statement.
   public void test_ForExpr021() throws Exception {
      String inputFile = "/TestSources/fsx.xml";
      String xqFile = "/Queries/XQuery/Expressions/FLWORExpr/ForExpr/ForExpr021.xq";
      String resultFile = "/ExpectedTestResults/Expressions/FLWORExpr/ForExpr/ForExpr021.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Redefine existing bound variable. Second binding overrides first.
   public void test_ForExpr025() throws Exception {
      String inputFile = "/TestSources/fsx.xml";
      String xqFile = "/Queries/XQuery/Expressions/FLWORExpr/ForExpr/ForExpr025.xq";
      String resultFile = "/ExpectedTestResults/Expressions/FLWORExpr/ForExpr/ForExpr025.xml";
      String expectedResult = "<result>" + getExpectedResult(resultFile) + "</result>";
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = "<result>" + buildXMLResultString(rs) + "</result>";
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertXMLEqual("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Missing 'in'.
   public void test_ForExpr026() throws Exception {
      String inputFile = "/TestSources/fsx.xml";
      String xqFile = "/Queries/XQuery/Expressions/FLWORExpr/ForExpr/ForExpr026.xq";
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

   //Wrong order for positional variable.
   public void test_ForExpr027() throws Exception {
      String inputFile = "/TestSources/fsx.xml";
      String xqFile = "/Queries/XQuery/Expressions/FLWORExpr/ForExpr/ForExpr027.xq";
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

}
      