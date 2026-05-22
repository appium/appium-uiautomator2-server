
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
      
      
public class QuantExprWithTest extends AbstractPsychoPathTest {

   //Simple quantified expression using "every" keyword and use of multiple variables and the xs:date data type.
   public void test_quantExpr_60() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Expressions/QuantExpr/QuantExprWith/quantExpr-60.xq";
      String resultFile = "/ExpectedTestResults/Expressions/QuantExpr/QuantExprWith/quantExpr-60.txt";
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

   //Simple quantified expression using "some" keyword that binds the declared variables to and xs:integer type.
   // Not an valid XPath 2.0 test, pass it by default.  This is an XQuery test due to the TypeDeclaration for the variable.
//   public void test_quantexpr_61() throws Exception {
//      String inputFile = "/TestSources/emptydoc.xml";
//      String xqFile = "/Queries/XQuery/Expressions/QuantExpr/QuantExprWith/quantexpr-61.xq";
//      String resultFile = "/ExpectedTestResults/Expressions/QuantExpr/QuantExprWith/truevalue.txt";
//      String expectedResult = getExpectedResult(resultFile);
//      URL fileURL = bundle.getEntry(inputFile);
//      loadDOMDocument(fileURL);
//      
//      // Get XML Schema Information for the Document
//      XSModel schema = getGrammar();
//
//      setupDynamicContext(schema);
//
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

   //Simple quantified expression using "some" keyword that binds the declared variable to an xs:string type.
   // Same as 61.  Pass this automatically.
//   public void test_quantexpr_62() throws Exception {
//      String inputFile = "/TestSources/emptydoc.xml";
//      String xqFile = "/Queries/XQuery/Expressions/QuantExpr/QuantExprWith/quantexpr-62.xq";
//      String resultFile = "/ExpectedTestResults/Expressions/QuantExpr/QuantExprWith/truevalue.txt";
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
//      //assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
//        
//
//   }

   //Simple quantified expression using "every" keyword that binds the declared variable to an xs:string type.
   // Same as 61, 62, pass this automatically
//   public void test_quantexpr_63() throws Exception {
//      String inputFile = "/TestSources/emptydoc.xml";
//      String xqFile = "/Queries/XQuery/Expressions/QuantExpr/QuantExprWith/quantexpr-63.xq";
//      String resultFile = "/ExpectedTestResults/Expressions/QuantExpr/QuantExprWith/truevalue.txt";
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
//      //assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
//        
//
//   }

   //Simple quantified expression using "every" keyword that binds the declared variables to an xs:string and xs:integer type respectively.
   // Same as 61, 62, 63, pass this automatically.
//   public void test_quantexpr_64() throws Exception {
//      String inputFile = "/TestSources/emptydoc.xml";
//      String xqFile = "/Queries/XQuery/Expressions/QuantExpr/QuantExprWith/quantexpr-64.xq";
//      String resultFile = "/ExpectedTestResults/Expressions/QuantExpr/QuantExprWith/truevalue.txt";
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
//      //assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
//        
//
//   }

   //Simple quantified expression using "some" keyword that binds the declared variable to an xs:integer and xs:float type respectively.
//   public void test_quantexpr_65() throws Exception {
//      String inputFile = "/TestSources/emptydoc.xml";
//      String xqFile = "/Queries/XQuery/Expressions/QuantExpr/QuantExprWith/quantexpr-65.xq";
//      String resultFile = "/ExpectedTestResults/Expressions/QuantExpr/QuantExprWith/truevalue.txt";
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
//      // See 61 - 64 for reason this is commented out.
//      //assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
//        
//
//   }

}
      