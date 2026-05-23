/*******************************************************************************
 * Copyright (c) 2005, 2011 Andrea Bittau, University College London, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Andrea Bittau - initial API and implementation from the PsychoPath XPath 2.0
 *     Mukul Gandhi - bug 273760 - wrong namespace for functions and data types
 *     Mukul Gandhi - bug 279373 - improvements to multiply operation on xs:yearMonthDuration
 *                                 data type.
 *     David Carver - bug 282223 - implementation of xs:duration data type.
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.types;

import java.math.BigDecimal;

import org.eclipse.wst.xml.xpath2.api.DynamicContext;
import org.eclipse.wst.xml.xpath2.api.Item;
import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeDefinition;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.ResultSequenceFactory;
import org.eclipse.wst.xml.xpath2.processor.internal.function.CmpEq;
import org.eclipse.wst.xml.xpath2.processor.internal.function.CmpGt;
import org.eclipse.wst.xml.xpath2.processor.internal.function.CmpLt;
import org.eclipse.wst.xml.xpath2.processor.internal.function.MathDiv;
import org.eclipse.wst.xml.xpath2.processor.internal.function.MathMinus;
import org.eclipse.wst.xml.xpath2.processor.internal.function.MathPlus;
import org.eclipse.wst.xml.xpath2.processor.internal.function.MathTimes;
import org.eclipse.wst.xml.xpath2.processor.internal.types.builtin.BuiltinTypeLibrary;

/**
 * A representation of the YearMonthDuration datatype
 */
