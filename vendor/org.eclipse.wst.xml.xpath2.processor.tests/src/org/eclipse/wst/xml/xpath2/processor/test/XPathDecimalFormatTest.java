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
 *     David Carver - initial API and implementation
 *     Jesper Steen Moller - bug 283404 - added locale sensitivity test
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.test;

import java.util.Locale;

import org.eclipse.wst.xml.xpath2.processor.internal.types.XPathDecimalFormat;

import junit.framework.TestCase;

public class XPathDecimalFormatTest extends TestCase {

	private static final String DOUBLE_FORMAT = "0.################E0";
	private static final String FLOAT_FORMAT = "0.#######E0";

	public void testDoublePositiveInfinity() {
		XPathDecimalFormat format = new XPathDecimalFormat(DOUBLE_FORMAT);
		Double value = new Double(Double.POSITIVE_INFINITY);
		String result = format.xpathFormat(value);
		assertEquals("Unexpected XPath format String:", "INF", result);
	}
	
	public void testDoubleNegativeInfinity() {
		XPathDecimalFormat format = new XPathDecimalFormat(DOUBLE_FORMAT);
		Double value = new Double(Double.NEGATIVE_INFINITY);
		String result = format.xpathFormat(value);
		assertEquals("Unexpected XPath format string:", "-INF", result);
	}
	
	public void testFloatPositiveInfinity() {
		XPathDecimalFormat format = new XPathDecimalFormat(FLOAT_FORMAT);
		Float value = new Float(Float.POSITIVE_INFINITY);
		String result = format.xpathFormat(value);
		assertEquals("Unexpected XPath format string:", "INF", result);
	}
	
	public void testFloatNegativeInfinity() {
		XPathDecimalFormat format = new XPathDecimalFormat(FLOAT_FORMAT);
		Float value = new Float(Float.NEGATIVE_INFINITY);
		String result = format.xpathFormat(value);
		assertEquals("Unexpected XPath format string:", "-INF", result);
	}

	public void testLocaleInsensitivity() {
		Locale.setDefault(Locale.GERMAN);
		XPathDecimalFormat format = new XPathDecimalFormat(FLOAT_FORMAT);
		Float value = Float.valueOf(1.2f);
		String result = format.xpathFormat(value);
		assertEquals("Unexpected XPath format string:", "1.2", result);
	}

}
