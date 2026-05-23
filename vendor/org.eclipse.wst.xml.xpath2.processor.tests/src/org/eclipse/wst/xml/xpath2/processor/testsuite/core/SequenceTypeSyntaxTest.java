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

package org.eclipse.wst.xml.xpath2.processor.testsuite.core;

import java.net.URL;

import org.apache.xerces.xs.XSModel;
import org.eclipse.wst.xml.xpath2.processor.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.StaticError;
import org.eclipse.wst.xml.xpath2.processor.XPathParserException;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyType;
import org.eclipse.wst.xml.xpath2.processor.test.AbstractPsychoPathTest;
      
      
public class SequenceTypeSyntaxTest extends AbstractPsychoPathTest {

   //Simple evaluation of sequence type matching involving instance of and a sequence of integers.
   public void test_sequence_type_1() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Basics/Types/SequenceTypeSyntax/sequence-type-1.xq";
      String resultFile = "/ExpectedTestResults/Basics/Types/SequenceTypeSyntax/truevalue.txt";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Simple evaluation of sequence type matching involving instance of and a sequence of doubles.
   public void test_sequence_type_2() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Basics/Types/SequenceTypeSyntax/sequence-type-2.xq";
      String resultFile = "/ExpectedTestResults/Basics/Types/SequenceTypeSyntax/truevalue.txt";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Simple evaluation of sequence type matching involving instance of and a sequence of decimal.
   public void test_sequence_type_3() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Basics/Types/SequenceTypeSyntax/sequence-type-3.xq";
      String resultFile = "/ExpectedTestResults/Basics/Types/SequenceTypeSyntax/truevalue.txt";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Simple evaluation of sequence type matching involving instance of and a sequence of strings.
   public void test_sequence_type_4() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Basics/Types/SequenceTypeSyntax/sequence-type-4.xq";
      String resultFile = "/ExpectedTestResults/Basics/Types/SequenceTypeSyntax/truevalue.txt";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Simple evaluation of sequence type matching involving instance of and a sequence of strings. (uses integer*).
   public void test_sequence_type_5() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Basics/Types/SequenceTypeSyntax/sequence-type-5.xq";
      String resultFile = "/ExpectedTestResults/Basics/Types/SequenceTypeSyntax/falsevalue.txt";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Simple evaluation of sequence type matching involving instance of and a sequence of one integer (Uses integer?).
   public void test_sequence_type_6() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Basics/Types/SequenceTypeSyntax/sequence-type-6.xq";
      String resultFile = "/ExpectedTestResults/Basics/Types/SequenceTypeSyntax/truevalue.txt";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Simple evaluation of sequence type matching involving instance of and a sequence of integers. (Uses integer?).
   public void test_sequence_type_7() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Basics/Types/SequenceTypeSyntax/sequence-type-7.xq";
      String resultFile = "/ExpectedTestResults/Basics/Types/SequenceTypeSyntax/falsevalue.txt";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Simple evaluation of sequence type matching involving instance of and a sequence of integers. (Uses integer+).
   public void test_sequence_type_8() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Basics/Types/SequenceTypeSyntax/sequence-type-8.xq";
      String resultFile = "/ExpectedTestResults/Basics/Types/SequenceTypeSyntax/truevalue.txt";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Simple evaluation of sequence type matching involving castable as and a sequence of one integer. (Uses integer?).
   public void test_sequence_type_9() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Basics/Types/SequenceTypeSyntax/sequence-type-9.xq";
      String resultFile = "/ExpectedTestResults/Basics/Types/SequenceTypeSyntax/truevalue.txt";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Simple evaluation of sequence type matching involving castable as and a sequence of two integers. (Uses integer?).
   public void test_sequence_type_10() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Basics/Types/SequenceTypeSyntax/sequence-type-10.xq";
      String resultFile = "/ExpectedTestResults/Basics/Types/SequenceTypeSyntax/falsevalue.txt";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Simple evaluation of sequence type matching involving castable as and a sequence of one double. (Uses double?).
   public void test_sequence_type_11() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Basics/Types/SequenceTypeSyntax/sequence-type-11.xq";
      String resultFile = "/ExpectedTestResults/Basics/Types/SequenceTypeSyntax/truevalue.txt";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Simple evaluation of sequence type matching involving castable as and a sequence of two double. (Uses double?).
   public void test_sequence_type_12() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Basics/Types/SequenceTypeSyntax/sequence-type-12.xq";
      String resultFile = "/ExpectedTestResults/Basics/Types/SequenceTypeSyntax/falsevalue.txt";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Simple evaluation of sequence type matching involving castable as and a sequence of one decimal. (Uses decimal?).
   public void test_sequence_type_13() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Basics/Types/SequenceTypeSyntax/sequence-type-13.xq";
      String resultFile = "/ExpectedTestResults/Basics/Types/SequenceTypeSyntax/truevalue.txt";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Simple evaluation of sequence type matching involving castable as and a sequence of two decimals. (Uses decimal?).
   public void test_sequence_type_14() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Basics/Types/SequenceTypeSyntax/sequence-type-14.xq";
      String resultFile = "/ExpectedTestResults/Basics/Types/SequenceTypeSyntax/falsevalue.txt";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Simple evaluation of sequence type matching involving castable as and a sequence of one string. (Uses string?).
   public void test_sequence_type_15() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Basics/Types/SequenceTypeSyntax/sequence-type-15.xq";
      String resultFile = "/ExpectedTestResults/Basics/Types/SequenceTypeSyntax/truevalue.txt";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Simple evaluation of sequence type matching involving castable as and a sequence of two strings. (Uses string?).
   public void test_sequence_type_16() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Basics/Types/SequenceTypeSyntax/sequence-type-16.xq";
      String resultFile = "/ExpectedTestResults/Basics/Types/SequenceTypeSyntax/falsevalue.txt";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Simple evaluation of sequence type matching involving castable as and a sequence of one string. (Uses integer?).
   public void test_sequence_type_17() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Basics/Types/SequenceTypeSyntax/sequence-type-17.xq";
      String resultFile = "/ExpectedTestResults/Basics/Types/SequenceTypeSyntax/falsevalue.txt";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Simple evaluation of sequence type matching involving castable as and a sequence of two strings. (Uses integer?).
   public void test_sequence_type_18() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Basics/Types/SequenceTypeSyntax/sequence-type-18.xq";
      String resultFile = "/ExpectedTestResults/Basics/Types/SequenceTypeSyntax/falsevalue.txt";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Simple evaluation of sequence type matching involving instance of and a sequence of two booleans. (Uses xs:boolean*).
   public void test_sequence_type_19() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Basics/Types/SequenceTypeSyntax/sequence-type-19.xq";
      String resultFile = "/ExpectedTestResults/Basics/Types/SequenceTypeSyntax/truevalue.txt";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Simple evaluation of sequence type matching involving instance of and a sequence of two booleans. (Uses xs:boolean?).
   public void test_sequence_type_20() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Basics/Types/SequenceTypeSyntax/sequence-type-20.xq";
      String resultFile = "/ExpectedTestResults/Basics/Types/SequenceTypeSyntax/falsevalue.txt";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

   //Simple evaluation of sequence type matching involving instance of and a sequence of two booleans. (Uses xs:boolean+).
   public void test_sequence_type_21() throws Exception {
      String inputFile = "/TestSources/emptydoc.xml";
      String xqFile = "/Queries/XQuery/Basics/Types/SequenceTypeSyntax/sequence-type-21.xq";
      String resultFile = "/ExpectedTestResults/Basics/Types/SequenceTypeSyntax/truevalue.txt";
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

	
	      AnyType result = rs.first();
	
	      actual = result.getStringValue();
      } catch (XPathParserException ex) {
    	 actual = ex.code();
      } catch (StaticError ex) {
         actual = ex.code();
      }

      assertEquals("XPath Result Error:", expectedResult, actual);
        

   }

}
      