
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
package org.eclipse.wst.xml.xpath2.processor.testsuite.core;

import java.net.URL;

import org.apache.xerces.xs.XSModel;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.StaticError;
import org.eclipse.wst.xml.xpath2.processor.XPathParserException;
import org.eclipse.wst.xml.xpath2.processor.test.AbstractPsychoPathTest;

// NodeTests need to compare based on Fragments.  We which means need to compare DOM outputs.
public class NodeTestTest extends AbstractPsychoPathTest {

   //Simple test for comment() node type.
   public void test_NodeTest001() throws Exception {
      String inputFile = "/TestSources/bib2.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/NodeTest/NodeTest001.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/NodeTest/NodeTest001.xml";
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

   //Simple test for processing-instruction() node test.
   public void test_NodeTest002() throws Exception {
      String inputFile = "/TestSources/bib2.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/NodeTest/NodeTest002.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/NodeTest/NodeTest002.xml";
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

   //Simple test for node type text().
   public void test_NodeTest006() throws Exception {
      String inputFile = "/TestSources/bib.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/NodeTest/NodeTest006.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/NodeTest/NodeTest006.xml";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      //String xpath = extractXPathExpression(xqFile, inputFile);
      String xpath = "$input-context/bib/book/editor/affiliation/text()";
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

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //processing-instruction() finds no descendant PIs of the given name anywhere.
   public void test_NodeTest007_1() throws Exception {
      String inputFile = "/TestSources/TreeEmpty.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/NodeTest/NodeTest007.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/NodeTest/NodeTest007-1.xml";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "fn:count($input-context//processing-instruction('a-pi'))";
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = "<out>" + buildResultString(rs) + "</out>";
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //processing-instruction() gets all PIs of the given name, including those off root.
   public void test_NodeTest007_2() throws Exception {
      String inputFile = "/TestSources/TopMany.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/NodeTest/NodeTest007.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/NodeTest/NodeTest007-2.xml";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "fn:count($input-context//processing-instruction('a-pi'))";
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = "<out>" + buildResultString(rs) + "</out>";
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //text() gets no descendant text nodes.
   public void test_NodeTest008_1() throws Exception {
      String inputFile = "/TestSources/Tree1Child.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/NodeTest/NodeTest008.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/NodeTest/NodeTest008-1.xml";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "fn:count($input-context//center/text())";
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = "<out>" + buildResultString(rs) + "</out>";
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //text() gets text nodes.
   public void test_NodeTest008_2() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/NodeTest/NodeTest008.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/NodeTest/NodeTest008-2.xml";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "fn:count($input-context//center/text())";
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = "<out>" + buildResultString(rs) + "</out>";
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //comment() gets no descendant comment nodes.
   public void test_NodeTest009_1() throws Exception {
      String inputFile = "/TestSources/Tree1Child.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/NodeTest/NodeTest009.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/NodeTest/NodeTest009-1.xml";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "fn:count($input-context//center/comment())";
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = "<out>" + buildResultString(rs) + "</out>";
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //comment() gets comment nodes.
   public void test_NodeTest009_2() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/NodeTest/NodeTest009.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/NodeTest/NodeTest009-2.xml";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "fn:count($input-context//center/comment())";
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = "<out>" + buildResultString(rs) + "</out>";
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ////comment() gets no comment nodes anywhere.
   public void test_NodeTest010_1() throws Exception {
      String inputFile = "/TestSources/TreeEmpty.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/NodeTest/NodeTest010.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/NodeTest/NodeTest010-1.xml";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "fn:count($input-context//comment())";
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = "<out>" + buildResultString(rs) + "</out>";
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   ////comment() gets all comments, including those off root.
   public void test_NodeTest010_2() throws Exception {
      String inputFile = "/TestSources/TopMany.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/NodeTest/NodeTest010.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/NodeTest/NodeTest010-2.xml";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "fn:count($input-context//comment())";
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = "<out>" + buildResultString(rs) + "</out>";
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //processing-instruction() finds no descendant PIs.
   public void test_NodeTest011_1() throws Exception {
      String inputFile = "/TestSources/Tree1Child.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/NodeTest/NodeTest011.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/NodeTest/NodeTest011-1.xml";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "fn:count($input-context//center/processing-instruction())";
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = "<out>" + buildResultString(rs) + "</out>";
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //processing-instruction() gets all descendant PIs.
   public void test_NodeTest011_2() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/NodeTest/NodeTest011.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/NodeTest/NodeTest011-2.xml";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "fn:count($input-context//center/processing-instruction())";
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = "<out>" + buildResultString(rs) + "</out>";
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //processing-instruction() finds no descendant PIs anywhere.
   public void test_NodeTest012_1() throws Exception {
      String inputFile = "/TestSources/TreeEmpty.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/NodeTest/NodeTest012.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/NodeTest/NodeTest012-1.xml";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "fn:count($input-context//processing-instruction())";
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = "<out>" + buildResultString(rs) + "</out>";
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //processing-instruction() gets all PIs, including those off root.
   public void test_NodeTest012_2() throws Exception {
      String inputFile = "/TestSources/TopMany.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/NodeTest/NodeTest012.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/NodeTest/NodeTest012-2.xml";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "fn:count($input-context//processing-instruction())";
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = "<out>" + buildResultString(rs) + "</out>";
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //processing-instruction() gets descendant PIs of the given name.
   public void test_NodeTest013_1() throws Exception {
      String inputFile = "/TestSources/TreeCompass.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/NodeTest/NodeTest013.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/NodeTest/NodeTest013-1.xml";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "fn:count($input-context//center/processing-instruction('a-pi'))";
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = "<out>" + buildResultString(rs) + "</out>";
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Simple test involving a text node and a boolean expression (and fn:true()).
   public void test_NodeTesthc_1() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/NodeTest/NodeTesthc-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/NodeTest/NodeTesthc-1.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "$input-context1//text() and fn:true()";
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = "<result>" + buildResultString(rs) + "</result>";
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Simple test involving a text node and a boolean expression (or fn:true()).
   public void test_NodeTesthc_2() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/NodeTest/NodeTesthc-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/NodeTest/NodeTesthc-2.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "$input-context1//text() or fn:true()";
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = "<result>" + buildResultString(rs) + "</result>";
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Simple test involving a text node and a boolean expression (and fn:false()).
   public void test_NodeTesthc_3() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/NodeTest/NodeTesthc-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/NodeTest/NodeTesthc-3.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "$input-context1//text() and fn:false()";
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = "<result>" + buildResultString(rs) + "</result>";
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Simple test involving a text node and a boolean expression (or fn:false()).
   public void test_NodeTesthc_4() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/NodeTest/NodeTesthc-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/NodeTest/NodeTesthc-4.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "$input-context1//text() or fn:false()";
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = "<result>" + buildResultString(rs) + "</result>";
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Simple test involving Element node types and a boolean expression (or fn:false()).
   public void test_NodeTesthc_5() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/NodeTest/NodeTesthc-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/NodeTest/NodeTesthc-5.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "$input-context1//overtime/node() or fn:false()";
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = "<result>" + buildResultString(rs) + "</result>";
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Simple test involving Element node types and a boolean expression (or fn:true()).
   public void test_NodeTesthc_6() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/NodeTest/NodeTesthc-6.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/NodeTest/NodeTesthc-6.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "$input-context1//overtime/node() or fn:true()";
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = "<result>" + buildResultString(rs) + "</result>";
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Simple test involving Element node types and a boolean expression (and fn:false()).
   public void test_NodeTesthc_7() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/NodeTest/NodeTesthc-7.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/NodeTest/NodeTesthc-7.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "$input-context1//overtime/node() and fn:false()";
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = "<result>" + buildResultString(rs) + "</result>";
	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Simple test involving Element node types and a boolean expression (and fn:true()).
   public void test_NodeTesthc_8() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/Steps/NodeTest/NodeTesthc-8.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/Steps/NodeTest/NodeTesthc-8.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "$input-context1//overtime/node() and fn:true()";
      String actual = null;
      try {
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);

         
          actual = "<result>" + buildResultString(rs) + "</result>";
	
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
      