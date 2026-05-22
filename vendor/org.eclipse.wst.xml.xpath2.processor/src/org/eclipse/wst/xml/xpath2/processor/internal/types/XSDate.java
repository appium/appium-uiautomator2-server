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
 *     Mukul Gandhi - bug 274792 - improvements to xs:date constructor function.
 *     David Carver - bug 282223 - implementation of xs:duration.
 *                                 fixed casting issue. 
 *     David Carver - bug 280547 - fix dates for comparison 
 *     Jesper Steen Moller  - bug 262765 - fix type tests
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.types;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;

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
import org.eclipse.wst.xml.xpath2.processor.internal.function.MathMinus;
import org.eclipse.wst.xml.xpath2.processor.internal.function.MathPlus;
import org.eclipse.wst.xml.xpath2.processor.internal.types.builtin.BuiltinTypeLibrary;

/**
 * Representation of a date of the form year-month-day and optional timezone
 */
public class XSDate extends CalendarType implements CmpEq, CmpLt, CmpGt,

MathMinus, MathPlus,

Cloneable {
	private static final String XS_DATE = "xs:date";
	private Calendar _calendar;
	private boolean _timezoned;
	private XSDuration _tz;

	/**
	 * Initializes a new representation of a supplied date
	 * 
	 * @param cal
	 *            The Calendar representation of the date to be stored
	 * @param tz
	 *            The time zone of the date to be stored.
	 */
	public XSDate(Calendar cal, XSDuration tz) {
		_calendar = cal;

		_tz = tz;
		if (tz == null)
			_timezoned = false;
		else
			_timezoned = true;
	}

	/**
	 * Initializes a new representation of the current date
	 */
	public XSDate() {
		this(new GregorianCalendar(TimeZone.getTimeZone("GMT")), null);
	}

	/**
	 * Retrieves the datatype name
	 * 
	 * @return "date" which is the dataype name
	 */
	public String type_name() {
		return "date";
	}

	/**
	 * Creates a copy of this date representation
	 * 
	 * @return A copy of this date representation
	 */
	public Object clone() throws CloneNotSupportedException {
		Calendar c = (Calendar) calendar().clone();
		XSDuration t = tz();

		if (t != null)
			t = (XSDuration) t.clone();

		return new XSDate(c, t);
	}

	/**
	 * Parses a String representation of a date (of the form year-month-day or
	 * year-month-day+timezone) and constructs a new XSDate representation of
	 * it.
	 * 
	 * @param str
	 *            The String representation of the date (and optional timezone)
	 * @return The XSDate representation of the supplied date
	 */
	public static XSDate parse_date(String str) {

		String date = "";
		String time = "T00:00:00.0";

		int index = str.indexOf('+', 1);
		if (index == -1) {
			index = str.indexOf('-', 1);
			if (index == -1)
				return null;
			index = str.indexOf('-', index + 1);
			if (index == -1)
				return null;
			index = str.indexOf('-', index + 1);
		}
		if (index == -1)
			index = str.indexOf('Z', 1);
		if (index != -1) {
			date = str.substring(0, index);
			// here we go
			date += time;
			date += str.substring(index, str.length());
		} else {
			date = str + time;
		}

		// sorry again =D
		XSDateTime dt = XSDateTime.parseDateTime(date);
		if (dt == null)
			return null;

		return new XSDate(dt.calendar(), dt.tz());
	}

	/**
	 * Creates a new result sequence consisting of the retrievable date value in
	 * the supplied result sequence
	 * 
	 * @param arg
	 *            The result sequence from which to extract the date value.
	 * @throws DynamicError
	 * @return A new result sequence consisting of the date value supplied.
	 */
	public ResultSequence constructor(ResultSequence arg) throws DynamicError {
		if (arg.empty())
			return ResultBuffer.EMPTY;

		Item aat = arg.first();

		if (!isCastable(aat)) {
			throw DynamicError.invalidType();
		}

		XSDate dt = castDate(aat);

		if (dt == null)
			throw DynamicError.cant_cast(null);

		return dt;
	}

	private boolean isCastable(Item aat) {

		// We might be able to cast these.
		if (aat instanceof XSString || aat instanceof XSUntypedAtomic
				|| aat instanceof NodeType) {
			return true;
		}

		if (aat instanceof XSTime) {
			return false;
		}

		if (aat instanceof XSDateTime) {
			return true;

		}

		if (aat instanceof XSDate) {
			return true;
		}

		return false;
	}

	private XSDate castDate(Item aat) {
		if (aat instanceof XSDate) {
			XSDate date = (XSDate) aat;
			return new XSDate(date.calendar(), date.tz());
		}

		if (aat instanceof XSDateTime) {
			XSDateTime dateTime = (XSDateTime) aat;
			return new XSDate(dateTime.calendar(), dateTime.tz());
		}

		return parse_date(aat.getStringValue());
	}

	/**
	 * Retrieve the year from the date stored
	 * 
	 * @return the year value of the date stored
	 */
	public int year() {
		int y = _calendar.get(Calendar.YEAR);
		if (_calendar.get(Calendar.ERA) == GregorianCalendar.BC)
			y *= -1;

		return y;
	}

	/**
	 * Retrieve the month from the date stored
	 * 
	 * @return the month value of the date stored
	 */
	public int month() {
		return _calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * Retrieve the day from the date stored
	 * 
	 * @return the day value of the date stored
	 */
	public int day() {
		return _calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * Retrieves whether this date has an optional timezone associated with it
	 * 
	 * @return True if there is a timezone associated with this date. False
	 *         otherwise.
	 */
	public boolean timezoned() {
		return _timezoned;
	}

	/**
	 * Retrieves a String representation of the date stored
	 * 
	 * @return String representation of the date stored
	 */
	public String getStringValue() {
		String ret = "";

		Calendar adjustFortimezone = calendar();

		if (adjustFortimezone.get(Calendar.ERA) == GregorianCalendar.BC) {
			ret += "-";
		}

		ret += XSDateTime.pad_int(adjustFortimezone.get(Calendar.YEAR), 4);

		ret += "-";
		ret += XSDateTime.pad_int(month(), 2);

		ret += "-";
		ret += XSDateTime.pad_int(adjustFortimezone.get(Calendar.DAY_OF_MONTH),
				2);

		if (timezoned()) {
			int hrs = _tz.hours();
			int min = _tz.minutes();
			double secs = _tz.seconds();
			if (hrs == 0 && min == 0 && secs == 0) {
				ret += "Z";
			} else {
				String tZoneStr = "";
				if (_tz.negative()) {
					tZoneStr += "-";
				} else {
					tZoneStr += "+";
				}
				tZoneStr += XSDateTime.pad_int(hrs, 2);
				tZoneStr += ":";
				tZoneStr += XSDateTime.pad_int(min, 2);

				ret += tZoneStr;
			}
		}

		return ret;
	}

	/**
	 * Retrive the datatype full pathname
	 * 
	 * @return "xs:date" which is the datatype full pathname
	 */
	public String string_type() {
		return XS_DATE;
	}

	/**
	 * Retrieves the Calendar representation of the date stored
	 * 
	 * @return Calendar representation of the date stored
	 */
	public Calendar calendar() {
		return _calendar;
	}

	/**
	 * Retrieves the timezone associated with the date stored
	 * 
	 * @return the timezone associated with the date stored
	 */
	public XSDuration tz() {
		return _tz;
	}

	// comparisons
	/**
	 * Equality comparison on this and the supplied dates (taking timezones into
	 * account)
	 * 
	 * @param arg
	 *            XSDate representation of the date to compare to
	 * @throws DynamicError
	 * @return True if the two dates are represent the same exact point in time.
	 *         False otherwise.
	 */
	public boolean eq(AnyType arg, DynamicContext dynamicContext) throws DynamicError {
		XSDate val = (XSDate) NumericType.get_single_type((Item)arg, XSDate.class);
		Calendar thiscal = normalizeCalendar(calendar(), tz());
		Calendar thatcal = normalizeCalendar(val.calendar(), val.tz());

		return thiscal.equals(thatcal);
	}

	/**
	 * Comparison on this and the supplied dates (taking timezones into account)
	 * 
	 * @param arg
	 *            XSDate representation of the date to compare to
	 * @throws DynamicError
	 * @return True if in time, this date lies before the date supplied. False
	 *         otherwise.
	 */
	public boolean lt(AnyType arg, DynamicContext context) throws DynamicError {
		XSDate val = (XSDate) NumericType.get_single_type((Item)arg, XSDate.class);
		Calendar thiscal = normalizeCalendar(calendar(), tz());
		Calendar thatcal = normalizeCalendar(val.calendar(), val.tz());

		return thiscal.before(thatcal);
	}

	/**
	 * Comparison on this and the supplied dates (taking timezones into account)
	 * 
	 * @param arg
	 *            XSDate representation of the date to compare to
	 * @throws DynamicError
	 * @return True if in time, this date lies after the date supplied. False
	 *         otherwise.
	 */
	public boolean gt(AnyType arg, DynamicContext context) throws DynamicError {
		XSDate val = (XSDate) NumericType.get_single_type((Item)arg, XSDate.class);
		Calendar thiscal = normalizeCalendar(calendar(), tz());
		Calendar thatcal = normalizeCalendar(val.calendar(), val.tz());

		return thiscal.after(thatcal);
	}

	// XXX this is incorrect [epoch]
	/**
	 * Currently unsupported method. Retrieves the date in milliseconds since
	 * the begining of epoch
	 * 
	 * @return Number of milliseconds since the begining of the epoch
	 */
	public double value() {
		return calendar().getTimeInMillis() / 1000.0;
	}

	// math
	/**
	 * Mathematical minus operator between this XSDate and a supplied result
	 * sequence (XSDate, XSYearMonthDuration and XSDayTimeDuration are only
	 * valid ones).
	 * 
	 * @param arg
	 *            The supplied ResultSequence that is on the right of the minus
	 *            operator. If this is an XSDate, the result will be a
	 *            XSDayTimeDuration of the duration of time between these two
	 *            dates. If arg is an XSYearMonthDuration or an
	 *            XSDayTimeDuration the result will be a XSDate of the result of
	 *            the current date minus the duration of time supplied.
	 * @return New ResultSequence consisting of the result of the mathematical
	 *         minus operation.
	 */
	public ResultSequence minus(ResultSequence arg) throws DynamicError {
		if (arg.size() != 1)
			throw DynamicError.throw_type_error();

		Item at = arg.first();

		if (!(at instanceof XSDate) && !(at instanceof XSYearMonthDuration)
				&& !(at instanceof XSDayTimeDuration)) {
			throw DynamicError.throw_type_error();
		}

		if (at instanceof XSDate) {
			return minusXSDate(arg);
		}
		
		if (at instanceof XSYearMonthDuration) {
			return minusXSYearMonthDuration((XSYearMonthDuration)at);
		}
		
		if (at instanceof XSDayTimeDuration) {
			return minusXSDayTimeDuration((XSDayTimeDuration)at);
		}

		return null;
	}

	private ResultSequence minusXSDayTimeDuration(AnyType at) {
		XSDuration val = (XSDuration) at;

		try {
			XSDate res = (XSDate) clone();
			XMLGregorianCalendar xmlCal = _datatypeFactory
					.newXMLGregorianCalendar(
							(GregorianCalendar) calendar());
			Duration dtduration = _datatypeFactory
					.newDuration(val.getStringValue());
			xmlCal.add(dtduration.negate());
			res = new XSDate(xmlCal.toGregorianCalendar(), res.tz());
			return ResultSequenceFactory.create_new(res);
		} catch (CloneNotSupportedException ex) {
		}
		return null;
	}

	private ResultSequence minusXSYearMonthDuration(AnyType at) {
		XSYearMonthDuration val = (XSYearMonthDuration) at;
		try {
			XSDate res = (XSDate) clone();

			res.calendar().add(Calendar.MONTH, val.monthValue() * -1);
			return ResultSequenceFactory.create_new(res);
		} catch (CloneNotSupportedException ex) {

		}
		return null;
	}

	private ResultSequence minusXSDate(ResultSequence arg) throws DynamicError {
		XSDate val = (XSDate) NumericType.get_single_type(arg, XSDate.class);
		Duration dtduration = null;
		Calendar thisCal = normalizeCalendar(calendar(), tz());
		Calendar thatCal = normalizeCalendar(val.calendar(), val.tz());
		long duration = thisCal.getTimeInMillis()
				- thatCal.getTimeInMillis();
		dtduration = _datatypeFactory.newDuration(duration);
		return ResultSequenceFactory.create_new(XSDayTimeDuration
				.parseDTDuration(dtduration.toString()));
	}

	/**
	 * Mathematical addition operator between this XSDate and a supplied result
	 * sequence (XDTYearMonthDuration and XDTDayTimeDuration are only valid
	 * ones).
	 * 
	 * @param arg
	 *            The supplied ResultSequence that is on the right of the minus
	 *            operator. If arg is an XDTYearMonthDuration or an
	 *            XDTDayTimeDuration the result will be a XSDate of the result
	 *            of the current date minus the duration of time supplied.
	 * @return New ResultSequence consisting of the result of the mathematical
	 *         minus operation.
	 */
	public ResultSequence plus(ResultSequence arg) throws DynamicError {
		if (arg.size() != 1)
			DynamicError.throw_type_error();

		Item at = arg.first();

		try {
			if (at instanceof XSYearMonthDuration) {
				XSYearMonthDuration val = (XSYearMonthDuration) at;

				XSDate res = (XSDate) clone();

				res.calendar().add(Calendar.MONTH, val.monthValue());
				return ResultSequenceFactory.create_new(res);
			} else if (at instanceof XSDayTimeDuration) {
				XSDayTimeDuration val = (XSDayTimeDuration) at;

				XSDate res = (XSDate) clone();

				// We only need to add the Number of days dropping the rest.
				int days = val.days();
				if (val.negative()) {
					days *= -1;
				}
				res.calendar().add(Calendar.DAY_OF_MONTH, days);

				res.calendar().add(Calendar.MILLISECOND,
						(int) (val.time_value() * 1000.0));
				return ResultSequenceFactory.create_new(res);
			} else {
				DynamicError.throw_type_error();
				return null; // unreach
			}
		} catch (CloneNotSupportedException err) {
			assert false;
			return null;
		}

	}
	public TypeDefinition getTypeDefinition() {
		return BuiltinTypeLibrary.XS_DATE;
	}

}
