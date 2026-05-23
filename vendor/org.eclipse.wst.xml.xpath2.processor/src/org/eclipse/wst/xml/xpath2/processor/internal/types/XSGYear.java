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
 *     David Carver (STAR) - bug 282223 - fix casting issues. 
 *     David Carver - bug 280547 - fix dates for comparison 
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.types;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.eclipse.wst.xml.xpath2.api.DynamicContext;
import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeDefinition;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.function.CmpEq;
import org.eclipse.wst.xml.xpath2.processor.internal.types.builtin.BuiltinTypeLibrary;

/**
 * A representation of the gMonth datatype
 */
public class XSGYear extends CalendarType implements CmpEq {

	private static final String XS_G_YEAR = "xs:gYear";
	private Calendar _calendar;
	private boolean _timezoned;
	private XSDuration _tz;


	/**
	 * Initialises a representation of the supplied month
	 * 
	 * @param cal
	 *            Calendar representation of the month to be stored
	 * @param tz
	 *            Timezone associated with this month
	 */
	public XSGYear(Calendar cal, XSDuration tz) {
		_calendar = cal;
		if (tz != null) {
			_timezoned = true;
			_tz = tz;
		}
		
	}

	/**
	 * Initialises a representation of the current year
	 */
	public XSGYear() {
		this(new GregorianCalendar(TimeZone.getTimeZone("GMT")), null);
	}

	/**
	 * Retrieves the datatype's name
	 * 
	 * @return "gYear" which is the datatype's name
	 */
	public String type_name() {
		return "gYear";
	}

	/**
	 * Parses a String representation of a year and constructs a new XSGYear
	 * representation of it.
	 * 
	 * @param str
	 *            The String representation of the year (and optional timezone)
	 * @return The XSGYear representation of the supplied date
	 */
	public static XSGYear parse_gYear(String str) {


		String year = "";
		String monthDaytime = "-01-01T00:00:00.0";
		

		int index = str.indexOf('+', 1);
		if (index == -1)
			index = str.indexOf('-', 1);
		if (index == -1)
			index = str.indexOf('Z', 1);
		if (index != -1) {
			year = str.substring(0, index);
			year += monthDaytime;
			year += str.substring(index, str.length());
		} else {
			year = str + monthDaytime;
		}

		XSDateTime dt = XSDateTime.parseDateTime(year);
		if (dt == null)
			return null;

		return new XSGYear(dt.calendar(), dt.tz());
	}

	/**
	 * Creates a new ResultSequence consisting of the extractable gYear in the
	 * supplied ResultSequence
	 * 
	 * @param arg
	 *            The ResultSequence from which the gYear is to be extracted
	 * @return New ResultSequence consisting of the supplied year
	 * @throws DynamicError
	 */
	public ResultSequence constructor(ResultSequence arg) throws DynamicError {
		if (arg.empty())
			return ResultBuffer.EMPTY;

		AnyAtomicType aat = (AnyAtomicType) arg.first();
		if (aat instanceof NumericType || aat instanceof XSDuration ||
			aat instanceof XSTime || isGDataType(aat) ||
			aat instanceof XSBoolean || aat instanceof XSBase64Binary ||
			aat instanceof XSHexBinary || aat instanceof XSAnyURI) {
			throw DynamicError.invalidType();
		}
		
		if (!isCastable(aat)) {
			throw DynamicError.cant_cast(null);
		}
		
		XSGYear val = castGYear(aat);

		if (val == null)
			throw DynamicError.cant_cast(null);

		return val;
	}
	
	protected boolean isGDataType(AnyAtomicType aat) {
		String type = aat.string_type();
		if (type.equals("xs:gMonthDay") ||
			type.equals("xs:gDay") ||
			type.equals("xs:gMonth") ||
			type.equals("xs:gYearMonth")) {
			return true;
		}
		return false;
	}
	
	private boolean isCastable(AnyAtomicType aat) {
		if (aat instanceof XSString || aat instanceof XSUntypedAtomic) {
			return true;
		}
		
		if (aat instanceof XSTime) {
			return false;
		}
		
		if (aat instanceof XSDate || aat instanceof XSDateTime || 
			aat instanceof XSGYear) {
			return true;
		}
		
		return false;
	}
	
	private XSGYear castGYear(AnyAtomicType aat) {
		if (aat instanceof XSGYear) {
			XSGYear gy = (XSGYear) aat;
			return new XSGYear(gy.calendar(), gy.tz());
		}
		
		if (aat instanceof XSDate) {
			XSDate date = (XSDate) aat;
			return new XSGYear(date.calendar(), date.tz());
		}
		
		if (aat instanceof XSDateTime) {
			XSDateTime dateTime = (XSDateTime) aat;
			return new XSGYear(dateTime.calendar(), dateTime.tz());
		}
		
		return parse_gYear(aat.getStringValue());
	}

	/**
	 * Retrieves the actual year as an integer
	 * 
	 * @return The actual year as an integer
	 */
	public int year() {
		int y = _calendar.get(Calendar.YEAR);
		if (_calendar.get(Calendar.ERA) == GregorianCalendar.BC)
			y *= -1;

		return y;
	}

	/**
	 * Check for whether a timezone was specified at creation
	 * 
	 * @return True if a timezone was specified. False otherwise
	 */
	public boolean timezoned() {
		return _timezoned;
	}

	/**
	 * Retrieves a String representation of the stored year
	 * 
	 * @return String representation of the stored year
	 */
	public String getStringValue() {
		String ret = "";

		ret += XSDateTime.pad_int(year(), 4);

		if (timezoned()) {
			
			int hrs = tz().hours();
			int min = tz().minutes();
			double secs = tz().seconds();
			if (hrs == 0 && min == 0 && secs == 0) {
			  ret += "Z";
			}
			else {
			  String tZoneStr = "";
			  if (tz().negative()) {
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
	 * @return "xs:gYear" which is the datatype's full pathname
	 */
	public String string_type() {
		return XS_G_YEAR;
	}

	/**
	 * Retrieves the Calendar representation of the year stored
	 * 
	 * @return Calendar representation of the year stored
	 */
	public Calendar calendar() {
		return _calendar;
	}

	/**
	 * Equality comparison between this and the supplied representation. This
	 * representation must be of type XSGYear
	 * 
	 * @param arg
	 *            The XSGYear to compare with
	 * @return True if the two representations are of the same year. False
	 *         otherwise
	 * @throws DynamicError
	 */
	public boolean eq(AnyType arg, DynamicContext dynamicContext) throws DynamicError {
		XSGYear val = (XSGYear) NumericType.get_single_type(arg, XSGYear.class);
		Calendar thiscal = normalizeCalendar(calendar(), tz());
		Calendar thatcal = normalizeCalendar(val.calendar(), val.tz());

		return thiscal.equals(thatcal);
	}
	
	/**
	 * Retrieves the timezone associated with the date stored
	 * 
	 * @return the timezone associated with the date stored
	 */
	public XSDuration tz() {
		return _tz;
	}	

	public TypeDefinition getTypeDefinition() {
		return BuiltinTypeLibrary.XS_GYEAR;
	}
}
