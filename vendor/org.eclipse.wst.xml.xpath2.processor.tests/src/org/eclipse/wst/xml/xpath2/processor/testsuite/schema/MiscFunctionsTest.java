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
      
      
public class MiscFunctionsTest extends AbstractPsychoPathTest {

   //Evaluation of fn:id with given IDREF matching a single element.
   public void test_fn_id_5() throws Exception {
      String inputFile = "/TestSources/id.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/MiscFunctions/fn-id-5.xq";
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpschema = loadSchema();
      loadDOMDocument(fileURL, jaxpschema);
      
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

      assertTrue("XPath Result Error:" + xqFile + ":", actual.contains("\"id1\""));
      //assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

private Schema loadSchema() throws SAXException {
	String schemaFile = "/TestSources/id.xsd";
      SchemaFactory schemaFactory = new XMLSchemaFactory();
      URL schemaURL = bundle.getEntry(schemaFile);
      Schema jaxpschema = schemaFactory.newSchema(schemaURL);
	return jaxpschema;
}

   //Evaluation of fn:id with given IDREF does not match any element.
   public void test_fn_id_6() throws Exception {
      String inputFile = "/TestSources/id.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/MiscFunctions/fn-id-6.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/MiscFunctions/fn-id-6.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpschema = loadSchema();
      loadDOMDocument(fileURL, jaxpschema);
      
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

   //Evaluation of fn:id with given IDREF matches same element (Eliminates duplicates).
   public void test_fn_id_7() throws Exception {
      String inputFile = "/TestSources/id.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/MiscFunctions/fn-id-7.xq";
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpschema = loadSchema();
      loadDOMDocument(fileURL, jaxpschema);
      
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

      
      assertTrue("XPath Result Error " + xqFile + ":", actual.contains("=\"id2\""));
        

   }

   //Evaluation of fn:id with multiple IDREF matching more than one element (Eliminates duplicates).
   public void test_fn_id_8() throws Exception {
      String inputFile = "/TestSources/id.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/MiscFunctions/fn-id-8.xq";
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpschema = loadSchema();
      loadDOMDocument(fileURL, jaxpschema);
      
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

      assertTrue("XPath Result Error " + xqFile + ":", actual.contains("=\"id1\""));
      assertTrue("XPath Result Error " + xqFile + ":", actual.contains("=\"id2\""));
        

   }

   //Evaluation of fn:id with multiple IDREF, but only one matching element.
   public void test_fn_id_9() throws Exception {
      String inputFile = "/TestSources/id.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/MiscFunctions/fn-id-9.xq";
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpschema = loadSchema();
      loadDOMDocument(fileURL, jaxpschema);
      
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

      assertTrue("XPath Result Error " + xqFile + ":", actual.contains("=\"id1\""));
        

   }

   //Evaluation of fn:id with multiple IDREF, and none matching an element.
   public void test_fn_id_10() throws Exception {
      String inputFile = "/TestSources/id.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/MiscFunctions/fn-id-10.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/MiscFunctions/fn-id-10.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpschema = loadSchema();
      loadDOMDocument(fileURL, jaxpschema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);

   }

   //Evaluation of fn:id with multiple IDREF set to empty string.
   public void test_fn_id_11() throws Exception {
      String inputFile = "/TestSources/id.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/MiscFunctions/fn-id-11.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/MiscFunctions/fn-id-11.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpschema = loadSchema();
      loadDOMDocument(fileURL, jaxpschema);
      
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

   //Evaluation of fn:id function, where first argument is given as part of fn:substring function.
   public void test_fn_id_12() throws Exception {
      String inputFile = "/TestSources/id.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/MiscFunctions/fn-id-12.xq";
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpschema = loadSchema();
      loadDOMDocument(fileURL, jaxpschema);
      
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

      assertTrue("XPath Result Error " + xqFile + ":", actual.contains("=\"id3\""));
        

   }

   //Evaluation of fn:id, where the same IDREF makes reference to the same element.
   public void test_fn_id_13() throws Exception {
      String inputFile = "/TestSources/id.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/MiscFunctions/fn-id-13.xq";
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpschema = loadSchema();
      loadDOMDocument(fileURL, jaxpschema);
      
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

      assertTrue("XPath Result Error " + xqFile + ":", actual.contains("=\"id4\""));
        

   }

   //Evaluation of fn:id for for which the given the given IDREF contains a prefix.
   public void test_fn_id_14() throws Exception {
      String inputFile = "/TestSources/id.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/MiscFunctions/fn-id-14.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/MiscFunctions/fn-id-14.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpschema = loadSchema();
      loadDOMDocument(fileURL, jaxpschema);
      
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

   //Evaluation of fn:id for which all members of the IDREF list having the same value.
   public void test_fn_id_15() throws Exception {
      String inputFile = "/TestSources/id.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/MiscFunctions/fn-id-15.xq";
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpschema = loadSchema();
      loadDOMDocument(fileURL, jaxpschema);
      
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

      assertTrue("XPath Result Error " + xqFile + ":", actual.contains("=\"id1\""));
        

   }

   //Evaluation of fn:id for which all members of the IDREF list having the same value (but different cases).
   public void test_fn_id_16() throws Exception {
      String inputFile = "/TestSources/id.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/MiscFunctions/fn-id-16.xq";
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpschema = loadSchema();
      loadDOMDocument(fileURL, jaxpschema);
      
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

      assertTrue("XPath Result Error " + xqFile + ":", actual.contains("=\"id1\""));
        

   }

   //Evaluation of fn:id for which the give IDREF uses the lower-case function.
   public void test_fn_id_17() throws Exception {
      String inputFile = "/TestSources/id.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/MiscFunctions/fn-id-17.xq";
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpschema = loadSchema();
      loadDOMDocument(fileURL, jaxpschema);
      
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

      assertTrue("XPath Result Error " + xqFile + ":", actual.contains("=\"id1\""));
        

   }

   //Evaluation of fn:id for which the give IDREF uses the upper-case function.
   public void test_fn_id_18() throws Exception {
      String inputFile = "/TestSources/id.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/MiscFunctions/fn-id-18.xq";
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpschema = loadSchema();

      loadDOMDocument(fileURL, jaxpschema);
      
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

      assertTrue("XPath Result Error " + xqFile + ":", actual.contains("=\"ID5\""));
        

   }

   //Evaluation of fn:id for which the give IDREF uses the fn:concat function.
   public void test_fn_id_19() throws Exception {
      String inputFile = "/TestSources/id.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/MiscFunctions/fn-id-19.xq";
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpschema = loadSchema();
      loadDOMDocument(fileURL, jaxpschema);
      
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

      assertTrue("XPath Result Error " + xqFile + ":", actual.contains("=\"id1\""));
        

   }

   //Evaluation of fn:id for which the give IDREF uses the xs:string function.
   public void test_fn_id_20() throws Exception {
      String inputFile = "/TestSources/id.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/MiscFunctions/fn-id-20.xq";
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpschema = loadSchema();
      loadDOMDocument(fileURL, jaxpschema);
      
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

      assertTrue("XPath Result Error " + xqFile + ":", actual.contains("=\"id1\""));
        

   }

   //Evaluation of fn:id for which the give IDREF uses the fn:string-join function.
   public void test_fn_id_21() throws Exception {
      String inputFile = "/TestSources/id.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/MiscFunctions/fn-id-21.xq";
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpschema = loadSchema();
      loadDOMDocument(fileURL, jaxpschema);
      
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

      assertTrue("XPath Result Error " + xqFile + ":", actual.contains("=\"id1\""));
        

   }

   //Evaluation of fn:id together with declare ordering.
   public void test_fn_id_23() throws Exception {
      String inputFile = "/TestSources/id.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/MiscFunctions/fn-id-23.xq";
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpschema = loadSchema();
      loadDOMDocument(fileURL, jaxpschema);
      
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

      assertTrue("XPath Result Error " + xqFile + ":", actual.contains("=\"id1\""));
      assertTrue("XPath Result Error " + xqFile + ":", actual.contains("=\"id2\""));
        

   }

   //Evaluation of the fn:data whose argument is a complex type with element only content.
   public void test_fn_data_1() throws Exception {
      String inputFile = "/TestSources/examples.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/MiscFunctions/fn-data-1.xq";
      URL fileURL = bundle.getEntry(inputFile);
      Schema jaxpschema = loadSchema();
      loadDOMDocument(fileURL, jaxpschema);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = buildResultString(rs);
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertTrue("XPath Result Error " + xqFile + ":", actual.contains("87"));
      assertTrue("XPath Result Error " + xqFile + ":", actual.contains("1.5"));
        

   }

}
      