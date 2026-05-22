
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
      
      
public class NodeAfterTest extends AbstractPsychoPathTest {

   //Evaluation of a Node expression With the operands/operator set with the following format: empty Sequence ">>" empty Sequence.
   public void test_nodeexpression33() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeAfter/nodeexpression33.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeAfter/nodeexpression33.txt";
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

   //Evaluation of a Node expression With the operands/operator set with the following format: empty Sequence ">>" Single Node Element.
   public void test_nodeexpression35() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeAfter/nodeexpression35.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeAfter/nodeexpression35.txt";
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

   //Evaluation of a Node expression With the operands/operator set with the following format: empty Sequence ">>" Sequence of single Element Node.
   public void test_nodeexpression36() throws Exception {
      String inputFile = "/TestSources/staff.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeAfter/nodeexpression36.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeAfter/nodeexpression36.txt";
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

   //Evaluation of a Node expression With the operands/operator set with the following format: Single Node Element ">>" empty Sequence.
   public void test_nodeexpression41() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeAfter/nodeexpression41.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeAfter/nodeexpression41.txt";
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

   //Evaluation of a Node expression With the operands/operator set with the following format: Single Node Element ">>" Single Node Element.
   public void test_nodeexpression43() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeAfter/nodeexpression43.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeAfter/nodeexpression43.txt";
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

   //Evaluation of a Node expression With the operands/operator set with the following format: Single Node Element ">>" Sequence of single Element Node.
   public void test_nodeexpression44() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String inputFile2 = "/TestSources/staff.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeAfter/nodeexpression44.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeAfter/nodeexpression44.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      URL fileURL2 = bundle.getEntry(inputFile2);
      load2DOMDocument(fileURL, fileURL2);
      
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

   //Evaluation of a Node expression With the operands/operator set with the following format: Sequence of single Element Node ">>" empty Sequence.
   public void test_nodeexpression45() throws Exception {
      String inputFile = "/TestSources/staff.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeAfter/nodeexpression45.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeAfter/nodeexpression45.txt";
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

   //Evaluation of a Node expression With the operands/operator set with the following format: Sequence of single Element Node ">>" Single Node Element.
   public void test_nodeexpression47() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String inputFile2 = "/TestSources/staff.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeAfter/nodeexpression47.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeAfter/nodeexpression47.txt";
      String expectedResult = getExpectedResult(resultFile);
      URL fileURL = bundle.getEntry(inputFile);
      URL fileURL2 = bundle.getEntry(inputFile2);
      load2DOMDocument(fileURL, fileURL2);
      
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

   //Evaluation of a Node expression With the operands/operator set with the following format: Sequence of single Element Node ">>" Sequence of single Element Node.
   public void test_nodeexpression48() throws Exception {
      String inputFile = "/TestSources/staff.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeAfter/nodeexpression48.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeAfter/nodeexpression48.txt";
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

   //Evaluation of a node after expression used as an argument to an fn:not function.
   public void test_nodeexpressionhc11() throws Exception {
      String inputFile = "/TestSources/staff.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeAfter/nodeexpressionhc11.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeAfter/nodeexpressionhc11.txt";
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

   //Evaluation of a node after expression used as part of a boolean-less-than expression (lt operator).
   public void test_nodeexpressionhc12() throws Exception {
      String inputFile = "/TestSources/staff.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeAfter/nodeexpressionhc12.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeAfter/nodeexpressionhc12.txt";
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

   //Evaluation of a node after expression used as part of a boolean-less-than expression (ge operator).
   public void test_nodeexpressionhc13() throws Exception {
      String inputFile = "/TestSources/staff.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeAfter/nodeexpressionhc13.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeAfter/nodeexpressionhc13.txt";
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

   //Evaluation of a node after expression used as part of a boolean-greater-than expression (gt operator).
   public void test_nodeexpressionhc14() throws Exception {
      String inputFile = "/TestSources/staff.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeAfter/nodeexpressionhc14.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeAfter/nodeexpressionhc14.txt";
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

   //Evaluation of a node after expression used as part of a boolean-greater-than expression (le operator).
   public void test_nodeexpressionhc15() throws Exception {
      String inputFile = "/TestSources/staff.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeAfter/nodeexpressionhc15.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeAfter/nodeexpressionhc15.txt";
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

   //Evaluation of the "node-after" operator with one of the operands is not a single node or the empty sequence.
   public void test_nodecomparisonerr_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeAfter/nodecomparisonerr-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeAfter/nodecomparisonerr-3.txt";
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

}
      