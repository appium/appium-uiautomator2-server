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
      
      
public class UserDefinedSITest extends AbstractPsychoPathTest {

   //Evaluation of simple constructor function for user defined data type derived from xs:integer.
   public void test_user_defined_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/UserDefinedSI/user-defined-1.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/UserDefinedSI/user-defined-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addUserDefinedSimpleTypes(schema);

      String xpath = "myType:sizeType(1)";
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
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

   //Evaluation of simple constructor function violation for user defined data type derived from xs:integer.
   public void test_user_defined_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      List expectedResult = Arrays.asList(new String[] {"FORG0001", "XQST0009", "XPST0017"});
      
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addUserDefinedSimpleTypes(schema);

      String xpath = "myType:sizeType(20)";
      String actual = null;
      try {
          compileXPath(xpath);
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

   //Evaluation of simple constructor function for used in addition operation. Type derived from xs:integer.
   public void test_user_defined_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/UserDefinedSI/user-defined-3.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/UserDefinedSI/user-defined-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addUserDefinedSimpleTypes(schema);

      String xpath = "myType:sizeType(1) + myType:sizeType(2)";
      String actual = null;
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

   //Evaluation of simple constructor function for user defined data type derived from xs:string.
   public void test_user_defined_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/UserDefinedSI/user-defined-4.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/UserDefinedSI/user-defined-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpSchema = loadSchema();
      loadDOMDocument(fileURL, jaxpSchema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addUserDefinedSimpleTypes(schema);

      String xpath = "myType:stringBased(\"valid value 4\")";
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of usage of fn:concat on constructor function for user defined data type derived from xs:string.
   public void test_user_defined_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/UserDefinedSI/user-defined-5.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/UserDefinedSI/user-defined-5.txt";
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

   //Use constructor function to cast a value to its base type (xs:integer).
   public void test_user_defined_6() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/UserDefinedSI/user-defined-6.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/UserDefinedSI/user-defined-6.txt";
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

   //Use constructor function to check if user defined type is castable as its base type (xs:integer).
   public void test_user_defined_7() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/UserDefinedSI/user-defined-7.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/UserDefinedSI/user-defined-7.txt";
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

   //Use constructor function to evaluate if two user defined types can be casted to another user defined type.
   public void test_user_defined_8() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/UserDefinedSI/user-defined-8.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/UserDefinedSI/user-defined-8.txt";
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

   //Use constructor function to evaluate if two user defined types are castable to another user defined type.
   public void test_user_defined_9() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/UserDefinedSI/user-defined-9.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/UserDefinedSI/user-defined-9.txt";
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

   //Use constructor functions on addition operation on two user defined types.
   public void test_user_defined_10() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/UserDefinedSI/user-defined-10.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/UserDefinedSI/user-defined-10.txt";
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
     	 ex.printStackTrace();
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
   
}
      