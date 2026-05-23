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

import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.SeqType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSNCName;

/**
 * Returns an xs:NCNAME representing the local part of $arg. If $arg is the
 * empty sequence, returns the empty sequence.
 */
public class FnLocalNameFromQName extends Function {
	private static Collection _expected_args = null;

	/**
	 * Constructor for FnLocalNameFromQName.
	 */
	public FnLocalNameFromQName() {
		super(new QName("local-name-from-QName"), 1);
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
		return local_name(args);
	}

	/**
	 * Local-Name-from-QName operation.
	 * 
	 * @param args
	 *            Result from the expressions evaluation.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of fn:local-name-from-QName operation.
	 */
	public static ResultSequence local_name(Collection args)
			throws DynamicError {

		Collection cargs = Function.convert_arguments(args, expected_args());

		// get arg
		ResultSequence arg1 = (ResultSequence) cargs.iterator().next();

		if (arg1.empty())
			return ResultBuffer.EMPTY;

		QName qname = (QName) arg1.first();

		return new XSNCName(qname.local());
	}

	/**
	 * Obtain a list of expected arguments.
	 * 
	 * @return Result of operation.
	 */
	public synchronized static Collection expected_args() {
		if (_expected_args == null) {
			_expected_args = new ArrayList();
			SeqType arg = new SeqType(new QName(), SeqType.OCC_QMARK);
			_expected_args.add(arg);
		}

		return _expected_args;
	}
}
