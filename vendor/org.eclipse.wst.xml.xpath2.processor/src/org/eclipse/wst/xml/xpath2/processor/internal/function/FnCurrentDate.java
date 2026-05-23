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
 *     Jesper S Moller - bug 286452 - always return the stable date/time from dynamic context
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.util.Collection;

import org.eclipse.wst.xml.xpath2.api.DynamicContext;
import org.eclipse.wst.xml.xpath2.api.EvaluationContext;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.ResultSequenceFactory;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSDate;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSDayTimeDuration;

/**
 * Returns xs:date(fn:current-dateTime()). This is a xs:date (with timezone)
 * that is current at some time during the evaluation of a query or
 * transformation in which fn:current-date() is executed. This function is
 * stable. The precise i instant during the query or transformation represented
 * by the value of fn:current-date() is implementation dependent.
 */
public class FnCurrentDate extends Function {
	/**
	 * Constructor for FnCurrentDate.
	 */
	public FnCurrentDate() {
		super(new QName("current-date"), 0);
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
		return current_date(args, ec.getDynamicContext());
	}

	/**
	 * Current-Date operation.
	 * 
	 * @param args
	 *            Result from the expressions evaluation.
	 * @param dc
	 *            Result of dynamic context operation.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of fn:current-date operation.
	 */
	public static ResultSequence current_date(Collection args, DynamicContext dc)
			throws DynamicError {
		assert args.size() == 0;

		XSDayTimeDuration tz = new XSDayTimeDuration(dc.getTimezoneOffset());
		AnyType res = new XSDate(dc.getCurrentDateTime(), tz);

		return ResultSequenceFactory.create_new(res);
	}
}
