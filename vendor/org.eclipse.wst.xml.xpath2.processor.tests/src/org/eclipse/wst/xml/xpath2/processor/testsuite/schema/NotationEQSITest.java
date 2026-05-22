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

package org.eclipse.wst.xml.xpath2.processor.testsuite.schema;

import java.net.URL;

import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.xerces.jaxp.validation.XMLSchemaFactory;
import org.apache.xerces.xs.XSModel;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.StaticError;
import org.eclipse.wst.xml.xpath2.processor.XPathParserException;
import org.eclipse.wst.xml.xpath2.processor.test.AbstractPsychoPathTest;
import org.xml.sax.SAXException;
      
      
public class NotationEQSITest extends AbstractPsychoPathTest {

   //Notation comparison.
   public void test_Comp_notation_1() throws Exception {
      String inputFile = "/TestSources/notation.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/NotationEQSI/Comp-notation-1.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/NotationEQSI/Comp-notation-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Notation comparison.
   public void test_Comp_notation_2() throws Exception {
      String inputFile = "/TestSources/notation.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/NotationEQSI/Comp-notation-2.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/NotationEQSI/Comp-notationalt-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("myns", "http://www.example.com/notation");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Notation comparison.
   public void test_Comp_notation_3() throws Exception {
      String inputFile = "/TestSources/notation.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/NotationEQSI/Comp-notation-3.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/NotationEQSI/Comp-notation-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("myns", "http://www.example.com/notation");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Notation comparison using "ne".
   public void test_Comp_notation_4() throws Exception {
      String inputFile = "/TestSources/notation.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/NotationEQSI/Comp-notation-4.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/NotationEQSI/Comp-notation-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("myns", "http://www.example.com/notation");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Notation comparison using "ne".
   public void test_Comp_notation_5() throws Exception {
      String inputFile = "/TestSources/notation.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/NotationEQSI/Comp-notation-5.xq";
      String expectedResult = "true";
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("myns", "http://www.example.com/notation");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Notation comparison using "ne".
   public void test_Comp_notation_6() throws Exception {
      String inputFile = "/TestSources/notation.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/NotationEQSI/Comp-notation-6.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/NotationEQSI/Comp-notation-6.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("myns", "http://www.example.com/notation");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of notation comparison using "eq" and used with fn:not - returns false.
   public void test_Comp_notation_7() throws Exception {
      String inputFile = "/TestSources/notation.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/NotationEQSI/Comp-notation-7.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/NotationEQSI/Comp-notation-7.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("myns", "http://www.example.com/notation");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of notation comparison using "eq" and used with fn:not - returns true.
   public void test_Comp_notation_8() throws Exception {
      String inputFile = "/TestSources/notation.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/NotationEQSI/Comp-notation-8.xq";
      String expectedResult = "true";
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("myns", "http://www.example.com/notation");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of notation comparison using "ne" and used with fn:not - returns true.
   public void test_Comp_notation_9() throws Exception {
      String inputFile = "/TestSources/notation.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/NotationEQSI/Comp-notation-9.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/NotationEQSI/Comp-notation-9.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("myns", "http://www.example.com/notation");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of notation comparison using "ne" and used with fn:not - returns false.
   public void test_Comp_notation_10() throws Exception {
      String inputFile = "/TestSources/notation.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/NotationEQSI/Comp-notation-10.xq";
      String expectedResult = "false";
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("myns", "http://www.example.com/notation");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of notation comparison using "eq" and used with fn:boolean function.
   public void test_Comp_notation_11() throws Exception {
      String inputFile = "/TestSources/notation.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/NotationEQSI/Comp-notation-11.xq";
      String expectedResult = "true";
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("myns", "http://www.example.com/notation");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of notation comparison using "ne" and used with fn:boolean function.
   public void test_Comp_notation_12() throws Exception {
      String inputFile = "/TestSources/notation.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/NotationEQSI/Comp-notation-12.xq";
      String expectedResult = "true";
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("myns", "http://www.example.com/notation");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of notation comparison using "eq" used in boolean expression with "fn:true" and "and".
   public void test_Comp_notation_13() throws Exception {
      String inputFile = "/TestSources/notation.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/NotationEQSI/Comp-notation-13.xq";
      String expectedResult = "false";
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("myns", "http://www.example.com/notation");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of notation comparison using "ne" used in boolean expression with "fn:true" and "and".
   public void test_Comp_notation_14() throws Exception {
      String inputFile = "/TestSources/notation.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/NotationEQSI/Comp-notation-14.xq";
      String expectedResult = "false"; 
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("myns", "http://www.example.com/notation");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of notation comparison using "eq" used in boolean expression with "fn:true" and "or".
   public void test_Comp_notation_15() throws Exception {
      String inputFile = "/TestSources/notation.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/NotationEQSI/Comp-notation-15.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/NotationEQSI/Comp-notation-15.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("myns", "http://www.example.com/notation");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of notation comparison using "ne" used in boolean expression with "fn:true" and "or".
   public void test_Comp_notation_16() throws Exception {
      String inputFile = "/TestSources/notation.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/NotationEQSI/Comp-notation-16.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/NotationEQSI/Comp-notation-16.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("myns", "http://www.example.com/notation");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of notation comparison using "eq" used in boolean expression with "fn:false" and "and".
   public void test_Comp_notation_17() throws Exception {
      String inputFile = "/TestSources/notation.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/NotationEQSI/Comp-notation-17.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/NotationEQSI/Comp-notation-17.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("myns", "http://www.example.com/notation");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of notation comparison using "ne" used in boolean expression with "fn:false" and "and".
   public void test_Comp_notation_18() throws Exception {
      String inputFile = "/TestSources/notation.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/NotationEQSI/Comp-notation-18.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/NotationEQSI/Comp-notation-18.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("myns", "http://www.example.com/notation");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of notation comparison using "eq" used in boolean expression with "fn:false" and "or".
   public void test_Comp_notation_19() throws Exception {
      String inputFile = "/TestSources/notation.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/NotationEQSI/Comp-notation-19.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/NotationEQSI/Comp-notation-19.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("myns", "http://www.example.com/notation");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of notation comparison using "ne" used in boolean expression with "fn:false" and "or".
   public void test_Comp_notation_20() throws Exception {
      String inputFile = "/TestSources/notation.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/NotationEQSI/Comp-notation-20.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/NotationEQSI/Comp-notation-20.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("myns", "http://www.example.com/notation");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of expression for notation comparison using "eq" ar argument to "fn:false" function.
   public void test_Comp_notation_21() throws Exception {
      String inputFile = "/TestSources/notation.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/NotationEQSI/Comp-notation-21.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/NotationEQSI/Comp-notation-21.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("myns", "http://www.example.com/notation");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of expression for notation comparison using "ne" ar argument to "fn:false" function.
   public void test_Comp_notation_22() throws Exception {
      String inputFile = "/TestSources/notation.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/NotationEQSI/Comp-notation-22.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/NotationEQSI/Comp-notation-22.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("myns", "http://www.example.com/notation");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   private Schema loadSchema() throws SAXException {
		String schemaFile = "/TestSources/notationschema.xsd";
	      SchemaFactory schemaFactory = new XMLSchemaFactory();
	      URL schemaURL = bundle.getEntry(schemaFile);
	      Schema jaxpschema = schemaFactory.newSchema(schemaURL);
		return jaxpschema;
	}
   
}
      