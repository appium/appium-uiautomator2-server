/*******************************************************************************
 * Copyright (c) 2009, 2017 Jesper Steen Moller and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Jesper S Moller - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xml.xpath2.processor.test;

import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.StaticError;
import org.eclipse.wst.xml.xpath2.processor.XPathParserException;

public class TestSumAvg extends AbstractPsychoPathTest {

	public void testEmpty() throws Exception {
		assertXPathEvalation("sum( () )", "0");
		assertXPathEvalation("sum( (), 99 )", "99");
		assertXPathEvalation("avg( () )", "");
	}

	public void testYMDMinMax() throws Exception {
		assertXPathEvalation("sum( (xs:yearMonthDuration('P0Y1M'), xs:yearMonthDuration('P1Y1M'), xs:yearMonthDuration('P1Y0M')) )", "P2Y2M");
		assertXPathEvalation("avg( (xs:yearMonthDuration('P0Y1M'), xs:yearMonthDuration('P1Y2M'), xs:yearMonthDuration('P1Y0M')) )", "P9M");
	}

	public void testDTDMax() throws Exception {
		assertXPathEvalation("sum( (xs:dayTimeDuration('P1DT2H'), xs:dayTimeDuration('P1D'), xs:dayTimeDuration('PT22H')) )", "P3D");
		assertXPathEvalation("avg( (xs:dayTimeDuration('P1DT2H'), xs:dayTimeDuration('P1D'), xs:dayTimeDuration('PT22H')) )", "P1D");
	}

	public void testAtomic() throws Exception {
		assertXPathEvalation("sum( (xs:untypedAtomic('1'), 0.9, xs:untypedAtomic('2.1'),0 ) )", "4");
		assertXPathEvalation("avg( (xs:untypedAtomic('1'), 0.9, xs:untypedAtomic('2.1'),0 ) )", "1");
	}

	public void testMixed() throws Exception {
		assertDynamicError("sum( ('a', 1.2, 1) )", "FORG0006");
		assertDynamicError("avg( ('a', 1.2, 1) )", "FORG0006");
		assertDynamicError("sum( (xs:dayTimeDuration('P1DT2H'), 123) )", "FORG0006");
		assertDynamicError("avg( (xs:dayTimeDuration('P1DT2H'), 123) )", "FORG0006");
		
		assertDynamicError("sum( (xs:untypedAtomic('1'), 1.2, '2.1') )", "FORG0006");
		assertDynamicError("avg( (xs:untypedAtomic('1'), 1.2, '2.1') )", "FORG0006");
	}

	private void assertXPathEvalation(String xpath, String expectedResult) throws XPathParserException, StaticError,
			DynamicError {
		setupDynamicContext(null);
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		String resultValue = buildResultString(rs);

		assertEquals(expectedResult, resultValue);
	}


	private void assertDynamicError(String xpath, String errorCode) throws XPathParserException, StaticError {
		setupDynamicContext(null);

		compileXPath(xpath);

		try {
			evaluate(null);
			fail("Error " + errorCode + " expected here");
		} catch (DynamicError de) {
			assertEquals("Wrong error code", errorCode, de.code());
		} catch (Throwable t) {
			fail("Unexpected error: " + t.getMessage());
		}
	}	
}
