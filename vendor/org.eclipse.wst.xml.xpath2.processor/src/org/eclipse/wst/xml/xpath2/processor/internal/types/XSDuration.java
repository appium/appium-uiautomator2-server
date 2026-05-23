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
 *     David Carver (STAR) - bug 282223 - Implemented XSDuration type for castable checking.
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.types;

import org.eclipse.wst.xml.xpath2.api.DynamicContext;
import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeDefinition;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.function.CmpEq;
import org.eclipse.wst.xml.xpath2.processor.internal.function.CmpGt;
import org.eclipse.wst.xml.xpath2.processor.internal.function.CmpLt;
import org.eclipse.wst.xml.xpath2.processor.internal.types.builtin.BuiltinTypeLibrary;

/**
 * A representation of the xs:duration data type. Other duration implementations
 * should inherit from this implementation.
 * 
 * @since 1.1 This used to be an abstract class but was incorrectly implemented
 *        as such.
 */
public class XSDuration extends CtrType implements CmpEq, CmpLt, CmpGt, Cloneable {

	private static final String XS_DURATION = "xs:duration";
	protected int _year;
	protected int _month;
	protected int _days;
	protected int _hours;
	protected int _minutes;
	protected double _seconds;
	protected boolean _negative;

	/**
	 * Initializes to the supplied parameters. If more than 24 hours is
	 * supplied, the number of days is adjusted accordingly. The same occurs for
	 * minutes and seconds
	 * 
	 * @param years
	 *            Number of years in this duration of time.
	 * @param months
	 *            Number of months in this duration of time.
	 * @param days
	 *            Number of days in this duration of time
	 * @param hours
	 *            Number of hours in this duration of time
	 * @param minutes
	 *            Number of minutes in this duration of time
	 * @param seconds
	 *            Number of seconds in this duration of time
	 * @param negative
	 *            True if this duration of time represents a backwards passage
	 *            through time. False otherwise
	 */
	public XSDuration(int years, int months, int days, int hours, int minutes,
			double seconds, boolean negative) {
		_year = years;
		_month = months;
		_days = days;
		_hours = hours;
		_minutes = minutes;
		_seconds = seconds;
		_negative = negative;

		if (_month >= 12) {
			_year += _month / 12;
			_month = _month % 12;
		}

		if (_seconds >= 60) {
			int isec = (int) _seconds;
			double rem = _seconds - (isec);

			_minutes += isec / 60;
			_seconds = isec % 60;
			_seconds += rem;
		}
		if (_minutes >= 60) {
			_hours += _minutes / 60;
			_minutes = _minutes % 60;
		}
		if (_hours >= 24) {
			_days += _hours / 24;
			_hours = _hours % 24;
		}

	}

	/**
	 * Initialises to the given number of seconds
	 * 
	 * @param secs
	 *            Number of seconds in the duration of time
	 */
	public XSDuration(double secs) {
		this(0, 0, 0, 0, 0, Math.abs(secs), secs < 0);
	}

	/**
	 * Initialises to a duration of no time (0days, 0hours, 0minutes, 0seconds)
	 */
	public XSDuration() {
		this(0, 0, 0, 0, 0, 0.0, false);
	}

	public String type_name() {
		return "duration";
	}

	public String string_type() {
		return XS_DURATION;
	}

	/**
	 * Retrieves a String representation of the duration stored
	 * 
	 * @return String representation of the duration stored
	 */
	public String getStringValue() {
		String ret = "";
		boolean did_something = false;
		String tret = "";

		if (negative() && !(days() == 0 && hours() == 0 && seconds() == 0))
			ret += "-";

		ret += "P";

		int years = year();
		if (years != 0)
			ret += years + "Y";

		int months = month();
		if (months != 0) {
			ret += months + "M";
		}

		if (days() != 0) {
			ret += days() + "D";
			did_something = true;
		}

		// do the "time" bit
		int hours = hours();
		int minutes = minutes();
		double seconds = seconds();
		
		if (hours != 0) {
			tret += hours + "H";
			did_something = true;
		}
		if (minutes != 0) {
			tret += minutes + "M";
			did_something = true;
		}
		if (seconds != 0) {
			String doubStr = (new Double(seconds).toString());
			if (doubStr.endsWith(".0")) {
				// string value of x.0 seconds is xS. e.g, 7.0S is converted to
				// 7S.
				tret += doubStr.substring(0, doubStr.indexOf(".0")) + "S";
			} else {
				tret += seconds + "S";
			}
			did_something = true;
		} else if (!did_something) {
				tret += "0S";
		}
		
		if ((year() == 0 && month() == 0) || (hours > 0 || minutes > 0 || seconds > 0)) {
			if (tret.length() > 0) {
				ret += "T" + tret;
			}
		}

		return ret;
	}

	/**
	 * Retrieves the number of days within the duration of time stored
	 * 
	 * @return Number of days within the duration of time stored
	 */
	public int days() {
		return _days;
	}

	/**
	 * Retrieves the number of minutes (max 60) within the duration of time
	 * stored
	 * 
	 * @return Number of minutes within the duration of time stored
	 */
	public int minutes() {
		return _minutes;
	}

	/**
	 * Retrieves the number of hours (max 24) within the duration of time stored
	 * 
	 * @return Number of hours within the duration of time stored
	 */
	public int hours() {
		return _hours;
	}

