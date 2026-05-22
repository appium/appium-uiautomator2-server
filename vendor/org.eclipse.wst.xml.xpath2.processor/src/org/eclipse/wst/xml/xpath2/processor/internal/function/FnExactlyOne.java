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

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;

/**
 * Returns $arg if it contains exactly one item. Otherwise, raises an error
 * [err:FORG0005]. The type of the result depends on the type of $arg.
 */
public class FnExactlyOne extends Function {
	/**
	 * Constructor for FnExactlyOne.
	 */
	public FnExactlyOne() {
		super(new QName("exactly-one"), 1);
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
		return exactly_one(args);
	}

	/**
	 * Exactly-one operation.
	 * 
	 * @param args
	 *            Result from the expressions evaluation.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of fn:exactly-one operation.
	 */
	public static ResultSequence exactly_one(Collection args)
			throws DynamicError {

		assert args.size() == 1;

		// get args
		Iterator citer = args.iterator();
		ResultSequence arg = (ResultSequence) citer.next();

		if (arg.size() != 1)
			throw DynamicError.not_one(null);

		return arg;
	}
}
