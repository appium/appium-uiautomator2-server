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
      
      
public class NodeLangFuncTest extends AbstractPsychoPathTest {

   //Evaluates the "lang" function with the arguments set as follows: $testlang = "en" and context node not containing "xml-lang" attribute.
   public void test_fn_lang1args_1() throws Exception {
      String inputFile = "/TestSources/atomicns.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeFunc/NodeLangFunc/fn-lang1args-1.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeFunc/NodeLangFunc/fn-lang1args-1.txt";
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

   //Evaluates the "lang" function with the arguments set as follows: $testlang = Evaluates The "lang" function with the arguments set as follows: $testlang = "EN" and context node not containing "xml-lang" attribute.
   public void test_fn_lang1args_2() throws Exception {
      String inputFile = "/TestSources/atomicns.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeFunc/NodeLangFunc/fn-lang1args-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeFunc/NodeLangFunc/fn-lang1args-2.txt";
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

   //Evaluates the "lang" function with the arguments set as follows: $testlang = Evaluates The "lang" function with the arguments set as follows: $testlang = "eN" and context node not containing "xml-lang" attribute.
   public void test_fn_lang1args_3() throws Exception {
      String inputFile = "/TestSources/atomicns.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeFunc/NodeLangFunc/fn-lang1args-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeFunc/NodeLangFunc/fn-lang1args-3.txt";
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

   //Evaluates the "lang" function with the second argument not set and no context item defined.
   public void test_fn_lang_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeFunc/NodeLangFunc/fn-lang-1.xq";
      String expectedResult = "XPDY0002";
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = "fn:lang(\"en\")";
      String actual = null;
      try {
	   	  compileXPath(xpath);
	
	      ResultSequence rs = evaluate(null); // no context
         	
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      } catch (DynamicError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error " + xqFile + ":", expectedResult, actual);
        

   }

   //Evaluation of the fn:lang function with testlang set to empty sequence.
   public void test_fn_lang_2() throws Exception {
      String inputFile = "/TestSources/lang.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeFunc/NodeLangFunc/fn-lang-2.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeFunc/NodeLangFunc/fn-lang-2.txt";
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

   //Evaluation of the fn:lang function with testlang "en" and context node having an "xml:lang" attribute set to "en".
   public void test_fn_lang_3() throws Exception {
      String inputFile = "/TestSources/lang.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeFunc/NodeLangFunc/fn-lang-3.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeFunc/NodeLangFunc/fn-lang-3.txt";
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

   //Evaluation of the fn:lang function with testlang set to "en". Immediate ancestor have the "xml:lang" attribute set to "en".
   public void test_fn_lang_4() throws Exception {
      String inputFile = "/TestSources/lang.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeFunc/NodeLangFunc/fn-lang-4.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeFunc/NodeLangFunc/fn-lang-4.txt";
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

   //Evaluation of the fn:lang function with testlang "en" and context node having an "xml:lang" attribute set to "EN".
   public void test_fn_lang_5() throws Exception {
      String inputFile = "/TestSources/lang.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeFunc/NodeLangFunc/fn-lang-5.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeFunc/NodeLangFunc/fn-lang-5.txt";
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

   //Evaluation of the fn:lang function with testlang "En" and context node having an "xml:lang" attribute set to "EN".
   public void test_fn_lang_6() throws Exception {
      String inputFile = "/TestSources/lang.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeFunc/NodeLangFunc/fn-lang-6.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeFunc/NodeLangFunc/fn-lang-6.txt";
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

   //Evaluation of the fn:lang function with testlang "eN" and context node having an "xml:lang" attribute set to "EN".
   public void test_fn_lang_7() throws Exception {
      String inputFile = "/TestSources/lang.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeFunc/NodeLangFunc/fn-lang-7.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeFunc/NodeLangFunc/fn-lang-7.txt";
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

   //Evaluation of the fn:lang function with testlang "en" and context node having an "xml:lang" attribute set to "EN".
   public void test_fn_lang_8() throws Exception {
      String inputFile = "/TestSources/lang.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeFunc/NodeLangFunc/fn-lang-8.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeFunc/NodeLangFunc/fn-lang-8.txt";
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

   //Evaluation of the fn:lang function with testlang "en" and context node having an "xml:lang" attribute set to "en-us".
   public void test_fn_lang_9() throws Exception {
      String inputFile = "/TestSources/lang.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeFunc/NodeLangFunc/fn-lang-9.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeFunc/NodeLangFunc/fn-lang-9.txt";
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

   //Evaluation of the fn:lang function with testlang "EN" and context node having an "xml:lang" attribute set to "en-us".
   public void test_fn_lang_10() throws Exception {
      String inputFile = "/TestSources/lang.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeFunc/NodeLangFunc/fn-lang-10.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeFunc/NodeLangFunc/fn-lang-10.txt";
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

   //Evaluation of the fn:lang function with testlang "En" and context node having an "xml:lang" attribute set to "en-us".
   public void test_fn_lang_11() throws Exception {
      String inputFile = "/TestSources/lang.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeFunc/NodeLangFunc/fn-lang-11.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeFunc/NodeLangFunc/fn-lang-11.txt";
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

   //Evaluation of the fn:lang function with testlang "eN" and context node having an "xml:lang" attribute set to "en-us".
   public void test_fn_lang_12() throws Exception {
      String inputFile = "/TestSources/lang.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeFunc/NodeLangFunc/fn-lang-12.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeFunc/NodeLangFunc/fn-lang-12.txt";
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

   //Evaluation of the fn:lang function with testlang "en" and context node having an "xml:lang" attribute set to "en-us".
   public void test_fn_lang_13() throws Exception {
      String inputFile = "/TestSources/lang.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeFunc/NodeLangFunc/fn-lang-13.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeFunc/NodeLangFunc/fn-lang-13.txt";
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

   //Evaluation of the fn:lang function with testlang "en-us" and context node having an "xml:lang" attribute set to "en-us".
   public void test_fn_lang_14() throws Exception {
      String inputFile = "/TestSources/lang.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeFunc/NodeLangFunc/fn-lang-14.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeFunc/NodeLangFunc/fn-lang-14.txt";
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

   //Evaluates the "lang" function with the context item not being a node.
   public void test_fn_lang_15() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeFunc/NodeLangFunc/fn-lang-15.xq";
      String expectedResult = "XPTY0004";
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

   //Evaluation of the fn:lang function with testlang "us-en" and context node having an "xml:lang" attribute set to "en-us".
   public void test_fn_lang_16() throws Exception {
      String inputFile = "/TestSources/lang.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeFunc/NodeLangFunc/fn-lang-16.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeFunc/NodeLangFunc/fn-lang-16.txt";
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

   //Evaluation of the fn:lang function with testlang set to "fr" and context node having an "xml:lang" attribute set to "EN".
   public void test_fn_lang_17() throws Exception {
      String inputFile = "/TestSources/lang.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeFunc/NodeLangFunc/fn-lang-17.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeFunc/NodeLangFunc/fn-lang-17.txt";
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

   //Evaluation of the fn:lang function with testlang set to "en" and specified node (second argument) having an "xml:lang" attribute set to "en".
   public void test_fn_lang_18() throws Exception {
      String inputFile = "/TestSources/lang.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeFunc/NodeLangFunc/fn-lang-18.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeFunc/NodeLangFunc/fn-lang-18.txt";
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

   //Evaluation of the fn:lang function with testlang set to "fr" and specified node (second argument) having an "xml:lang" attribute set to "en".
   public void test_fn_lang_19() throws Exception {
      String inputFile = "/TestSources/lang.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeFunc/NodeLangFunc/fn-lang-19.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeFunc/NodeLangFunc/fn-lang-19.txt";
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

   //Evaluation of the fn:lang function with testlang set to "en" and specified node (second argument) having an "xml:lang" attribute set to "en-us".
   public void test_fn_lang_20() throws Exception {
      String inputFile = "/TestSources/lang.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeFunc/NodeLangFunc/fn-lang-20.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeFunc/NodeLangFunc/fn-lang-20.txt";
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

   //Evaluation of the fn:lang function with testlang set to "en" and specified node (second argument) having an "xml:lang" attribute set to "EN".
   public void test_fn_lang_21() throws Exception {
      String inputFile = "/TestSources/lang.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeFunc/NodeLangFunc/fn-lang-21.xq";
      String resultFile = "/ExpectedTestResults/Functions/NodeFunc/NodeLangFunc/fn-lang-21.txt";
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

   //Evaluates the "lang" function with the second argument set to "." and no context item defined.
   public void test_fn_lang_22() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Functions/NodeFunc/NodeLangFunc/fn-lang-22.xq";
      String expectedResult = "XPDY0002";
      URL fileURL = bundle.getEntry(inputFile);
      loadDOMDocument(fileURL);
      
      // Get XML Schema Information for the Document
      XSModel schema = getGrammar();

      setupDynamicContext(schema);

      String xpath = extractXPathExpression(xqFile, inputFile);
      String actual = null;
      try {
	   	  compileXPath(xpath);
	      ResultSequence rs = evaluate(null); // no context
         
	      // we cannot require that a context is always present
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
      