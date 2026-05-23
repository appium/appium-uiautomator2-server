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
      
      
public class UseCaseSGMLTest extends AbstractPsychoPathTest {

   //Locate all paragraphs in the report (all "para" elements occurring anywhere within the "report" element).
   public void test_sgml_queries_results_q1() throws Exception {
      String inputFile = "/TestSources/sgml.xml";
      String xqFile = "/Queries/XQuery/UseCase/UseCaseSGML/sgml-queries-results-q1.xq";
      String resultFile = "/ExpectedTestResults/UseCase/UseCaseSGML/sgml-queries-results-q1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "$input-context//report//para";
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

   //Locate all paragraph elements in an introduction (all "para" elements directly contained within an "intro" element).
   public void test_sgml_queries_results_q2() throws Exception {
      String inputFile = "/TestSources/sgml.xml";
      String xqFile = "/Queries/XQuery/UseCase/UseCaseSGML/sgml-queries-results-q2.xq";
      String resultFile = "/ExpectedTestResults/UseCase/UseCaseSGML/sgml-queries-results-q2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "$input-context//intro/para";
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = "<result>" +buildXMLResultString(rs) + "</result>";
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertXMLEqual("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Locate the second paragraph in the third section in the second chapter (the second "para" element occurring in the third "section" element occurring in the second "chapter" element occurring in the "report").
   public void test_sgml_queries_results_q4() throws Exception {
      String inputFile = "/TestSources/sgml.xml";
      String xqFile = "/Queries/XQuery/UseCase/UseCaseSGML/sgml-queries-results-q4.xq";
      String resultFile = "/ExpectedTestResults/UseCase/UseCaseSGML/sgml-queries-results-q4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "((($input-context//chapter)[2]//section)[3]//para)[2]";
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

   //Locate all classified paragraphs (all "para" elements whose "security" attribute has the value "c").
   public void test_sgml_queries_results_q5() throws Exception {
      String inputFile = "/TestSources/sgml.xml";
      String xqFile = "/Queries/XQuery/UseCase/UseCaseSGML/sgml-queries-results-q5.xq";
      String resultFile = "/ExpectedTestResults/UseCase/UseCaseSGML/sgml-queries-results-q5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "$input-context//para[@security = \"c\"]";
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

   //Locate all sections with a title that has "is SGML" in it. The string may occur anywhere in the descendants of the title element, and markup boundaries are ignored.
   public void test_sgml_queries_results_q8a() throws Exception {
      String inputFile = "/TestSources/sgml.xml";
      String xqFile = "/Queries/XQuery/UseCase/UseCaseSGML/sgml-queries-results-q8a.xq";
      String resultFile = "/ExpectedTestResults/UseCase/UseCaseSGML/sgml-queries-results-q8a.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "$input-context//section[.//title[contains(., \"is SGML\")]]";
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

   //Same as (Q8a), but the string "is SGML" cannot be interrupted by sub-elements, and must appear in a single text node.
   public void test_sgml_queries_results_q8b() throws Exception {
      String inputFile = "/TestSources/sgml.xml";
      String xqFile = "/Queries/XQuery/UseCase/UseCaseSGML/sgml-queries-results-q8b.xq";
      String resultFile = "/ExpectedTestResults/UseCase/UseCaseSGML/sgml-queries-results-q8b.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "$input-context//section[.//title/text()[contains(., \"is SGML\")]]";
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

   //Locate all the topics referenced by a cross-reference anywhere in the report (all the "topic" elements whose "topicid" attribute value is the same as an "xrefid" attribute value of any "xref" element).
   public void test_sgml_queries_results_q9() throws Exception {
      String inputFile = "/TestSources/sgml.xml";
      String xqFile = "/Queries/XQuery/UseCase/UseCaseSGML/sgml-queries-results-q9.xq";
      String resultFile = "/ExpectedTestResults/UseCase/UseCaseSGML/sgml-queries-results-q9.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "for $id in $input-context//xref/@xrefid " +
                     "return $input-context//topic[@topicid = $id]";
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

}
      