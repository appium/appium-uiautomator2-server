/*******************************************************************************
 * Copyright (c) 2009, 2011 Standards for Technology in Automotive Retail and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    David Carver - initial API and implementation
 *    Jesper Steen Moller - bug 283404 - fixed locale
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.types;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.FieldPosition;
import java.util.Locale;

/**
 * This is an XPath specific implementation of DecimalFormat to handle
 * some of the xpath specific formatting requirements.   Specifically
 * it allows for E# to be represented to indicate that the exponent value
 * is optional.  Otherwise all existing DecimalFormat patterns are handled
 * as is.
 * @author dcarver
 * @see 1.1
 *
 */
public class XPathDecimalFormat extends DecimalFormat {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8229885955864187400L;
	private static final String NEG_INFINITY = "-INF";
	private static final String POS_INFINITY = "INF";

	public XPathDecimalFormat(String pattern) {
		// Xpath hardcodes this to US locale
		super(pattern, new DecimalFormatSymbols(Locale.US));
	}

	/**
	 * Formats the string dropping a Zero Exponent Value if it exists.
	 * @param obj
	 * @return
	 */
	public String xpathFormat(Object obj) {
		return formatXPath(obj);
	}

	private String formatXPath(Object obj) {
		String curPattern = toPattern();
		String newPattern = curPattern.replaceAll("E0", "");
		if (obj instanceof Float) {
            return formatFloatValue(obj, curPattern, newPattern);
		}
		if (obj instanceof Double) {
			return formatDoubleValue(obj, curPattern, newPattern);
		}
		return super.format(obj, new StringBuffer(), new FieldPosition(0)).toString();
	}

	private String formatDoubleValue(Object obj, String curPattern,
			String newPattern) {
		Double doubleValue = (Double) obj;
		if (isDoubleNegativeInfinity(doubleValue)) {
			return NEG_INFINITY;
		}
		if (isDoublePositiveInfinity(doubleValue)) {
			return POS_INFINITY;
		}
		doubleXPathPattern(obj, curPattern, newPattern);
		return format(obj, new StringBuffer(), new FieldPosition(0)).toString();
	}

	private void doubleXPathPattern(Object obj, String curPattern,
			String newPattern) {
		BigDecimal doubValue = new BigDecimal((((Double) obj)).doubleValue());
		BigDecimal minValue = new BigDecimal("-1E6");
		BigDecimal maxValue = new BigDecimal("1E6");
		if (doubValue.compareTo(minValue) > 0 && doubValue.compareTo(maxValue) < 0) {
			applyPattern(newPattern);
		} else { //if (doubValue.compareTo(minValue) < 0) {
			applyPattern(curPattern.replaceAll("0\\.#", "0.0"));
		}
	}

	private boolean isDoublePositiveInfinity(Double doubleValue) {
		return doubleValue.doubleValue() == Double.POSITIVE_INFINITY;
	}

	private boolean isDoubleNegativeInfinity(Double doubleValue) {
		return doubleValue.doubleValue() == Double.NEGATIVE_INFINITY;
	}

	private String formatFloatValue(Object obj, String curPattern,
			String newPattern) {
		Float floatValue = (Float) obj;
		if (isFloatNegInfinity(floatValue)) {
			return NEG_INFINITY;
		}
		if (isFloatPosInfinity(floatValue)) {
			return POS_INFINITY;
		}
		floatXPathPattern(curPattern, newPattern, floatValue);
		return format(obj, new StringBuffer(), new FieldPosition(0)).toString();
	}

	private boolean isFloatPosInfinity(Float floatValue) {
		return floatValue.floatValue() == Float.POSITIVE_INFINITY;
	}

	private boolean isFloatNegInfinity(Float floatValue) {
		return floatValue.floatValue() == Float.NEGATIVE_INFINITY;
	}

	private void floatXPathPattern(String curPattern, String newPattern,
			Float floatValue) {
		if (floatValue.floatValue() > -1E6f && floatValue.floatValue() < 1E6f) {
			
			applyPattern(newPattern);
		} else if (floatValue.floatValue() <= -1E6f) {
			applyPattern(curPattern.replaceAll("0\\.#", "0.0" ));
		}
	}
	
	
}
