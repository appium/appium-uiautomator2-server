
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
      
      
public class UnabbrAxesTest extends AbstractPsychoPathTest {

   //Evaluates unabbreviated syntax - child::empnum - select empnum children of the context node.
   public void test_unabbreviatedSyntax_1() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-1.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-1.txt";
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

   //Evaluates unabbreviated syntax - child::* - select all element children of the context node.
   public void test_unabbreviatedSyntax_2() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-2.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-2.txt";
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

   //Evaluates unabbreviated syntax - child::text() - select all text node children of the context node.
   public void test_unabbreviatedSyntax_3() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-3.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-3.txt";
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

   //Evaluates unabbreviated syntax - child::node() - select all children of the context node.
   public void test_unabbreviatedSyntax_4() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-4.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-4.txt";
      String expectedResult = "<result>" + getExpectedResult(resultFile) + "</result>";
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

   //Evaluates unabbreviated syntax - child::node() - select all children of the context node.
   public void test_unabbreviatedSyntax_5() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-5.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-5.txt";
      String expectedResult = "<result>" + getExpectedResult(resultFile) + "</result>";
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

   //Evaluates unabbreviated syntax - parent::node() - Selects the parent of the context node.
   public void test_unabbreviatedSyntax_8() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-8.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-8.txt";
      String expectedResult = formatResultString(resultFile);
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

   //Evaluates unabbreviated syntax - descendant::empnum - Selects the "empnum" descendants of the context node.
   public void test_unabbreviatedSyntax_9() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-9.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-9.txt";
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

   //Evaluates unabbreviated syntax - descendant-or-self::employee - Selects all the "employee" descendant of the context node (selects employee, if the context node is "employee").
   public void test_unabbreviatedSyntax_12() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-12.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-12.txt";
      String expectedResult = formatResultString(resultFile);
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

   //Evaluates unabbreviated syntax - self::employee - Selects the context node, if it is an "employee" element, otherwise returns empty sequence. This test retuns an "employee" element.
   public void test_unabbreviatedSyntax_13() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-13.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-13.txt";
      String expectedResult = formatResultString(resultFile);
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

   //Evaluates unabbreviated syntax - self::hours - Selects the context node, if it is an "hours" element, otherwise returns empty sequence. This test retuns the empty sequence.
   public void test_unabbreviatedSyntax_14() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-14.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-14.txt";
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

   //Evaluates unabbreviated syntax child::employee/descendant:empnum- Selects the empnum element descendants of the employee element children of the context node.
   public void test_unabbreviatedSyntax_15() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-15.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-15.txt";
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

   //Evaluates unabbreviated syntax child::*/child:pnum)- Selects the pnum grandchildren of the context node.
   public void test_unabbreviatedSyntax_16() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-16.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-16.txt";
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

   //Evaluates unabbreviated syntax. Evaluate /descendant::pnum - Selects all the pnum elements in the same document as the context node.
   public void test_unabbreviatedSyntax_18() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-18.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-18.txt";
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

   //Evaluates unabbreviated syntax. Evaluate /descendant::employee/child::pnum selects all the pnum elements that have an "employee" parent and that are in the same document as the context node.
   public void test_unabbreviatedSyntax_19() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-19.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-19.txt";
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

   //Evaluates unabbreviated syntax. Evaluate "child::employee[fn:position() = 1]". Selects the first employee child of the context node.
   public void test_unabbreviatedSyntax_20() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-20.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-20.txt";
      String expectedResult = formatResultString(resultFile);
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

   //Evaluates unabbreviated syntax. Evaluate "child::employee[fn:position() = fn:last()]". Selects the last "employee" child of the context node.
   public void test_unabbreviatedSyntax_21() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-21.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-21.txt";
      String expectedResult = formatResultString(resultFile);
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

   //Evaluates unabbreviated syntax. Evaluate "child::employee[fn:position() = fn:last()-1]. Selects the previous to the last one "employee" child of the context node.
   public void test_unabbreviatedSyntax_22() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-22.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-22.txt";
      String expectedResult = formatResultString(resultFile);
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

   //Evaluates unabbreviated syntax. Evaluate "child::hours[fn:position() > 1]". Selects all the para children of the context node other than the first "hours" child of the context node.
   public void test_unabbreviatedSyntax_23() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-23.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-23.txt";
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

   //Evaluates unabbreviated syntax. Evaluate "/descendant::employee[fn:position() = 12]". Selects the twelfth employee element in the document containing the context node.
   public void test_unabbreviatedSyntax_26() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-26.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-26.txt";
      String expectedResult = formatResultString(resultFile);
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

   //Evaluates unabbreviated syntax. Evaluate "/child::works/child::employee[fn:position() = 5]/child::hours[fn:position() = 2]". Selects the second "hours" of the fifth "employee" of the "works" whose parent is the document node that contains the context node.
   public void test_unabbreviatedSyntax_27() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-27.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-27.txt";
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

   //Evaluates unabbreviated syntax. Evaluate "child::employee[attribute::name eq "Jane Doe 11"]". Selects all "employee" children of the context node that have a "name" attribute with value "Jane Doe 11".
   public void test_unabbreviatedSyntax_28() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-28.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-28.txt";
      String expectedResult = formatResultString(resultFile);
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

   //Evaluates unabbreviated syntax. Evaluate "child::employee[attribute::gender eq 'female'][fn:position() = 5]". Selects the fifth employee child of the context node that has a gender attribute with value "female".
   public void test_unabbreviatedSyntax_29() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-29.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-29.txt";
      String expectedResult = formatResultString(resultFile);
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

   //Evaluates unabbreviated syntax. Evaluate "child::employee[child::empnum = 'E3']". Selects the employee children of the context node that have one or more empnum children whose typed value is equal to the string "E3".
   public void test_unabbreviatedSyntax_30() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-30.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-30.txt";
      String expectedResult = "<result>" + getExpectedResult(resultFile) + "</result>";
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

   //Evaluates unabbreviated syntax. Evaluate "child::employee[child::status]". Selects the employee children of the context node that have one or more status children.
   public void test_unabbreviatedSyntax_31() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-31.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-31.txt";
      String expectedResult = formatResultString(resultFile);
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

   //Evaluates unabbreviated syntax. Evaluate "child::*[self::pnum or self::empnum]". Selects the pnum and empnum children of the context node.
   public void test_unabbreviatedSyntax_32() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-32.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-32.txt";
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

   //Evaluates unabbreviated syntax. Evaluate "child::*[self::empnum or self::pnum][fn:position() = fn:last()]". Selects the last empnum or pnum child of the context node.
   public void test_unabbreviatedSyntax_33() throws Exception {
      String inputFile = "/TestSources/works-mod.xml";
      String xqFile = "/Queries/XQuery/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-33.xq";
      String resultFile = "/ExpectedTestResults/Expressions/PathExpr/UnabbrAxes/unabbreviatedSyntax-33.txt";
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
      