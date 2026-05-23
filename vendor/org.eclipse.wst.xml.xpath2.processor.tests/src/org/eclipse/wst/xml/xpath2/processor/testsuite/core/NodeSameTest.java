
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
      
      
public class NodeSameTest extends AbstractPsychoPathTest {

   //Evaluation of a Node expression With the operands/operator set with the following format: empty Sequence is empty Sequence.
   public void test_nodeexpression1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeSame/nodeexpression1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeSame/nodeexpression1.txt";
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

   //Evaluation of a Node expression With the operands/operator set with the following format: empty Sequence is Single Node Element.
   public void test_nodeexpression3() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeSame/nodeexpression3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeSame/nodeexpression3.txt";
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

   //Evaluation of a Node expression With the operands/operator set with the following format: empty Sequence is Sequence of single Element Node.
   public void test_nodeexpression4() throws Exception {
      String inputFile = "/TestSources/staff.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeSame/nodeexpression4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeSame/nodeexpression4.txt";
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

   //Evaluation of a Node expression With the operands/operator set with the following format: Single Node Element is empty Sequence.
   public void test_nodeexpression9() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeSame/nodeexpression9.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeSame/nodeexpression9.txt";
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

   //Evaluation of a Node expression With the operands/operator set with the following format: Single Node Element is Single Node Element.
   public void test_nodeexpression11() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeSame/nodeexpression11.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeSame/nodeexpression11.txt";
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

   //Evaluation of a Node expression With the operands/operator set with the following format: Single Node Element is Sequence of single Element Node.
   public void test_nodeexpression12() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String inputFile2 = "/TestSources/staff.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeSame/nodeexpression12.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeSame/nodeexpression12.txt";
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

   //Evaluation of a Node expression With the operands/operator set with the following format: Sequence of single Element Node is empty Sequence.
   public void test_nodeexpression13() throws Exception {
      String inputFile = "/TestSources/staff.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeSame/nodeexpression13.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeSame/nodeexpression13.txt";
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

   //Evaluation of a Node expression With the operands/operator set with the following format: Sequence of single Element Node is Single Node Element.
   public void test_nodeexpression15() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String inputFile2 = "/TestSources/staff.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeSame/nodeexpression15.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeSame/nodeexpression15.txt";
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

   //Evaluation of a Node expression With the operands/operator set with the following format: Sequence of single Element Node is Sequence of single Element Node.
   public void test_nodeexpression16() throws Exception {
      String inputFile = "/TestSources/staff.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeSame/nodeexpression16.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeSame/nodeexpression16.txt";
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

   //Evaluation of a node expression used as argument to a fn:not function.
   public void test_nodeexpressionhc1() throws Exception {
      String inputFile = "/TestSources/staff.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeSame/nodeexpressionhc1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeSame/nodeexpressionhc1.txt";
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

   //Evaluation of a node expression used as part of a boolean less than expression (lt operator).
   public void test_nodeexpressionhc2() throws Exception {
      String inputFile = "/TestSources/staff.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeSame/nodeexpressionhc2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeSame/nodeexpressionhc2.txt";
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

   //Evaluation of a node expression used as part of a boolean less than expression (ge operator).
   public void test_nodeexpressionhc3() throws Exception {
      String inputFile = "/TestSources/staff.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeSame/nodeexpressionhc3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeSame/nodeexpressionhc3.txt";
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

   //Evaluation of a node expression used as part of a boolean greater than expression (gt operator).
   public void test_nodeexpressionhc4() throws Exception {
      String inputFile = "/TestSources/staff.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeSame/nodeexpressionhc4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeSame/nodeexpressionhc4.txt";
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

   //Evaluation of a node expression used as part of a boolean greater than expression (ge operator).
   public void test_nodeexpressionhc5() throws Exception {
      String inputFile = "/TestSources/staff.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeSame/nodeexpressionhc5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeSame/nodeexpressionhc5.txt";
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

   //Evaluation of the "is-same-node" operator with one of the operands is not a single node or the empty sequence.
   public void test_nodecomparisonerr_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeSame/nodecomparisonerr-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeSame/nodecomparisonerr-1.txt";
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
      