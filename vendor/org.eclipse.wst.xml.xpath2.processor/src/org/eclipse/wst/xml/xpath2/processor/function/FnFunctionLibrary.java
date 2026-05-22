/*******************************************************************************
 * Copyright (c) 2005, 2009 Andrea Bittau, University College London, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Andrea Bittau - initial API and implementation from the PsychoPath XPath 2.0
 *     David Carver (STAR) - bug 262765 - Added FnDefaultCollation.
 *     David Carver (STAR) - bug 285321 - implemented fn:encode-for-uri()
 *     Jesper Moller       - bug 287369 - Support fn:codepoint-equal()
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.function;

import org.eclipse.wst.xml.xpath2.processor.internal.function.*;

// this is the equivalent of libc =D
/**
 * Maintains a library of core functions and user defined functions.
 */
public class FnFunctionLibrary extends FunctionLibrary {
	/**
	 * Path to xpath functions specification.
	 */
	public static final String XPATH_FUNCTIONS_NS = "http://www.w3.org/2005/xpath-functions";

	/**
	 * Constructor for FnFunctionLibrary.
	 */
	public FnFunctionLibrary() {
		super(XPATH_FUNCTIONS_NS);

		// add functions here
		add_function(new FnBoolean());
		add_function(new FnRoot());
		add_function(new FnNot());

		// accessors
		add_function(new FnNodeName());
		add_function(new FnNilled());
		add_function(new FnData());
		add_function(new FnString());
		add_function(new FnBaseUri());
		add_function(new FnStaticBaseUri());
		add_function(new FnDocumentUri());

		// error
		add_function(new FnError());

		// trace
		add_function(new FnTrace());

		// numeric functions
		add_function(new FnAbs());
		add_function(new FnCeiling());
		add_function(new FnFloor());
		add_function(new FnRound());
		add_function(new FnRoundHalfToEven());

		// string functions
		add_function(new FnCodepointsToString());
		add_function(new FnStringToCodepoints());
		add_function(new FnCompare());
		add_function(new FnCodepointEqual());
		add_function(new FnConcat());
		add_function(new FnStringJoin());
		add_function(new FnSubstring());
		add_function(new FnStringLength());
		add_function(new FnNormalizeSpace());
		add_function(new FnNormalizeUnicode());
		add_function(new FnUpperCase());
		add_function(new FnLowerCase());
		add_function(new FnTranslate());
		add_function(new FnEscapeHTMLUri());
		add_function(new FnIriToURI());
		add_function(new FnContains());
		add_function(new FnStartsWith());
		add_function(new FnEndsWith());
		add_function(new FnSubstringBefore());
		add_function(new FnSubstringAfter());
		add_function(new FnMatches());
		add_function(new FnReplace());
		add_function(new FnTokenize());
		add_function(new FnEncodeForURI());
		add_function(new FnResolveURI());

		// boolean functions
		add_function(new FnTrue());
		add_function(new FnFalse());

		// date extraction functions
		add_function(new FnYearsFromDuration());
		add_function(new FnMonthsFromDuration());
		add_function(new FnDaysFromDuration());
		add_function(new FnHoursFromDuration());
		add_function(new FnMinutesFromDuration());
		add_function(new FnSecondsFromDuration());
		add_function(new FnYearFromDateTime());
		add_function(new FnMonthFromDateTime());
		add_function(new FnDayFromDateTime());
		add_function(new FnHoursFromDateTime());
		add_function(new FnMinutesFromDateTime());
		add_function(new FnSecondsFromDateTime());
		add_function(new FnTimezoneFromDateTime());
		add_function(new FnYearFromDate());
		add_function(new FnMonthFromDate());
		add_function(new FnDayFromDate());
		add_function(new FnTimezoneFromDate());
		add_function(new FnHoursFromTime());
		add_function(new FnMinutesFromTime());
		add_function(new FnSecondsFromTime());
		add_function(new FnTimezoneFromTime());
		add_function(new FnDateTime());

		// timezone functs
		add_function(new FnImplicitTimezone());
		add_function(new FnAdjustDateTimeToTimeZone());
		add_function(new FnAdjustTimeToTimeZone());
		add_function(new FnAdjustDateToTimeZone());

		// QName functs
		add_function(new FnResolveQName());
		add_function(new FnQName());
		add_function(new FnLocalNameFromQName());
		add_function(new FnNamespaceUriFromQName());
		add_function(new FnPrefixFromQName());

		// XXX implement hex & binary & notations

		// node functions
		add_function(new FnName());
		add_function(new FnLocalName());
		add_function(new FnNamespaceUri());
		add_function(new FnNumber());
		add_function(new FnInScopePrefixes());

		// node functs
		add_function(new FnLang());

		// sequence functions
		add_function(new FnIndexOf());
		add_function(new FnEmpty());
		add_function(new FnExists());
		add_function(new FnDistinctValues());
		add_function(new FnInsertBefore());
		add_function(new FnRemove());
		add_function(new FnReverse());
		add_function(new FnSubsequence());
		add_function(new FnUnordered());

		// sequence caridnality
		add_function(new FnZeroOrOne());
		add_function(new FnOneOrMore());
		add_function(new FnExactlyOne());

		add_function(new FnDeepEqual());

		// aggregate functions
		add_function(new FnCount());
		add_function(new FnAvg());
		add_function(new FnMax());
		add_function(new FnMin());
		add_function(new FnSum());

		// XXX implement functions that generate sequences
		add_function(new FnDoc());
		add_function(new FnCollection());

		// context functions
		add_function(new FnPosition());
		add_function(new FnLast());
		add_function(new FnCurrentDateTime());
		add_function(new FnCurrentDate());
		add_function(new FnCurrentTime());
		
		// XXX collation
		add_function(new FnDefaultCollation());
		
		// ID and IDRef
		add_function(new FnID());
		add_function(new FnIDREF());

	}
}
