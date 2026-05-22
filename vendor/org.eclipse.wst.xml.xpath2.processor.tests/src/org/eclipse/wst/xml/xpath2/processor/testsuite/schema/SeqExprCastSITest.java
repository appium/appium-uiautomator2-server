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
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.testsuite.schema;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

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
      
      
public class SeqExprCastSITest extends AbstractPsychoPathTest {

   //Evaluates casting a QName to another QName.
   public void test_qname_cast_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/qname-cast-1.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/value1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addUserDefinedSimpleTypes(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates casting QName derived type to a QName.
   public void test_qname_cast_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/qname-cast-2.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/value1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addUserDefinedSimpleTypes(schema);

      String xpath = "myType:QNameBased(\"value1\")";
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates casting QName derived type to another QName derived type.
   public void test_qname_cast_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/qname-cast-3.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/value1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addUserDefinedSimpleTypes(schema);

      String xpath = "myType:QNameBased(\"value1\") cast as myType:QNameBased";
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates casting QName type to a QName derived type.
   public void test_qname_cast_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/qname-cast-4.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/value1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addUserDefinedSimpleTypes(schema);

      String xpath = "xs:QName(\"value1\") cast as myType:QNameBased";
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluates casting a type derived from an xs:NOTATION to an xs:NOTATION type.
   public void test_notation_cast_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/notation-cast-2.xq";
      List expectedResult = Arrays.asList(new String[] {"XPST0080", "XPST0017"});
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addUserDefinedSimpleTypes(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertTrue("Expected one of " + expectedResult + " but actual was: " + actual, expectedResult.contains(actual));
   }

   //Evaluates casting a type derived from an xs:NOTATION to a type derived from xs:NOTATION.
   public void test_notation_cast_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/notation-cast-3.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/mytype-value1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addUserDefinedSimpleTypes(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);
         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluate an expression and cast it as an xs:integer. This test queries an XML file to obtain data.
   public void test_casthcds1() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds1.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluate an expression and cast it as an xs:float. This test queries an XML file to obtain data.
   public void test_casthcds2() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds2.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/truevalue.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluate an expression and cast it as an xs:boolean. This test queries an XML file to obtain data.
   public void test_casthcds3() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds3.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluate an expression and cast it as an xs:double. This test queries an XML file to obtain data.
   public void test_casthcds4() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds4.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluate an expression and cast it as an xs:decimal. This test queries an XML file to obtain data.
   public void test_casthcds5() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds5.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluate an expression and cast it as an xs:string. This test queries an XML file to obtain data.
   public void test_casthcds6() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds6.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds6.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a xs:string value casted as an xs:string. This test queries an XML file to obtain data.
   public void test_casthcds7() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds7.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds7.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a xs:float value casted as an xs:float. This test queries an XML file to obtain data.
   public void test_casthcds8() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds8.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/truevalue.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a xs:float value casted as an xs:string. This test queries an XML file to obtain data.
   public void test_casthcds9() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds9.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds9alt.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a xs:float value casted as an xs:double. This test queries an XML file to obtain data.
   public void test_casthcds10() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds10.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds10.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of a xs:float value casted as an xs:boolean. This test queries an XML file to obtain data.
   public void test_casthcds11() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds11.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds11.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of an xs:double value casted as an xs:double. This test queries an XML file to obtain data.
   public void test_casthcds12() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds12.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds12.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of an xs:double value casted as an xs:string. This test queries an XML file to obtain data.
   public void test_casthcds13() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds13.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds13.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of an xs:double value casted as an xs:float. This test queries an XML file to obtain data.
   public void test_casthcds14() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds14.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/truevalue.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of an xs:double value casted as an xs:boolean. This test queries an XML file to obtain data.
   public void test_casthcds15() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds15.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds15.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of an xs:decimal value casted as an xs:string. This test queries an XML file to obtain data.
   public void test_casthcds16() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds16.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds16.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of an xs:decimal value casted as an xs:float. This test queries an XML file to obtain data.
   public void test_casthcds17() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds17.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds17.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of an xs:decimal value casted as an xs:double. This test queries an XML file to obtain data.
   public void test_casthcds18() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds18.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds18.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of an xs:decimal value casted as an xs:decimal. This test queries an XML file to obtain data.
   public void test_casthcds19() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds19.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds19.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of an xs:decimal value casted as an xs:integer. This test queries an XML file to obtain data.
   public void test_casthcds20() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds20.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds20.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
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

   //Evaluation of an xs:decimal value casted as an xs:boolean. This test queries an XML file to obtain data.
   public void test_casthcds21() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds21.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds21.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of an xs:integer value casted as an xs:string. This test queries an XML file to obtain data.
   public void test_casthcds22() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds22.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds22.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of an xs:integer value casted as an xs:float. This test queries an XML file to obtain data.
   public void test_casthcds23() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds23.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/truevalue.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of an xs:integer value casted as an xs:double. This test queries an XML file to obtain data.
   public void test_casthcds24() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds24.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds24.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of an xs:integer value casted as an xs:decimal. This test queries an XML file to obtain data.
   public void test_casthcds25() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds25.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds25.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of an xs:integer value casted as an xs:integer. This test queries an XML file to obtain data.
   public void test_casthcds26() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds26.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds26.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of an xs:integer value casted as an xs:boolean. This test queries an XML file to obtain data.
   public void test_casthcds27() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds27.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds27.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of an xs:dateTime value casted as an xs:string. This test queries an XML file to obtain data.
   public void test_casthcds28() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds28.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds28.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of an xs:dateTime value casted as an xs:dateTime. This test queries an XML file to obtain data.
   public void test_casthcds29() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds29.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds29.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of an xs:dateTime value casted as an xs:time. This test queries an XML file to obtain data.
   public void test_casthcds30() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds30.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds30.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of an xs:dateTime value casted as an xs:date. This test queries an XML file to obtain data.
   public void test_casthcds31() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds31.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds31.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of an xs:date value casted as an xs:string. This test queries an XML file to obtain data.
   public void test_casthcds32() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds32.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds32.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of an xs:date value casted as an xs:dateTime. This test queries an XML file to obtain data.
   public void test_casthcds33() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds33.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds33.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of an xs:date value casted as an xs:date. This test queries an XML file to obtain data.
   public void test_casthcds34() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds34.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds34.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of an xs:time value casted as an xs:string. This test queries an XML file to obtain data.
   public void test_casthcds35() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds35.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds35.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of an xs:time value casted as an xs:time. This test queries an XML file to obtain data.
   public void test_casthcds36() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds36.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds36.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of an xs:boolean value casted as an xs:string. This test queries an XML file to obtain data.
   public void test_casthcds37() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds37.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds37.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of an xs:boolean value casted as an xs:float. This test queries an XML file to obtain data.
   public void test_casthcds38() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds38.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds38.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of an xs:boolean value casted as an xs:double. This test queries an XML file to obtain data.
   public void test_casthcds39() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds39.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds39.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of an xs:boolean value casted as an xs:decimal. This test queries an XML file to obtain data.
   public void test_casthcds40() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds40.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds40.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of an xs:boolean value casted as an xs:integer. This test queries an XML file to obtain data.
   public void test_casthcds41() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds41.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds41.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of an xs:boolean value casted as an xs:boolean. This test queries an XML file to obtain data.
   public void test_casthcds42() throws Exception {
      String inputFile = "/TestSources/atomic.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/SeqExprCastSI/casthcds42.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/SeqExprCastSI/casthcds42.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadAtomicSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("atomic", "http://www.w3.org/XQueryTest");

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
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
		String schemaFile = "/TestSources/userdefined.xsd";
	      SchemaFactory schemaFactory = new XMLSchemaFactory();
	      URL schemaURL = bundle.getEntry(schemaFile);
	      Schema jaxpschema = schemaFactory.newSchema(schemaURL);
		return jaxpschema;
	}
   
   private Schema loadAtomicSchema() throws SAXException {
		String schemaFile = "/TestSources/atomic.xsd";
	      SchemaFactory schemaFactory = new XMLSchemaFactory();
	      URL schemaURL = bundle.getEntry(schemaFile);
	      Schema jaxpschema = schemaFactory.newSchema(schemaURL);
		return jaxpschema;
	}
   

}
      