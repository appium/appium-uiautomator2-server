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

package org.eclipse.wst.xml.xpath2.processor.testsuite.functions;

import java.net.URL;

import org.apache.xerces.xs.XSModel;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.StaticError;
import org.eclipse.wst.xml.xpath2.processor.XPathParserException;
import org.eclipse.wst.xml.xpath2.processor.test.AbstractPsychoPathTest;
      
      
public class SeqIDFuncTest extends AbstractPsychoPathTest {

   //Evaluation of fn:id with second argument ommited an no context node.
   public void test_fn_id_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeSeqFunc/SeqIDFunc/fn-id-2.xq";

      String expectedResult = "XPDY0002";
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "fn:id(\"argument1\")";
      String actual = null;
      try {
	   	  compileXPath(xpath);
	
	      ResultSequence rs = evaluate(null); // no context
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

   //Evaluation of fn:id with given IDREF matching a single element.  This test uses a DTD.
   public void test_fn_id_dtd_5() throws Exception {
      String inputFile = "/TestSources/iddtd.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeSeqFunc/SeqIDFunc/fn-id-dtd-5.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeSeqFunc/SeqIDFunc/fn-id-dtd-5.txt";
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

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of fn:id with given IDREF does not match any element.  This test uses a DTD.
   public void test_fn_id_dtd_6() throws Exception {
      String inputFile = "/TestSources/iddtd.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeSeqFunc/SeqIDFunc/fn-id-dtd-6.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeSeqFunc/SeqIDFunc/fn-id-dtd-6.txt";
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

   //Evaluation of fn:id with given IDREF matches same element (Eliminates duplicates).  This test uses a DTD.
   public void test_fn_id_dtd_7() throws Exception {
      String inputFile = "/TestSources/iddtd.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeSeqFunc/SeqIDFunc/fn-id-dtd-7.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeSeqFunc/SeqIDFunc/fn-id-dtd-7.txt";
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

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of fn:id with multiple IDREF matching more than one element (Eliminates duplicates).  This test uses a DTD.
   public void test_fn_id_dtd_8() throws Exception {
      String inputFile = "/TestSources/iddtd.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeSeqFunc/SeqIDFunc/fn-id-dtd-8.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeSeqFunc/SeqIDFunc/fn-id-dtd-8.txt";
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

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of fn:id with multiple IDREF, but only one matching element.  This test uses a DTD.
   public void test_fn_id_dtd_9() throws Exception {
      String inputFile = "/TestSources/iddtd.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeSeqFunc/SeqIDFunc/fn-id-dtd-9.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeSeqFunc/SeqIDFunc/fn-id-dtd-9.txt";
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

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of fn:id with multiple IDREF, and none matching an element.  This test uses a DTD.
   public void test_fn_id_dtd_10() throws Exception {
      String inputFile = "/TestSources/iddtd.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeSeqFunc/SeqIDFunc/fn-id-dtd-10.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeSeqFunc/SeqIDFunc/fn-id-dtd-10.txt";
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

   //Evaluation of fn:id with multiple IDREF set to empty string.  This test uses a DTD.
   public void test_fn_id_dtd_11() throws Exception {
      String inputFile = "/TestSources/iddtd.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeSeqFunc/SeqIDFunc/fn-id-dtd-11.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeSeqFunc/SeqIDFunc/fn-id-dtd-11.txt";
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

   //Evaluation of fn:id function, where first argument is given as part of fn:substring function.  This test uses a DTD.
   public void test_fn_id_dtd_12() throws Exception {
      String inputFile = "/TestSources/iddtd.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeSeqFunc/SeqIDFunc/fn-id-dtd-12.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeSeqFunc/SeqIDFunc/fn-id-dtd-12.txt";
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

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of fn:id, where the same IDREF makes reference to the same element.  This test uses a DTD.
   public void test_fn_id_dtd_13() throws Exception {
      String inputFile = "/TestSources/iddtd.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeSeqFunc/SeqIDFunc/fn-id-dtd-13.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeSeqFunc/SeqIDFunc/fn-id-dtd-13.txt";
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

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of fn:id for for which the given the given IDREF contains a prefix.  This test uses a DTD.
   public void test_fn_id_dtd_14() throws Exception {
      String inputFile = "/TestSources/iddtd.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeSeqFunc/SeqIDFunc/fn-id-dtd-14.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeSeqFunc/SeqIDFunc/fn-id-dtd-14.txt";
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

   //Evaluation of fn:id for which all members of the IDREF list having the same value.  This test uses a DTD.
   public void test_fn_id_dtd_15() throws Exception {
      String inputFile = "/TestSources/iddtd.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeSeqFunc/SeqIDFunc/fn-id-dtd-15.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeSeqFunc/SeqIDFunc/fn-id-dtd-15.txt";
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

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of fn:id for which all members of the IDREF list having the same value (but different cases).  This test uses a DTD.
   public void test_fn_id_dtd_16() throws Exception {
      String inputFile = "/TestSources/iddtd.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeSeqFunc/SeqIDFunc/fn-id-dtd-16.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeSeqFunc/SeqIDFunc/fn-id-dtd-16.txt";
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

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of fn:id for which the give IDREF uses the lower-case function.  This test uses a DTD.
   public void test_fn_id_dtd_17() throws Exception {
      String inputFile = "/TestSources/iddtd.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeSeqFunc/SeqIDFunc/fn-id-dtd-17.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeSeqFunc/SeqIDFunc/fn-id-dtd-17.txt";
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

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of fn:id for which the give IDREF uses the upper-case function.  This test uses a DTD.
   public void test_fn_id_dtd_18() throws Exception {
      String inputFile = "/TestSources/iddtd.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeSeqFunc/SeqIDFunc/fn-id-dtd-18.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeSeqFunc/SeqIDFunc/fn-id-dtd-18.txt";
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

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of fn:id for which the give IDREF uses the fn:concat function.  This test uses a DTD.
   public void test_fn_id_dtd_19() throws Exception {
      String inputFile = "/TestSources/iddtd.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeSeqFunc/SeqIDFunc/fn-id-dtd-19.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeSeqFunc/SeqIDFunc/fn-id-dtd-19.txt";
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

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of fn:id for which the give IDREF uses the xs:string function.  This test uses a DTD.
   public void test_fn_id_dtd_20() throws Exception {
      String inputFile = "/TestSources/iddtd.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeSeqFunc/SeqIDFunc/fn-id-dtd-20.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeSeqFunc/SeqIDFunc/fn-id-dtd-20.txt";
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

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of fn:id for which the give IDREF uses the fn:string-join function.  This test uses a DTD.
   public void test_fn_id_dtd_21() throws Exception {
      String inputFile = "/TestSources/iddtd.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeSeqFunc/SeqIDFunc/fn-id-dtd-21.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeSeqFunc/SeqIDFunc/fn-id-dtd-21.txt";
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

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of fn:id with second argument set to "." an no context node.
   public void test_fn_id_22() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeSeqFunc/SeqIDFunc/fn-id-22.xq";
      String expectedResult = "XPDY0002";
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "fn:id(\"argument1\", .)";
      String actual = null;
      try {
	   	  compileXPath(xpath);
	
	      ResultSequence rs = evaluate(null); // no context
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

   //Evaluation of fn:id together with declare ordering.  This test uses a DTD.
   public void test_fn_id_dtd_23() throws Exception {
      String inputFile = "/TestSources/iddtd.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeSeqFunc/SeqIDFunc/fn-id-dtd-23.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeSeqFunc/SeqIDFunc/fn-id-dtd-23.txt";
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

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

}
      