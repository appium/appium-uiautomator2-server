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
      
      
public class NumericEqualSITest extends AbstractPsychoPathTest {

   //Evaluation of numeric value comparison involving type promotion to least common type. Uses "eq" operator between integer and float types.
   public void test_value_comparison_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/NumericEqualSI/value-comparison-3.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/NumericEqualSI/value-comparison-3.txt";
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

   //Evaluation of numeric value comparison involving type promotion to least common type. Uses "ne" operator between integer and float types.
   public void test_value_comparison_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/SchemaImport/NumericEqualSI/value-comparison-4.xq";
      String resultFile = "/ExpectedTestResults/SchemaImport/NumericEqualSI/value-comparison-4.txt";
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

   private Schema loadSchema() throws SAXException {
		String schemaFile = "/TestSources/userdefined.xsd";
	      SchemaFactory schemaFactory = new XMLSchemaFactory();
	      URL schemaURL = bundle.getEntry(schemaFile);
	      Schema jaxpschema = schemaFactory.newSchema(schemaURL);
		return jaxpschema;
	}
   
}
      