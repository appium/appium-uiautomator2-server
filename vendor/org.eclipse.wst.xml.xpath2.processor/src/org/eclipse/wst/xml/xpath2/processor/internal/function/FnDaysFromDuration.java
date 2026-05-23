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
 *     David Carver - bug 282223 - implementation of xs:duration.
 *     David Carver (STAR) - bug 262765 - fixed expected xs:duration argument. 
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.SeqType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSDuration;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSInteger;

/**
 * Returns an xs:integer representing the days component in the canonical
 * lexical representation of the value of $arg. The result may be negative. If
 * $arg is the empty sequence, returns the empty sequence.
 */
public class FnDaysFromDuration extends Function {
	private static Collection _expected_args = null;

	/**
	 * Constructor for FnDaysFromDuration.
	 */
	public FnDaysFromDuration() {
		super(new QName("days-from-duration"), 1);
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
		return days_from_duration(args);
	}

	/**
	 * Days-From-Duration operation.
	 * 
	 * @param args
	 *            Result from the expressions evaluation.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of fn:days-from-duration operation.
	 */
	public static ResultSequence days_from_duration(Collection args)
			throws DynamicError {
		Collection cargs = Function.convert_arguments(args, expected_args());

		ResultSequence arg1 = (ResultSequence) cargs.iterator().next();

		if (arg1.empty()) {
			return ResultBuffer.EMPTY;
		}

		XSDuration dtd = (XSDuration) arg1.first();

		int res = dtd.days();

		if (dtd.negative())
			res *= -1;

		return new XSInteger(BigInteger.valueOf(res));
	}

	/**
	 * Obtain a list of expected arguments.
	 * 
	 * @return Result of operation.
	 */
	public synchronized static Collection expected_args() {
		if (_expected_args == null) {
			_expected_args = new ArrayList();
			_expected_args.add(new SeqType(new XSDuration(),
					SeqType.OCC_QMARK));
		}

		return _expected_args;
	}
}
