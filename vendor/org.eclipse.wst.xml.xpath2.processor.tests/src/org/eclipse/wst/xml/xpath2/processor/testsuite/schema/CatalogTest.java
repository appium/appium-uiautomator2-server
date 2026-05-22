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

import org.apache.xerces.xs.XSModel;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.StaticError;
import org.eclipse.wst.xml.xpath2.processor.XPathParserException;
import org.eclipse.wst.xml.xpath2.processor.test.AbstractPsychoPathTest;
      
      
public class CatalogTest extends AbstractPsychoPathTest {

   //Check variable name is used, if source is not "emptydoc".
   public void test_Catalog001() throws Exception {
      String inputFile = "/XQTSCatalog.xml";
      String xqFile = "/Queries/XQuery/Catalog/Catalog001.xq";
      String resultFile = "/ExpectedTestResults/Catalog/Catalog001.xml";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "for $x in $input-context//*:input-file[count(@variable) = 0 and ./text() != \"emptydoc\"] return string($x/../@name)";
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = "<missing-variable>" + buildXMLResultString(rs) + "</missing-variable>";
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertXMLEqual("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Test all standard test cases have at least one output file.
   public void test_Catalog002() throws Exception {
      String inputFile = "/XQTSCatalog.xml";
      String xqFile = "/Queries/XQuery/Catalog/Catalog002.xq";
      String resultFile = "/ExpectedTestResults/Catalog/Catalog002.xml";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "$input-context//*:test-case[@scenario = \"standard\"][fn:count(*:output-file) = 0]/@name/string()";
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = "<standard-no-outputfile>" + buildXMLResultString(rs) + "</standard-no-outputfile>";
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertXMLEqual("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Test all error test cases have at least one expected error.
   public void test_Catalog003() throws Exception {
      String inputFile = "/XQTSCatalog.xml";
      String xqFile = "/Queries/XQuery/Catalog/Catalog003.xq";
      String resultFile = "/ExpectedTestResults/Catalog/Catalog003.xml";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = 
    	  "$input-context//*:test-case[@scenario = \"runtime-error\" or @scenario = \"parse-error\"][fn:count(*:expected-error) = 0 and fn:count(*:output-file[@compare = \"Inspect\"]) = 0]/@name/string()";
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = "<error-no-expected-error>" + buildXMLResultString(rs) + "</error-no-expected-error>";
	
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
      