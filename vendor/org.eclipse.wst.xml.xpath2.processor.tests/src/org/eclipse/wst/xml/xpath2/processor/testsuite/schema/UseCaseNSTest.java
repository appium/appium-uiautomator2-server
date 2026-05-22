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

package org.eclipse.wst.xml.xpath2.processor.testsuite.schema;

import java.net.URL;

import org.apache.xerces.xs.XSModel;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.StaticError;
import org.eclipse.wst.xml.xpath2.processor.XPathParserException;
import org.eclipse.wst.xml.xpath2.processor.test.AbstractPsychoPathTest;
      
      
public class UseCaseNSTest extends AbstractPsychoPathTest {

   //Select the title of each record that is for sale.
   public void test_ns_queries_results_q2() throws Exception {
      String inputFile = "/TestSources/auction.xml";
      String xqFile = "/Queries/XQuery/UseCase/UseCaseNS/ns-queries-results-q2.xq";
      String resultFile = "/ExpectedTestResults/UseCase/UseCaseNS/ns-queries-results-q2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("music","http://www.example.org/music/records");
      

      String xpath = "$input-context//music:title";
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = "<Q2>" + buildXMLResultString(rs) + "</Q2>";
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertXMLEqual("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Select all elements that have an attribute whose name is in the XML Schema namespace.
   public void test_ns_queries_results_q3() throws Exception {
      String inputFile = "/TestSources/auction.xml";
      String xqFile = "/Queries/XQuery/UseCase/UseCaseNS/ns-queries-results-q3.xq";
      String resultFile = "/ExpectedTestResults/UseCase/UseCaseNS/ns-queries-results-q3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);
      addNamespace("dt", "http://www.w3.org/2001/XMLSchema");

      String xpath = "$input-context//*[@dt:*]";
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = "<Q3>" + buildXMLResultString(rs) + "</Q3>";
	
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
      