	/**
	 * Retrieves the number of seconds (max 60) within the duration of time
	 * stored
	 * 
	 * @return Number of seconds within the duration of time stored
	 */
	public double seconds() {
		return _seconds;
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
		XSDuration val = (XSDuration) NumericType.get_single_type(arg,
				XSDuration.class);

		return value() == val.value();
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
		XSDuration val = (XSDuration) NumericType.get_single_type(arg,
				XSDayTimeDuration.class);

		return value() < val.value();
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
		XSDuration val = (XSDuration) NumericType.get_single_type(arg,
				XSDayTimeDuration.class);

		return value() > val.value();
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
	 * Retrieves the duration of time stored as the number of seconds within it
	 * 
	 * @return Number of seconds making up this duration of time
	 */
	public double value() {
		double ret = days() * 24 * 60 * 60;

		ret += hours() * 60 * 60;
		ret += minutes() * 60;
		ret += seconds();

		if (negative())
			ret *= -1;
		
		

		return ret;
	}
	
	public double time_value() {
		double ret = 0;
		ret += hours() * 60 * 60;
		ret += minutes() * 60;
		ret += seconds();

		if (negative())
			ret *= -1;
		return ret;
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

		if (!(isCastable(aat))) {
			throw DynamicError.cant_cast(null);
		}
		
		XSDuration duration = castDuration(aat);

		if (duration == null)
			throw DynamicError.cant_cast(null);

		return duration;
	}

	private XSDuration castDuration(AnyAtomicType aat) {
		if (aat instanceof XSDuration) {
			XSDuration duration = (XSDuration) aat;
			return new XSDuration(duration.year(), duration.month(), duration.days(), duration.hours(), duration.minutes(), duration.seconds(), duration.negative());
		}
		
		return parseDTDuration(aat.getStringValue());
	}
	/**
	 * Creates a new XSDayTimeDuration by parsing the supplied String
	 * represented duration of time
	 * 
	 * @param str
	 *            String represented duration of time
	 * @return New XSDayTimeDuration representing the duration of time supplied
	 */
	public static XSDuration parseDTDuration(String str) {
		boolean negative = false;
		int years = 0;
		int months = 0;
		int days = 0;
		int hours = 0;
		int minutes = 0;
		double seconds = 0;

		// string following the P
		String pstr = "";
		String tstr = "";

		// get the negative and pstr
		if (str.startsWith("-P")) {
			negative = true;
			pstr = str.substring(2, str.length());
		} else if (str.startsWith("P")) {
			negative = false;
			pstr = str.substring(1, str.length());
		} else
			return null;

		try {
			int index = pstr.indexOf('Y');
			boolean did_something = false;

			if (index != -1) {
				String digit = pstr.substring(0, index);
				years = Integer.parseInt(digit);
				pstr = pstr.substring(index + 1, pstr.length());
				did_something = true;
			}

			index = pstr.indexOf('M');
			if (index != -1) {
				String digit = pstr.substring(0, index);
				months = Integer.parseInt(digit);
				pstr = pstr.substring(index + 1, pstr.length());
				did_something = true;
			}

			// get the days
			index = pstr.indexOf('D');

			if (index == -1) {
				if (pstr.startsWith("T")) {
					tstr = pstr.substring(1, pstr.length());
				}
			} else {
				String digit = pstr.substring(0, index);
				days = Integer.parseInt(digit);
				tstr = pstr.substring(index + 1, pstr.length());

				if (tstr.startsWith("T")) {
					tstr = tstr.substring(1, tstr.length());
				} else {
					tstr = "";
					did_something = true;
				}
			}

			// do the T str

			// hour
			index = tstr.indexOf('H');
			if (index != -1) {
				String digit = tstr.substring(0, index);
				hours = Integer.parseInt(digit);
				tstr = tstr.substring(index + 1, tstr.length());
				did_something = true;
			}
			// minute
			index = tstr.indexOf('M');
			if (index != -1) {
				String digit = tstr.substring(0, index);
				minutes = Integer.parseInt(digit);
				tstr = tstr.substring(index + 1, tstr.length());
				did_something = true;
			}
			// seconds
			index = tstr.indexOf('S');
			if (index != -1) {
				String digit = tstr.substring(0, index);
				seconds = Double.parseDouble(digit);
				tstr = tstr.substring(index + 1, tstr.length());
				did_something = true;
			}
			if (!did_something) {
				return null;
			}

		} catch (NumberFormatException err) {
			return null;
		}

		return new XSDuration(years, months, days, hours, minutes, seconds,
				negative);
	}

	public Object clone() throws CloneNotSupportedException {
		return new XSDuration(year(), month(), days(), hours(), minutes(),
				seconds(), negative());
	}

	/**
	 * Retrieves the number of years within the duration of time stored
	 * 
	 * @return Number of years within the duration of time stored
	 */
	public int year() {
		return _year;
	}

	/**
	 * Retrieves the number of months within the duration of time stored
	 * 
	 * @return Number of months within the duration of time stored
	 */
	public int month() {
		return _month;
	}

	protected boolean isCastable(AnyAtomicType aat) {
		String value = aat.getStringValue(); // get this once so we don't recreate everytime.
		String type = aat.string_type();
		if (type.equals("xs:string") || type.equals("xs:untypedAtomic")) {
			if (isDurationValue(value)) {
				return true;  // We might be able to cast this.
			}
		}
		
		// We can cast from ourself or derivations of ourselves.
		if (aat instanceof XSDuration) {
			return true;
		}
				
		return false;
	}

	private boolean isDurationValue(String value) {
		return value.startsWith("P") || value.startsWith("-P");
	}
	

	public TypeDefinition getTypeDefinition() {
		return BuiltinTypeLibrary.XS_DURATION;
	}

	public Object getNativeValue() {
		return _datatypeFactory.newDuration(! negative(), year(), month(), days(), hours(), minutes(), (int)seconds());
	}
}
