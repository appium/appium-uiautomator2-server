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
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.testsuite.core;

import java.net.URL;

import org.apache.xerces.xs.XSModel;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.StaticError;
import org.eclipse.wst.xml.xpath2.processor.XPathParserException;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyType;
import org.eclipse.wst.xml.xpath2.processor.test.AbstractPsychoPathTest;
      
      
public class ExternalContextExprTest extends AbstractPsychoPathTest {

   //Context Item expression that just uses the "name" element and no context item defined.
   public void test_externalcontextitem_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-1.xq";

      String expectedResult = "XPDY0002";
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "name";
      String actual = null;
      try {
	   	  compileXPath(xpath);
	      ResultSequence rs = evaluate(null); // no context node
	
	      AnyType result = rs.first();

	      actual = result.getStringValue();
	      
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of external context item expression where context item is used as a string.
   public void test_externalcontextitem_2() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
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
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of external context item expression where context item is used as an integer.
   public void test_externalcontextitem_3() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
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
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of external context item expression where context item is used as a decimal.
   public void test_externalcontextitem_4() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
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
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of external context item expression where context item is used a float.
   public void test_externalcontextitem_5() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
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
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of external context item expression where context item is used as a double.
   public void test_externalcontextitem_6() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-6.xq";
      String resultFile = "/ExpectedTestResults/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-6.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
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
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of external context item expression where context item is used as a boolean value.
   public void test_externalcontextitem_7() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-7.xq";
      String resultFile = "/ExpectedTestResults/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-7.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
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
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of external context item expression where context item is used as a boolean with fn:not.
   public void test_externalcontextitem_8() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-8.xq";
      String resultFile = "/ExpectedTestResults/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-8.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
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
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of external context item expression where context item is used as argument to sum function.
   public void test_externalcontextitem_9() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-9.xq";
      String resultFile = "/ExpectedTestResults/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-9.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
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
      } catch (DynamicError ex) {
    	 ex.printStackTrace();
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of external context item expression where context item used as part of addition operation.
   public void test_externalcontextitem_10() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-10.xq";
      String resultFile = "/ExpectedTestResults/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-10.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
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
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of external context item expression where context item used as part of a subtraction operation.
   public void test_externalcontextitem_11() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-11.xq";
      String resultFile = "/ExpectedTestResults/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-11.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
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
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of external context item expression where context item used as part of a multiplication operation.
   public void test_externalcontextitem_12() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-12.xq";
      String resultFile = "/ExpectedTestResults/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-12.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
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
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of external context item expression where context item used as part of a modulus operation.
   public void test_externalcontextitem_13() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-13.xq";
      String resultFile = "/ExpectedTestResults/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-13.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
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
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of external context item expression where context item used as part of a divison (div operator) operation.
   public void test_externalcontextitem_14() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-14.xq";
      String resultFile = "/ExpectedTestResults/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-14.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
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
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of external context item expression where context item used as part of a divison (idiv operator) operation.
   public void test_externalcontextitem_15() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-15.xq";
      String resultFile = "/ExpectedTestResults/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-15.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
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
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of external context item expression where context item used as part of a boolean expression (and operator).
   public void test_externalcontextitem_16() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-16.xq";
      String resultFile = "/ExpectedTestResults/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-16.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
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
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of external context item expression where context item used as part of a boolean expression (or operator).
   public void test_externalcontextitem_17() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-17.xq";
      String resultFile = "/ExpectedTestResults/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-17.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
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
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of external context item expression where context item used as argument to string-length function.
   public void test_externalcontextitem_18() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-18.xq";
      String resultFile = "/ExpectedTestResults/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-18.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
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
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of external context item expression where context item used as argument to "avg" function.
   public void test_externalcontextitem_19() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-19.xq";
      String resultFile = "/ExpectedTestResults/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-19.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
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
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of external context item expression where context item used as argument to "min" function.
   public void test_externalcontextitem_20() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-20.xq";
      String resultFile = "/ExpectedTestResults/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-20.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
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
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of external context item expression where context item used as argument to "max" function.
   public void test_externalcontextitem_21() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-21.xq";
      String resultFile = "/ExpectedTestResults/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-21.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
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
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of external context item expression where context item used as argument to "max" function.
   public void test_externalcontextitem_22() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-22.xq";
      String resultFile = "/ExpectedTestResults/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-22.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
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

   //Reference to a context item that has not been bound.
   public void test_externalcontextitem_23() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-23.xq";
      String expectedResult = "XPDY0002";
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
	   	  compileXPath(xpath);
	      ResultSequence rs = evaluate(null); // no context node
	      
	      AnyType result = rs.first();
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of external context item expression where context item used as argument to "max" function.
   public void test_externalcontextitem_24() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-24.xq";
      String resultFile = "/ExpectedTestResults/Expressions/ContextExpr/ExternalContextExpr/externalcontextitem-22.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
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

}
      