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
package org.eclipse.wst.xml.xpath2.processor.testsuite.dates;

import java.net.URL;

import org.apache.xerces.xs.XSModel;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.StaticError;
import org.eclipse.wst.xml.xpath2.processor.XPathParserException;
import org.eclipse.wst.xml.xpath2.processor.test.AbstractPsychoPathTest;

public class DateEdgeCasesTest extends AbstractPsychoPathTest {

	// Evaluates the "op:subtract-dateTimes-yielding-dayTimeDuration" operator
	// that returns a negative value.
	public void test_op_subtract_dateTimes_yielding_DTD_8() throws Exception {
		String inputFile = "/TestSources/emptydoc.xml";
		String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/DurationArith/DateTimesSubtract/op-subtract-dateTimes-yielding-DTD-8.xq";
		String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/DurationArith/DateTimesSubtract/op-subtract-dateTimes-yielding-DTD-8.txt";
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

		assertEquals("XPath Result Error " + xqFile + ":", expectedResult,
				actual);

	}

	// Evaluates the "op:subtract-dates-yielding-dayTimeDuration" operator that
	// returns a negative value.
	public void test_op_subtract_dates_yielding_DTD_8() throws Exception {
		String inputFile = "/TestSources/emptydoc.xml";
		String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/DurationArith/DatesSubtract/op-subtract-dates-yielding-DTD-8.xq";
		String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/DurationArith/DatesSubtract/op-subtract-dates-yielding-DTD-8.txt";
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

		assertEquals("XPath Result Error " + xqFile + ":", expectedResult,
				actual);

	}

	// Evaluates the "op:subtract-dayTimeDurations" function, which is part of a
	// div expression.
	public void test_op_subtract_dayTimeDurations_11() throws Exception {
		String inputFile = "/TestSources/emptydoc.xml";
		String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/DurationArith/DayTimeDurationSubtract/op-subtract-dayTimeDurations-11.xq";
		String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/DurationArith/DayTimeDurationSubtract/op-subtract-dayTimeDurations-11.txt";
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

		assertEquals("XPath Result Error " + xqFile + ":", expectedResult,
				actual);

	}

	// Evaluates the "op:divide-dayTimeDuration-by-dayTimeDuration" function,
	// which is part of a div expression.
	public void test_op_divide_dayTimeDuration_by_dTD_11() throws Exception {
		String inputFile = "/TestSources/emptydoc.xml";
		String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/DurationArith/DayTimeDurationDivideDTD/op-divide-dayTimeDuration-by-dTD-11.xq";
		String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/DurationArith/DayTimeDurationDivideDTD/op-divide-dayTimeDuration-by-dTD-11.txt";
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

		assertEquals("XPath Result Error " + xqFile + ":", expectedResult,
				actual);

	}

	// Evaluates the "op:divide-dayTimeDuration-by-dayTimeDuration" operator
	// with the arguments set as follows: $arg1 = xs:dayTimeDuration(mid range)
	// $arg2 = xs:dayTimeDuration(lower bound).
	public void test_op_divide_dayTimeDuration_by_dayTimeDuration2args_2()
			throws Exception {
		String inputFile = "/TestSources/emptydoc.xml";
		String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/DurationArith/DayTimeDurationDivideDTD/op-divide-dayTimeDuration-by-dayTimeDuration2args-2.xq";
		String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/DurationArith/DayTimeDurationDivideDTD/op-divide-dayTimeDuration-by-dayTimeDuration2args-2.txt";
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

		assertEquals("XPath Result Error " + xqFile + ":", expectedResult,
				actual);

	}

	// Evaluates the "op:divide-dayTimeDuration-by-dayTimeDuration" operator
	// with the arguments set as follows: $arg1 = xs:dayTimeDuration(upper
	// bound) $arg2 = xs:dayTimeDuration(lower bound).
	public void test_op_divide_dayTimeDuration_by_dayTimeDuration2args_3()
			throws Exception {
		String inputFile = "/TestSources/emptydoc.xml";
		String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/DurationArith/DayTimeDurationDivideDTD/op-divide-dayTimeDuration-by-dayTimeDuration2args-3.xq";
		String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/DurationArith/DayTimeDurationDivideDTD/op-divide-dayTimeDuration-by-dayTimeDuration2args-3.txt";
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

		assertEquals("XPath Result Error " + xqFile + ":", expectedResult,
				actual);

	}

	   //Evaluates the "op:divide-dayTimeDuration-by-dayTimeDuration" operator that returns a negative value.
	   public void test_op_divide_dayTimeDuration_by_dTD_8() throws Exception {
	      String inputFile = "/TestSources/emptydoc.xml";
	      String xqFile = "/Queries/XQuery/Expressions/Operators/ArithExpr/DurationArith/DayTimeDurationDivideDTD/op-divide-dayTimeDuration-by-dTD-8.xq";
	      String resultFile = "/ExpectedTestResults/Expressions/Operators/ArithExpr/DurationArith/DayTimeDurationDivideDTD/op-divide-dayTimeDuration-by-dTD-8.txt";
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
