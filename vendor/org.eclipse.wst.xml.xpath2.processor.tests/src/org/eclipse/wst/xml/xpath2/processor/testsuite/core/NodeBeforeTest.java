
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
      
      
public class NodeBeforeTest extends AbstractPsychoPathTest {

   //Evaluation of a Node expression With the operands/operator set with the following format: empty Sequence "<<" empty Sequence.
   public void test_nodeexpression17() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeBefore/nodeexpression17.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeBefore/nodeexpression17.txt";
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

   //Evaluation of a Node expression With the operands/operator set with the following format: empty Sequence "<<" Single Node Element.
   public void test_nodeexpression19() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeBefore/nodeexpression19.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeBefore/nodeexpression19.txt";
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

   //Evaluation of a Node expression With the operands/operator set with the following format: empty Sequence "<<" Sequence of single Element Node.
   public void test_nodeexpression20() throws Exception {
      String inputFile = "/TestSources/staff.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeBefore/nodeexpression20.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeBefore/nodeexpression20.txt";
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

   //Evaluation of a Node expression With the operands/operator set with the following format: Single Node Element "<<" empty Sequence.
   public void test_nodeexpression25() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeBefore/nodeexpression25.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeBefore/nodeexpression25.txt";
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

   //Evaluation of a Node expression With the operands/operator set with the following format: Single Node Element "<<" Single Node Element.
   public void test_nodeexpression27() throws Exception {
      String inputFile = "/TestSources/works.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeBefore/nodeexpression27.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeBefore/nodeexpression27.txt";
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

   //Evaluation of a Node expression With the operands/operator set with the following format: Single Node Element "<<" Sequence of single Element Node.
//   public void test_nodeexpression28() throws Exception {
//      String inputFile = "/TestSources/works.xml";
//      String inputFile2 = "/TestSources/staff.xml";
//      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeBefore/nodeexpression28.xq";
//      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeBefore/nodeexpression28.txt";
//      String expectedResult = getExpectedResult(resultFile);
//      URL fileURL = bundle.getEntry(inputFile);
//      URL fileURL2 = bundle.getEntry(inputFile2);
//
//      loadDOMDocument(fileURL);
//      
//      // Get XML Schema Information for the Document
//      XSModel schema = getGrammar();
//
//      setupDynamicContext(schema);
//
//      String xpath = extractXPathExpression(xqFile, inputFile);
//      String actual = null;
//      try {
//	   	  XPath path = compileXPath(xpath);
//	
//	      Evaluator eval = new DefaultEvaluator(dc, domDoc);
//	      ResultSequence rs = eval.evaluate(path);
//         
//          actual = buildResultString(rs);
//	
//      } catch (XPathParserException ex) {
//    	 actual = ex.code();
//      } catch (StaticError ex) {
//         actual = ex.code();
//      } catch (DynamicError ex) {
//         actual = ex.code();
//      }
//
//      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
//        
//
//   }

   //Evaluation of a Node expression With the operands/operator set with the following format: Sequence of single Element Node "<<" empty Sequence.
   public void test_nodeexpression29() throws Exception {
      String inputFile = "/TestSources/staff.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeBefore/nodeexpression29.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeBefore/nodeexpression29.txt";
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

   //Evaluation of a Node expression With the operands/operator set with the following format: Sequence of single Element Node "<<" Single Node Element.
//   public void test_nodeexpression31() throws Exception {
//      String inputFile = "/TestSources/works.xml";
//      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeBefore/nodeexpression31.xq";
//      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeBefore/nodeexpression31.txt";
//      String expectedResult = getExpectedResult(resultFile);
//      URL fileURL = bundle.getEntry(inputFile);
//      loadDOMDocument(fileURL);
//      
//      // Get XML Schema Information for the Document
//      XSModel schema = getGrammar();
//
//      setupDynamicContext(schema);
//
//      String xpath = extractXPathExpression(xqFile, inputFile);
//      String actual = null;
//      try {
//	   	  XPath path = compileXPath(xpath);
//	
//	      Evaluator eval = new DefaultEvaluator(dc, domDoc);
//	      ResultSequence rs = eval.evaluate(path);
//         
//          actual = buildResultString(rs);
//	
//      } catch (XPathParserException ex) {
//    	 actual = ex.code();
//      } catch (StaticError ex) {
//         actual = ex.code();
//      } catch (DynamicError ex) {
//         actual = ex.code();
//      }
//
//      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
//        
//
//   }

   //Evaluation of a Node expression With the operands/operator set with the following format: Sequence of single Element Node "<<" Sequence of single Element Node.
   public void test_nodeexpression32() throws Exception {
      String inputFile = "/TestSources/staff.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeBefore/nodeexpression32.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeBefore/nodeexpression32.txt";
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

   //Evaluation of a Node before expression as an argument to the "fn:not" function.
   public void test_nodeexpressionhc6() throws Exception {
      String inputFile = "/TestSources/staff.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeBefore/nodeexpressionhc6.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeBefore/nodeexpressionhc6.txt";
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

   //Evaluation of a Node before expression used as part of a boolean-less-than expression (lt operator).
   public void test_nodeexpressionhc7() throws Exception {
      String inputFile = "/TestSources/staff.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeBefore/nodeexpressionhc7.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeBefore/nodeexpressionhc7.txt";
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

   //Evaluation of a Node before expression used as part of a boolean-less-than expression (ge operator).
   public void test_nodeexpressionhc8() throws Exception {
      String inputFile = "/TestSources/staff.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeBefore/nodeexpressionhc8.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeBefore/nodeexpressionhc8.txt";
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

   //Evaluation of a Node before expression used as part of a boolean-greater-than expression (gt operator).
   public void test_nodeexpressionhc9() throws Exception {
      String inputFile = "/TestSources/staff.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeBefore/nodeexpressionhc9.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeBefore/nodeexpressionhc9.txt";
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

   //Evaluation of a Node before expression used as part of a boolean-greater-than expression (le operator).
   public void test_nodeexpressionhc10() throws Exception {
      String inputFile = "/TestSources/staff.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeBefore/nodeexpressionhc10.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeBefore/nodeexpressionhc10.txt";
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

   //Evaluation of the "node-before" operator with one of the operands is not a single node or the empty sequence.
   public void test_nodecomparisonerr_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/Operators/NodeOp/NodeBefore/nodecomparisonerr-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/Operators/NodeOp/NodeBefore/nodecomparisonerr-2.txt";
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
      