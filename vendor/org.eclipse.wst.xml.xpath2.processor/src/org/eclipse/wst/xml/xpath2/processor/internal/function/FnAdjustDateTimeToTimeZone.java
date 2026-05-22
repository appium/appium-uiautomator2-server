/*******************************************************************************
 * Copyright (c) 2009, 2011 Standards for Technology in Automotive Retail, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     David Carver - bug 280547 - initial API and implementation. 
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.wst.xml.xpath2.api.EvaluationContext;
import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.SeqType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.CalendarType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSDateTime;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSDayTimeDuration;

/**
 * Adjusts an xs:dateTime value to a specific timezone, or to no timezone at
 * all. If <code>$timezone</code> is the empty sequence, returns an
 * <code>xs:dateTime</code> without timezone. Otherwise, returns an
 * <code>xs:dateTime</code> with a timezone.
 */
public class FnAdjustDateTimeToTimeZone extends Function {
	private static Collection _expected_args = null;
	private static final XSDayTimeDuration minDuration = new XSDayTimeDuration(
			0, 14, 0, 0, true);
	private static final XSDayTimeDuration maxDuration = new XSDayTimeDuration(
			0, 14, 0, 0, false);

	/**
	 * Constructor for FnDateTime.
	 */
	public FnAdjustDateTimeToTimeZone() {
		super(new QName("adjust-dateTime-to-timezone"), 1, 2);
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
	public ResultSequence evaluate(Collection args, EvaluationContext ec) {
		return adjustdateTime(args, ec.getDynamicContext());
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
	public static ResultSequence adjustdateTime(Collection args,
			org.eclipse.wst.xml.xpath2.api.DynamicContext dynamicContext) throws DynamicError {

		Collection cargs = Function.convert_arguments(args, expectedArgs());

		// get args
		Iterator argiter = cargs.iterator();
		ResultSequence arg1 = (ResultSequence) argiter.next();
		if (arg1.empty()) {
			return ResultBuffer.EMPTY;
		}
		ResultSequence arg2 = null;
		if (argiter.hasNext()) {
			arg2 = (ResultSequence) argiter.next();
		}
		XSDateTime dateTime = (XSDateTime) arg1.item(0);
		XSDayTimeDuration timezone = null;
		
		if (arg2 != null && arg2.empty()) {
			if (dateTime.timezoned()) {
				CalendarType localized = new XSDateTime(dateTime.calendar(), null);
				return localized;
			} else {
				return arg1;
			}
		} else if (arg2 == null) {
			CalendarType localized = new XSDateTime(dateTime.normalizeCalendar(dateTime.calendar(), dateTime.tz()), null);
			return localized;
		}

		timezone = (XSDayTimeDuration) arg2.item(0);
		if (timezone.lt(minDuration, dynamicContext) || timezone.gt(maxDuration, dynamicContext)) {
			throw DynamicError.invalidTimezone();
		}
		
		if (dateTime.tz() == null) {
			return new XSDateTime(dateTime.calendar(), timezone);
		}
		
		XMLGregorianCalendar xmlCalendar = _datatypeFactory.newXMLGregorianCalendar((GregorianCalendar)dateTime.normalizeCalendar(dateTime.calendar(), dateTime.tz()));
		
		Duration duration = _datatypeFactory.newDuration(timezone.getStringValue());
		xmlCalendar.add(duration);

		return new XSDateTime(xmlCalendar.toGregorianCalendar(), timezone);
	}

	/**
	 * Obtain a list of expected arguments.
	 * 
	 * @return Result of operation.
	 */
	public synchronized static Collection expectedArgs() {
		if (_expected_args == null) {
			_expected_args = new ArrayList();
			_expected_args
					.add(new SeqType(new XSDateTime(), SeqType.OCC_QMARK));
			_expected_args.add(new SeqType(new XSDayTimeDuration(),
					SeqType.OCC_QMARK));
		}

		return _expected_args;
	}
}
