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
 *     David Carver - bug 282223 - implementation of xs:duration data type.
 *                                 correction of casting to time. 
 *     David Carver - bug 280547 - fix dates for comparison 
 *     Jesper Steen Moller - bug 262765 - fix type tests
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
 * A representation of the Time datatype
 */
public class XSTime extends CalendarType implements CmpEq, CmpLt, CmpGt,

MathMinus, MathPlus,

Cloneable {

	private static final String XS_TIME = "xs:time";
	private Calendar _calendar;
	private boolean _timezoned;
	private XSDuration _tz;

	/**
	 * Initialises to the supplied time and timezone
	 * 
	 * @param cal
	 *            Calendar representation of the time to be stored
	 * @param tz
	 *            The timezone (possibly null) associated with this time
	 */
	public XSTime(Calendar cal, XSDuration tz) {
		_calendar = cal;

		_tz = tz;
		if (tz == null)
			_timezoned = false;
		else
			_timezoned = true;
	}

	/**
	 * Initialises to the current time
	 */
	public XSTime() {
		this(new GregorianCalendar(TimeZone.getTimeZone("GMT")), null);
	}

	/**
	 * Creates a new copy of the time (and timezone) stored
	 * 
	 * @return New XSTime representing the copy of the time and timezone
	 * @throws CloneNotSupportedException
	 */
	public Object clone() throws CloneNotSupportedException {
		Calendar c = (Calendar) calendar().clone();
		XSDuration t = tz();

		if (t != null)
			t = (XSDuration) t.clone();

		return new XSTime(c, t);
	}

	/**
	 * Retrieves the datatype's name
	 * 
	 * @return "time" which is the datatype's name
	 */
	public String type_name() {
		return "time";
	}

	/**
	 * Creates a new XSTime representing the String represented supplied time
	 * 
	 * @param str
	 *            String represented time and timezone to be stored
	 * @return New XSTime representing the supplied time
	 */
	public static CalendarType parse_time(String str) {

		String startdate = "1983-11-29T";
		
		XSDateTime dt = XSDateTime.parseDateTime(startdate + str);
		if (dt == null)
			return null;

		return new XSTime(dt.calendar(), dt.tz());
	}

	/**
	 * Creates a new ResultSequence consisting of the extractable time from the
	 * supplied ResultSequence
	 * 
	 * @param arg
	 *            The ResultSequence from which to extract the time
	 * @return New ResultSequence consisting of the supplied time
	 * @throws DynamicError
	 */
	public ResultSequence constructor(ResultSequence arg) throws DynamicError {
		if (arg.empty())
			return ResultBuffer.EMPTY;

		AnyAtomicType aat = (AnyAtomicType) arg.first();
		if (!isCastable(aat)) {
			throw DynamicError.invalidType();
		}
		
		CalendarType t = castTime(aat);

		if (t == null)
			throw DynamicError.cant_cast(null);

		return t;
	} 

	private boolean isCastable(AnyAtomicType aat) {
		if (aat instanceof XSString || aat instanceof XSUntypedAtomic) {
			return true;
		}
		
		if (aat instanceof XSDateTime) {
			return true;
		}
		
		if (aat instanceof XSTime) {
			return true;
		}
		return false;
	}
	
	private CalendarType castTime(AnyAtomicType aat) {
		if (aat instanceof XSTime) {
			XSTime time = (XSTime) aat;
			return new XSTime(time.calendar(), time.tz());
		}
		if (aat instanceof XSDateTime) {
			XSDateTime dateTime = (XSDateTime) aat;
			return new XSTime(dateTime.calendar(), dateTime.tz());
		}
		
		return parse_time(aat.getStringValue());
	}
	/**
	 * Retrieves the hour stored as an integer
	 * 
	 * @return The hour stored
	 */
	public int hour() {
		return _calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * Retrieves the minute stored as an integer
	 * 
	 * @return The minute stored
	 */
	public int minute() {
		return _calendar.get(Calendar.MINUTE);
	}

	/**
	 * Retrieves the seconds stored as an integer
	 * 
	 * @return The second stored
	 */
	public double second() {
		double s = _calendar.get(Calendar.SECOND);

		double ms = _calendar.get(Calendar.MILLISECOND);

		ms /= 1000;

		s += ms;
		return s;
	}

	/**
	 * Check for whether the time stored has a timezone associated with it
	 * 
	 * @return True if the time has a timezone associated. False otherwise
	 */
	public boolean timezoned() {
		return _timezoned;
	}

	/**
	 * Retrieves a String representation of the time stored
	 * 
	 * @return String representation of the time stored
	 */
	public String getStringValue() {
		String ret = "";
		
		Calendar adjustFortimezone = calendar();
		ret += XSDateTime.pad_int(adjustFortimezone.get(Calendar.HOUR_OF_DAY), 2);
		
		ret += ":";
		ret += XSDateTime.pad_int(adjustFortimezone.get(Calendar.MINUTE), 2);
		

		ret += ":";
		int isecond = (int) second();
		double sec = second();

		if ((sec - (isecond)) == 0.0)
			ret += XSDateTime.pad_int(isecond, 2);
		else {
			if (sec < 10.0)
				ret += "0" + sec;
			else
				ret += sec;
		}

		if (timezoned()) {
			int hrs = _tz.hours();
			int min = _tz.minutes();
			double secs = _tz.seconds();
			if (hrs == 0 && min == 0 && secs == 0) {
			  ret += "Z";
			}
			else {
			  String tZoneStr = "";
			  if (_tz.negative()) {
				tZoneStr += "-";  
			  }
			  else {
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
	 * Retrieves the datatype's full pathname
	 * 
	 * @return "xs:time" which is the datatype's full pathname
	 */
	public String string_type() {
		return XS_TIME;
	}

	/**
	 * Retrieves a Calendar representation of time stored
	 * 
	 * @return Calendar representation of the time stored
	 */
	public Calendar calendar() {
		return _calendar;
	}

	/**
	 * Retrieves the timezone associated with the time stored as a duration of
	 * time
	 * 
	 * @return The duration of time between the time stored and the actual time
	 *         after the timezone is taken into account
	 */
	public XSDuration tz() {
		return _tz;
	}

	/**
	 * Retrieves the time in milliseconds since the epoch
	 * 
	 * @return time stored in milliseconds since the epoch
	 */
	public double value() {
		return calendar().getTimeInMillis() / 1000.0;
	}

	/**
	 * Equality comparison between this and the supplied XSTime representation
	 * 
	 * @param arg
	 *            The XSTime to compare with
	 * @return True if both XSTime's represent the same time. False otherwise
	 * @throws DynamicError
	 */
	public boolean eq(AnyType arg, DynamicContext dynamicContext) throws DynamicError {
		XSTime val = (XSTime) NumericType.get_single_type(arg, XSTime.class);
		Calendar thiscal = normalizeCalendar(calendar(), tz());
		Calendar thatcal = normalizeCalendar(val.calendar(), val.tz());

		return thiscal.equals(thatcal);
	}

	/**
	 * Comparison between this and the supplied XSTime representation
	 * 
	 * @param arg
	 *            The XSTime to compare with
	 * @return True if the supplied time represnts a point in time after that
	 *         represented by the time stored. False otherwise
	 */
	public boolean lt(AnyType arg, DynamicContext context) throws DynamicError {
		XSTime val = (XSTime) NumericType.get_single_type(arg, XSTime.class);
		Calendar thiscal = normalizeCalendar(calendar(), tz());
		Calendar thatcal = normalizeCalendar(val.calendar(), val.tz());
		return thiscal.before(thatcal);
	}
	
	/**
	 * Comparison between this and the supplied XSTime representation
	 * 
	 * @param arg
	 *            The XSTime to compare with
	 * @return True if the supplied time represnts a point in time before that
	 *         represented by the time stored. False otherwise
	 * @throws DynamicError
	 */
	public boolean gt(AnyType arg, DynamicContext context) throws DynamicError {
		XSTime val = (XSTime) NumericType.get_single_type(arg, XSTime.class);
		Calendar thiscal = normalizeCalendar(calendar(), tz());
		Calendar thatcal = normalizeCalendar(val.calendar(), val.tz());

		return thiscal.after(thatcal);
	}

	/**
	 * Mathematical subtraction between this time stored and the supplied
	 * representation. This supplied representation must be of either type
	 * XSTime (in which case the result is the duration of time between these
	 * two times) or a XSDayTimeDuration (in which case the result is the time
	 * when this duration is subtracted from the time stored).
	 * 
	 * @param arg
	 *            The representation to subtract (either XSTim or
	 *            XDTDayTimeDuration)
	 * @return A ResultSequence representing the result of the subtraction
	 */
	public ResultSequence minus(ResultSequence arg) throws DynamicError {
		if (arg.size() != 1)
			DynamicError.throw_type_error();

		Item at = arg.first();
		
		if (!(at instanceof XSTime) && !(at instanceof XSDayTimeDuration)) {
			throw DynamicError.throw_type_error();
		}

		if (at instanceof XSTime) {
			return minusXSTimeDuration(at);
		}
		
		if (at instanceof XSDayTimeDuration) {
			return minusXSDayTimeDuration(at);
		}
		return null; // unreach
	}

	private ResultSequence minusXSDayTimeDuration(Item at) {
		XSDuration val = (XSDuration) at;

		XSTime res = null;
		try {
			res = (XSTime) clone();
		} catch (CloneNotSupportedException err) {
			return null;
		}

		XMLGregorianCalendar xmlCal = _datatypeFactory.newXMLGregorianCalendar((GregorianCalendar)calendar());
		Duration dtduration = _datatypeFactory.newDuration(val.getStringValue());
		xmlCal.add(dtduration.negate());
		res = new XSTime(xmlCal.toGregorianCalendar(), res.tz());

		return ResultSequenceFactory.create_new(res);
	}

	private ResultSequence minusXSTimeDuration(Item at) {
		XSTime val = (XSTime) at;
		Duration dtduration = null;
		Calendar thisCal = normalizeCalendar(calendar(), tz());
		Calendar thatCal = normalizeCalendar(val.calendar(), val.tz());
		long duration = thisCal.getTimeInMillis() - thatCal.getTimeInMillis();
		dtduration = _datatypeFactory.newDuration(duration);
		return ResultSequenceFactory.create_new(XSDayTimeDuration.parseDTDuration(dtduration.toString()));
	}

	/**
	 * Mathematical addition between this time stored and the supplied time
	 * duration.
	 * 
	 * @param arg
	 *            A XDTDayTimeDuration representation of the duration of time to
	 *            add
	 * @return A XSTime representing the result of this addition.
	 * @throws DynamicError
	 */
	public ResultSequence plus(ResultSequence arg) throws DynamicError {
		XSDuration val = (XSDuration) NumericType
				.get_single_type(arg, XSDayTimeDuration.class);

		try {
			double ms = val.time_value() * 1000.0;

			XSTime res = (XSTime) clone();

			res.calendar().add(Calendar.MILLISECOND, (int) ms);

			return ResultSequenceFactory.create_new(res);
		} catch (CloneNotSupportedException err) {
			assert false;
			return null;
		}
	}

	public TypeDefinition getTypeDefinition() {
		return BuiltinTypeLibrary.XS_TIME;
	}

	public Object getNativeValue() {
		return _calendar.clone();
	}	
}