public class XSYearMonthDuration extends XSDuration implements CmpEq, CmpLt,
		CmpGt, MathPlus, MathMinus, MathTimes, MathDiv {


	private static final String XS_YEAR_MONTH_DURATION = "xs:yearMonthDuration";

	/**
	 * Initialises using the supplied parameters. If the number of months
	 * supplied is more than 12, the number of years is adjusted accordingly.
	 * 
	 * @param year
	 *            Number of years in this duration of time
	 * @param month
	 *            Number of months in this duration of time
	 * @param negative
	 *            True if this duration of time represents a backwards passage
	 *            through time. False otherwise
	 */
	public XSYearMonthDuration(int year, int month, boolean negative) {
		super(year, month, 0, 0, 0, 0, negative);
	}

	/**
	 * Initialises to the given number of months
	 * 
	 * @param months
	 *            Number of months in the duration of time
	 */
	public XSYearMonthDuration(int months) {
		this(0, Math.abs(months), months < 0);
	}

	/**
	 * Initialises to a duration of no time (0years and 0months)
	 */
	public XSYearMonthDuration() {
		this(0, 0, false);
	}

	/**
	 * Creates a new XSYearMonthDuration by parsing the supplied String
	 * represented duration of time
	 * 
	 * @param str
	 *            String represented duration of time
	 * @return New XSYearMonthDuration representing the duration of time
	 *         supplied
	 */
	public static XSDuration parseYMDuration(String str) {
		boolean negative = false;
		int year = 0;
		int month = 0;

		int state = 0; // 0 beginning
		// 1 year
		// 2 month
		// 3 done
		// 4 expecting P
		// 5 expecting Y or M
		// 6 expecting M or end
		// 7 expecting end

		String digits = "";
		for (int i = 0; i < str.length(); i++) {
			char x = str.charAt(i);

			switch (state) {
			// beginning
			case 0:
				if (x == '-') {
					negative = true;
					state = 4;
				} else if (x == 'P')
					state = 5;
				else
					return null;
				break;

			case 4:
				if (x == 'P')
					state = 5;
				else
					return null;
				break;

			case 5:
				if ('0' <= x && x <= '9')
					digits += x;
				else if (x == 'Y') {
					if (digits.length() == 0)
						return null;
					year = Integer.parseInt(digits);
					digits = "";
					state = 6;
				} else if (x == 'M') {
					if (digits.length() == 0)
						return null;
					month = Integer.parseInt(digits);
					state = 7;
				} else
					return null;
				break;

			case 6:
				if ('0' <= x && x <= '9')
					digits += x;
				else if (x == 'M') {
					if (digits.length() == 0)
						return null;
					month = Integer.parseInt(digits);
					state = 7;

				} else
					return null;
				break;

			case 7:
				return null;

			default:
				assert false;
				return null;

			}
		}

		return new XSYearMonthDuration(year, month, negative);
	}

	/**
	 * Retrives the datatype's name
	 * 
	 * @return "yearMonthDuration" which is the datatype's name
	 */
	public String type_name() {
		return "yearMonthDuration";
	}

	/**
	 * Creates a new ResultSequence consisting of the extractable time duration
	 * from the supplied ResultSequence
	 * 
	 * @param arg
	 *            The ResultSequence from which to extract
	 * @return New ResultSequence consisting of the time duration extracted
	 * @throws DynamicError
	 */
	public ResultSequence constructor(ResultSequence arg) throws DynamicError {

		if (arg.empty())
			return ResultBuffer.EMPTY;

		AnyAtomicType aat = (AnyAtomicType) arg.first();
		if (aat instanceof NumericType || aat instanceof CalendarType ||
			aat instanceof XSBoolean || aat instanceof XSBase64Binary ||
			aat instanceof XSHexBinary || aat instanceof XSAnyURI) {
			throw DynamicError.invalidType();
		}

		if (!isCastable(aat)) {
			throw DynamicError.cant_cast(null);
		}

		XSDuration ymd = castYearMonthDuration(aat);

		if (ymd == null)
			throw DynamicError.cant_cast(null);

		return ymd;
	}
	
	private XSDuration castYearMonthDuration(AnyAtomicType aat) {
		if (aat instanceof XSDuration) {
			XSDuration duration = (XSDuration) aat;
			return new XSYearMonthDuration(duration.year(), duration.month(), duration.negative());
		}
		
		return parseYMDuration(aat.getStringValue());
	}

	/**
	 * Retrieves whether this duration represents a backward passage through
	 * time
	 * 
	 * @return True if this duration represents a backward passage through time.
	 *         False otherwise
	 */
	public boolean negative() {
		return _negative;
	}

	/**
	 * Retrieves a String representation of the duration of time stored
	 * 
	 * @return String representation of the duration of time stored
	 */
	public String getStringValue() {
		String strval = "";

		if (negative())
			strval += "-";

		strval += "P";

		int years = year();
		if (years != 0)
			strval += years + "Y";

		int months = month();
		if (months == 0) {
			if (years == 0)
				strval += months + "M";
		} else
			strval += months + "M";

		return strval;
	}

	/**
	 * Retrieves the datatype's full pathname
	 * 
	 * @return "xs:yearMonthDuration" which is the datatype's full pathname
	 */
	public String string_type() {
		return XS_YEAR_MONTH_DURATION;
	}

	/**
	 * Retrieves the duration of time stored as the number of months within it
	 * 
	 * @return Number of months making up this duration of time
	 */
	public int monthValue() {
		int ret = year() * 12 + month();

		if (negative())
			ret *= -1;

		return ret;
	}

	/**
	 * Equality comparison between this and the supplied duration of time.
	 * 
	 * @param arg
	 *            The duration of time to compare with
	 * @return True if they both represent the duration of time. False otherwise
	 * @throws DynamicError
	 */
	public boolean eq(AnyType arg, DynamicContext dynamicContext) throws DynamicError {
		if (arg instanceof XSDayTimeDuration) {
			XSDayTimeDuration dayTimeDuration = (XSDayTimeDuration)arg;
			return (monthValue() == 0 && dayTimeDuration.value() == 0.0);
		} else if (arg instanceof XSYearMonthDuration) {
			XSYearMonthDuration yearMonthDuration = (XSYearMonthDuration)arg;
			return monthValue() == yearMonthDuration.monthValue();
		}
		XSDuration val = (XSDuration) NumericType
				.get_single_type(arg, XSDuration.class);
		return super.eq(val, dynamicContext);
	}

	/**
	 * Comparison between this and the supplied duration of time.
	 * 
	 * @param arg
	 *            The duration of time to compare with
	 * @return True if the supplied time represents a larger duration than that
	 *         stored. False otherwise
	 * @throws DynamicError
	 */
	public boolean lt(AnyType arg, DynamicContext context) throws DynamicError {
		XSYearMonthDuration val = (XSYearMonthDuration) NumericType
				.get_single_type(arg, XSYearMonthDuration.class);

		return monthValue() < val.monthValue();
	}

	/**
	 * Comparison between this and the supplied duration of time.
	 * 
	 * @param arg
	 *            The duration of time to compare with
	 * @return True if the supplied time represents a smaller duration than that
	 *         stored. False otherwise
	 * @throws DynamicError
	 */
	public boolean gt(AnyType arg, DynamicContext context) throws DynamicError {
		XSYearMonthDuration val = (XSYearMonthDuration) NumericType
				.get_single_type(arg, XSYearMonthDuration.class);

		return monthValue() > val.monthValue();
	}

	/**
	 * Mathematical addition between this duration stored and the supplied
	 * duration of time (of type XSYearMonthDuration)
	 * 
	 * @param arg
	 *            The duration of time to add
	 * @return New XSYearMonthDuration representing the resulting duration
	 *         after the addition
	 * @throws DynamicError
	 */
	public ResultSequence plus(ResultSequence arg) throws DynamicError {
		XSYearMonthDuration val = (XSYearMonthDuration) NumericType
				.get_single_type(arg, XSYearMonthDuration.class);

		int res = monthValue() + val.monthValue();

		return ResultSequenceFactory.create_new(new XSYearMonthDuration(res));
	}

	/**
	 * Mathematical subtraction between this duration stored and the supplied
	 * duration of time (of type XSYearMonthDuration)
	 * 
	 * @param arg
	 *            The duration of time to subtract
	 * @return New XSYearMonthDuration representing the resulting duration
	 *         after the subtraction
	 * @throws DynamicError
	 */
	public ResultSequence minus(ResultSequence arg) throws DynamicError {
		XSYearMonthDuration val = (XSYearMonthDuration) NumericType
				.get_single_type(arg, XSYearMonthDuration.class);

		int res = monthValue() - val.monthValue();

		return ResultSequenceFactory.create_new(new XSYearMonthDuration(res));
	}

	/**
	 * Mathematical multiplication between this duration stored and the supplied
	 * duration of time (of type XSYearMonthDuration)
	 * 
	 * @param arg
	 *            The duration of time to multiply by
	 * @return New XSYearMonthDuration representing the resulting duration
	 *         after the multiplication
	 * @throws DynamicError
	 */
	public ResultSequence times(ResultSequence arg) throws DynamicError {
		ResultSequence convertedRS = arg;		
		if (arg.size() == 1) {
			Item argValue = arg.first();
            if (argValue instanceof XSDecimal) {
            	convertedRS = ResultSequenceFactory.create_new(new XSDouble(argValue.getStringValue()));	
            }
		}
		
		XSDouble val = (XSDouble) NumericType.get_single_type(convertedRS,
				XSDouble.class);

		if (val.nan()) {
			throw DynamicError.nan();
		}
		
		if (val.infinite()) {
			throw DynamicError.overflowDateTime();
		}
		
		int res = (int) Math.round(monthValue() * val.double_value());

		return ResultSequenceFactory.create_new(new XSYearMonthDuration(res));
	}

	/**
	 * Mathematical division between this duration stored and the supplied
	 * duration of time (of type XSYearMonthDuration)
	 * 
	 * @param arg
	 *            The duration of time to divide by
	 * @return New XSYearMonthDuration representing the resulting duration
	 *         after the division
	 * @throws DynamicError
	 */
	public ResultSequence div(ResultSequence arg) throws DynamicError {
		if (arg.size() != 1)
			DynamicError.throw_type_error();

		Item at = arg.first();

		if (at instanceof XSDouble) {
			XSDouble dt = (XSDouble) at;

			int ret = 0;

			if (!dt.zero())
				ret = (int) Math.round(monthValue() / dt.double_value());

			return ResultSequenceFactory.create_new(new XSYearMonthDuration(
					ret));
		} else if (at instanceof XSDecimal) {
			XSDecimal dt = (XSDecimal) at;
			
			int ret = 0;
			
			if (!dt.zero())
				ret = (int) Math.round(monthValue() / dt.getValue().doubleValue());
			
			return ResultSequenceFactory.create_new(new XSYearMonthDuration(
					ret));	
		} else if (at instanceof XSYearMonthDuration) {
			XSYearMonthDuration md = (XSYearMonthDuration) at;

			double res = (double) monthValue() / md.monthValue();

			return ResultSequenceFactory.create_new(new XSDecimal(new BigDecimal(res)));
		} else {
			DynamicError.throw_type_error();
			return null; // unreach
		}
	}	

	public TypeDefinition getTypeDefinition() {
		return BuiltinTypeLibrary.XS_YEARMONTHDURATION;
	}

}
