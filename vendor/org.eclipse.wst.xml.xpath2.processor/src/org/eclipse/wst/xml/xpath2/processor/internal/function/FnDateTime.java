/*******************************************************************************
 * Copyright (c) 2009, 2011 Mukul Gandhi, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Mukul Gandhi - bug 281822 - initial API and implementation
 *     David Carver - bug 282223 - implementation of xs:duration 
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.wst.xml.xpath2.api.EvaluationContext;
import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.api.StaticContext;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.SeqType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSDate;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSDateTime;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSDuration;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSTime;

/**
  * A special constructor function for constructing a xs:dateTime value from a xs:date
  * value and a xs:time value.
  * ref: Section 5.2 of the F&O spec, http://www.w3.org/TR/xpath-functions/.
 */
public class FnDateTime extends Function {
	private static Collection _expected_args = null;

	/**
	 * Constructor for FnDateTime.
	 */
	public FnDateTime() {
		super(new QName("dateTime"), 2);
	}

	/**
	 * Evaluate arguments.
	 * 
	 * @param args
	 *            argument expressions.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of evaluation.
	 */
	public ResultSequence evaluate(Collection args, EvaluationContext ec) throws DynamicError {
		return dateTime(args, ec.getStaticContext());
	}

	/**
	 * Evaluate the function using the arguments passed.
	 * 
	 * @param args
	 *            Result from the expressions evaluation.
	 * @param sc
	 *            Result of static context operation.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of the fn:dateTime operation.
	 */
	public static ResultSequence dateTime(Collection args, StaticContext sc)
			throws DynamicError {

		Collection cargs = Function.convert_arguments(args, expected_args());

		// get args
		Iterator argiter = cargs.iterator();
		ResultSequence arg1 = (ResultSequence) argiter.next();
		ResultSequence arg2 = (ResultSequence) argiter.next();

		// if either of the parameter is an empty sequence, the result
		// is an empty sequence
		if (arg1.empty() || arg2.empty()) {
			  return ResultBuffer.EMPTY;	
		}
		XSDate param1 = (XSDate)arg1.first();
		XSTime param2 = (XSTime)arg2.first();
		
		Calendar cal = Calendar.getInstance();
		cal.set(param1.year(), param1.month() - 1, param1.day());
		cal.set(Calendar.HOUR_OF_DAY, param2.hour());
		cal.set(Calendar.MINUTE, param2.minute());
		cal.set(Calendar.SECOND, (new Double(Math.floor(param2.second())).intValue()));
		cal.set(Calendar.MILLISECOND, 0);
		
		XSDuration dateTimeZone = param1.tz();
		XSDuration timeTimeZone = param2.tz();
		if ((dateTimeZone != null && timeTimeZone != null) &&
		     !dateTimeZone.getStringValue().equals(timeTimeZone.getStringValue())) {
		  // it's an error, if the arguments have different timezones
		  throw DynamicError.inconsistentTimeZone();
		} else if (dateTimeZone == null && timeTimeZone != null) {
           return new XSDateTime(cal, timeTimeZone);
		} else if (dateTimeZone != null && timeTimeZone == null) {
		   return new XSDateTime(cal, dateTimeZone);
		}
		else if ((dateTimeZone != null && timeTimeZone != null) &&
			     dateTimeZone.getStringValue().equals(timeTimeZone.getStringValue())) {
		   return new XSDateTime(cal, dateTimeZone);
		}
		else {
		   return new XSDateTime(cal, null);
		}
	}

	/**
	 * Obtain a list of expected arguments.
	 * 
	 * @return Result of operation.
	 */
	public synchronized static Collection expected_args() {
		if (_expected_args == null) {
			_expected_args = new ArrayList();
			_expected_args.add(new SeqType(new XSDate(), SeqType.OCC_QMARK));
			_expected_args.add(new SeqType(new XSTime(), SeqType.OCC_QMARK));
		}

		return _expected_args;
	}
}

