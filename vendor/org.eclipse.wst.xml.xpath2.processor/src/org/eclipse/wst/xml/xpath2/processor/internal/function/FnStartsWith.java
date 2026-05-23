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
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.SeqType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSBoolean;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSString;

/**
 * Returns an xs:boolean indicating whether or not the value of $arg1 starts
 * with a sequence of collation units that provides a minimal match to the
 * collation units of $arg2 according to the collation that is used.
 */
public class FnStartsWith extends Function {
	private static Collection _expected_args = null;

	/**
	 * Constructor for FnStartsWith.
	 */
	public FnStartsWith() {
		super(new QName("starts-with"), 2);
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
		return starts_with(args);
	}

	/**
	 * Starts-with operation.
	 * 
	 * @param args
	 *            Result from the expressions evaluation.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of fn:starts-with operation.
	 */
	public static ResultSequence starts_with(Collection args)
			throws DynamicError {
		Collection cargs = Function.convert_arguments(args, expected_args());

		// get args
		Iterator argiter = cargs.iterator();
		ResultSequence arg1 = (ResultSequence) argiter.next();
		String str1 = "";
		String str2 = "";
		if (!arg1.empty())
			str1 = ((XSString) arg1.first()).value();

		ResultSequence arg2 = (ResultSequence) argiter.next();
		if (!arg2.empty())
			str2 = ((XSString) arg2.first()).value();

		int str1len = str1.length();
		int str2len = str2.length();

		if (str1len == 0 && str2len != 0) {
			return XSBoolean.FALSE;
		}
		if (str2len == 0) {
			return XSBoolean.TRUE;
		}

		return XSBoolean.valueOf(str1.startsWith(str2));
	}

	/**
	 * Obtain a list of expected arguments.
	 * 
	 * @return Result of operation.
	 */
	public synchronized static Collection expected_args() {
		if (_expected_args == null) {
			_expected_args = new ArrayList();
			SeqType arg = new SeqType(new XSString(), SeqType.OCC_QMARK);
			_expected_args.add(arg);
			_expected_args.add(arg);
		}

		return _expected_args;
	}
}
