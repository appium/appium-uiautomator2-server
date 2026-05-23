/*******************************************************************************
 * Copyright (c) 2009, 2017 Standards for Technology for Automotive Retail and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     David Carver (STAR) - initial API and implementation
 *     Jesper Steen Moeller - bug 282096 - special case for a test which works
 *                                         differently in XPath2 than in XQuery
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.testsuite.core;

import java.net.URL;

import org.apache.xerces.xs.XSModel;
import org.eclipse.wst.xml.xpath2.processor.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.StaticError;
import org.eclipse.wst.xml.xpath2.processor.XPathParserException;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyType;
import org.eclipse.wst.xml.xpath2.processor.test.AbstractPsychoPathTest;
      
      
public class LiteralsTest extends AbstractPsychoPathTest {

   //Simple use case for string literals.
   public void test_Literals001() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals001.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals001.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Simple use case for string literals.
   public void test_Literals002() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals002.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals002.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test case where string literal contains a new line.
   public void test_Literals003() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals003.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals003.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test case where string literal contains a new line.
   public void test_Literals004() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals004.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals004.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }


   //Unterminated string literal.
   public void test_Literals006() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals006.xq";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Unterminated string literal.
   public void test_Literals007() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals007.xq";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Incorrectly terminated string literal.
   public void test_Literals008() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals008.xq";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Incorrectly terminated string literal.
   public void test_Literals009() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals009.xq";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for valid interger literal.
   public void test_Literals010() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals010.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals010.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for valid interger literal.
   public void test_Literals011() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals011.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals011.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for valid decimal literal.
   public void test_Literals012() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals012.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals012.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for valid decimal literal.
   public void test_Literals013() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals013.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals013.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for valid decimal literal.
   public void test_Literals014() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals014.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals014.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for valid decimal literal.
   public void test_Literals015() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals015.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals015.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for valid double literal.
   public void test_Literals016() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals016.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals016.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for valid double literal.
   public void test_Literals017() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals017.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals017.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for valid double literal.
   public void test_Literals018() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals018.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals018.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for valid double literal.
   public void test_Literals019() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals019.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals019.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for valid double literal.
   public void test_Literals020() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals020.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals020.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for valid double literal.
   public void test_Literals021() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals021.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals021.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for valid double literal.
   public void test_Literals022() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals022.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals022.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for valid double literal.
   public void test_Literals023() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals023.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals023.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for valid double literal.
   public void test_Literals024() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals024.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals024.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for valid double literal.
   public void test_Literals025() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals025.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals025.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for valid double literal.
   public void test_Literals026() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals026.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals026.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for valid double literal.
   public void test_Literals027() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals027.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals027.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for valid double literal.
   public void test_Literals028() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals028.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals028.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for valid double literal.
   public void test_Literals029() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals029.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals029.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for valid double literal.
   public void test_Literals030() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals030.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals030.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for valid double literal.
   public void test_Literals031() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals031.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals031.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for valid double literal.
   public void test_Literals032() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals032.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals032.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for valid double literal.
   public void test_Literals033() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals033.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals033.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for valid double literal.
   public void test_Literals034() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals034.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals034.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for valid double literal.
   public void test_Literals035() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals035.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals035.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for invalid decimal literal.
   public void test_Literals036() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals036.xq";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for invalid decimal literal.
   public void test_Literals037() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals037.xq";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for invalid decimal literal.
   public void test_Literals038() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals038.xq";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for invalid decimal literal.
   public void test_Literals039() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals039.xq";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for invalid decimal literal.
   public void test_Literals040() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals040.xq";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for invalid double literal.
   public void test_Literals041() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals041.xq";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for invalid double literal.
   public void test_Literals042() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals042.xq";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for invalid double literal.
   public void test_Literals043() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals043.xq";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for invalid double literal.
   public void test_Literals044() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals044.xq";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for invalid double literal.
   public void test_Literals045() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals045.xq";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for invalid double literal.
   public void test_Literals046() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals046.xq";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for invalid double literal.
   public void test_Literals047() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals047.xq";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for invalid double literal.
   public void test_Literals048() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals048.xq";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for invalid double literal.
   public void test_Literals049() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals049.xq";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for invalid double literal.
   public void test_Literals050() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals050.xq";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for invalid double literal.
   public void test_Literals051() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals051.xq";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for invalid double literal.
   public void test_Literals052() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals052.xq";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for invalid double literal.
   public void test_Literals053() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals053.xq";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for invalid double literal.
   public void test_Literals054() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals054.xq";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for invalid double literal.
   public void test_Literals055() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals055.xq";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for string literal containing the predefined entity reference '&amp;'.
   public void test_Literals056() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals056.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals056.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for string literal containing the predefined entity reference '&quot;'.
   public void test_Literals057() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals057.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals057.txt";
      String expectedResult = getExpectedResultNoEscape(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpressionNoEscape(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for string literal containing the predefined entity reference '&apos;'.
   public void test_Literals058() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals058.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals058-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for string literal containing the predefined entity reference '&lt;'.
   public void test_Literals059() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals059.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals059.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test for string literal containing the predefined entity reference '&gt;'.
   public void test_Literals060() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals060.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals060.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test the escaping of the " (quotation) character in XQuery.
   public void test_Literals062() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals062.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals062-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test the escaping of the ' (apostrophe) character in XQuery.
   public void test_Literals063() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals063.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals063-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test the escaping of the ' (apostrophe) and " (quotation) characters in XQuery.
   public void test_Literals064() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals064.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals064-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test the escaping of the ' (apostrophe) and " (quotation) characters in XQuery.
   public void test_Literals065() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals065.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals065-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);

   }


   //Test the escaping of the ' (apostrophe) and " (quotation) characters as part of an XML text node constructor.
   public void test_Literals068() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      //String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals068.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals068-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "'He said, \"I don''t like it.\"'";
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Test the escaping of the ' (apostrophe) and " (quotation) characters as part of an XML text node constructor.
   public void test_Literals069() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      //String xqFile = "/Queries/XQuery/Expressions/PrimaryExpr/Literals/Literals069.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PrimaryExpr/Literals/Literals069-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "\"He said, \"\"I don't like it.\"\"\"";
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

}
      