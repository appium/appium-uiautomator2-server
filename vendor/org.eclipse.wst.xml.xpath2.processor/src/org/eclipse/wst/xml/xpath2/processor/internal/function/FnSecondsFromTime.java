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
 *     David Carver - bug 277774 - XSDecimal returing wrong values. 
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.SeqType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSDecimal;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSTime;

/**
 * Returns an xs:decimal value between 0 and 60.999..., both inclusive,
 * representing the seconds and fractional seconds in the localized value of
 * $arg. Note that the value can be greater than 60 seconds to accommodate
 * occasional leap seconds used to keep human time synchronized with the
 * rotation of the planet. If $arg is the empty sequence, returns the empty
 * sequence.
 */
public class FnSecondsFromTime extends Function {
	private static Collection _expected_args = null;

	/**
	 * Constructor for FnSecondsFromTime.
	 */
	public FnSecondsFromTime() {
		super(new QName("seconds-from-time"), 1);
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
	public ResultSequence evaluate(Collection args, org.eclipse.wst.xml.xpath2.api.EvaluationContext ec) throws DynamicError {
		return seconds_from_time(args);
	}

	/**
	 * Base-Uri operation.
	 * 
	 * @param args
	 *            Result from the expressions evaluation.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of fn:base-uri operation.
	 */
	public static ResultSequence seconds_from_time(Collection args)
			throws DynamicError {
		Collection cargs = Function.convert_arguments(args, expected_args());

		ResultSequence arg1 = (ResultSequence) cargs.iterator().next();

		if (arg1.empty()) {
			return ResultBuffer.EMPTY;
		}

		XSTime dt = (XSTime) arg1.first();

		double res = dt.second();

		return new XSDecimal(new BigDecimal(res));
	}

	/**
	 * Obtain a list of expected arguments.
	 * 
	 * @return Result of operation.
	 */
	public synchronized static Collection expected_args() {
		if (_expected_args == null) {
			_expected_args = new ArrayList();
			_expected_args.add(new SeqType(new XSTime(), SeqType.OCC_QMARK));
		}

		return _expected_args;
	}
}
