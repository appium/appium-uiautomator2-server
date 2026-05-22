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
 *     Mukul Gandhi    - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/
package org.eclipse.wst.xml.xpath2.processor.test;

import java.util.Comparator;

import org.eclipse.wst.xml.xpath2.api.CollationProvider;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.StaticError;
import org.eclipse.wst.xml.xpath2.processor.XPathParserException;

public class TestMinMax extends AbstractPsychoPathTest {

	private static final String URN_X_ECLIPSE_XPATH20_FUNKY_COLLATOR = "urn:x-eclipse:xpath20:funky-collator";

	public void testEmpty() throws Exception {
		assertXPathEvalation("max( () )", "");
		assertXPathEvalation("min( () )", "");
	}

	public void testYMDMinMax() throws Exception {
		assertXPathEvalation("max( (xs:yearMonthDuration('P0Y1M'), xs:yearMonthDuration('P1Y1M'), xs:yearMonthDuration('P1Y0M')) )", "P1Y1M");
		assertXPathEvalation("min( (xs:yearMonthDuration('P0Y1M'), xs:yearMonthDuration('P1Y1M'), xs:yearMonthDuration('P1Y0M')) )", "P1M");
	}

	public void testDTDMax() throws Exception {
		assertXPathEvalation("max( (xs:dayTimeDuration('P1DT2H'), xs:dayTimeDuration('P1D'), xs:dayTimeDuration('PT22H')) )", "P1DT2H");
		assertXPathEvalation("min( (xs:dayTimeDuration('P1DT2H'), xs:dayTimeDuration('P1D'), xs:dayTimeDuration('PT22H')) )", "PT22H");
	}

	public void testAtomic() throws Exception {
		assertXPathEvalation("max( (xs:untypedAtomic('1'), 0.9, xs:untypedAtomic('2.1')) )", "2.1");
		assertXPathEvalation("min( (xs:untypedAtomic('1'), 0.9, xs:untypedAtomic('2.1')) )", "0.9");
	}

	public void testMixed() throws Exception {
		assertDynamicError("max( ('a', 1.2, 1) )", "FORG0006");
		assertDynamicError("min( ('a', 1.2, 1) )", "FORG0006");
		assertDynamicError("max( (xs:dayTimeDuration('P1DT2H'), 123) )", "FORG0006");
		assertDynamicError("min( (xs:dayTimeDuration('P1DT2H'), 123) )", "FORG0006");
		
		assertDynamicError("max( (xs:untypedAtomic('1'), 1.2, '2.1') )", "FORG0006");
		assertDynamicError("min( (xs:untypedAtomic('1'), 1.2, '2.1') )", "FORG0006");
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
			evaluate(domDoc);
			fail("Error " + errorCode + " expected here");
		} catch (DynamicError de) {
			assertEquals("Wrong error code", errorCode, de.code());
		} catch (Throwable t) {
			fail("Unexpected error: " + t.getMessage());
		}
	}

	public void testStringMax() throws Exception {
		setupDynamicContext(null);
		setCollationProvider(createLengthCollatorProvider());

		String xpath = "max( ('1000', '200', '30', '4') )";
          compileXPath(xpath);
          ResultSequence rs = evaluate(domDoc);


		String resultValue = rs.first().getStringValue();

		// lexicographically, '4' is biggest
		assertEquals("4", resultValue);
		setDefaultCollation(URN_X_ECLIPSE_XPATH20_FUNKY_COLLATOR);

		rs = evaluate(null);
		resultValue = rs.first().getStringValue();

		// length-wise, '1000' is biggest!
		assertEquals("1000", resultValue);
	}
	
	private CollationProvider createLengthCollatorProvider() {
		final CollationProvider oldProvider = getStaticContext().getCollationProvider();
		return new CollationProvider() {
			
			public Comparator getCollation(String name) {
				if (name.equals(URN_X_ECLIPSE_XPATH20_FUNKY_COLLATOR)) {
					return new Comparator() {
						public int compare(Object o1, Object o2) {
							return ((String)o1).length() - ((String)o2).length();
						}
					};
				}
				return oldProvider.getCollation(name);
			}

			public String getDefaultCollation() {
				return oldProvider.getDefaultCollation();
			}
		};
	}
	
}
