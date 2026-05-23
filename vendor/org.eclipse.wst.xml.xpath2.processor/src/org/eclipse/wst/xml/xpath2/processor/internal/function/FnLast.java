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

import java.math.BigInteger;
import java.util.Collection;

import org.eclipse.wst.xml.xpath2.api.EvaluationContext;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.ResultSequenceFactory;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSInteger;

/**
 * Returns an xs:integer indicating the number of items in the sequence of items
 * currently being processed. If the context item is undefined, an error is
 * raised [err:FONC0001].
 */
public class FnLast extends Function {
	/**
	 * Constructor for FnLast.
	 */
	public FnLast() {
		super(new QName("last"), 0);
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
		return last(args, ec);
	}

	/**
	 * Last operation.
	 * 
	 * @param args
	 *            Result from the expressions evaluation.
	 * @param dc
	 *            Result of dynamic context operation.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of fn:last operation.
	 */
	public static ResultSequence last(Collection args, EvaluationContext ec)
			throws DynamicError {
		assert args.size() == 0;
		
		if (ec.getContextItem() == null) {
			throw DynamicError.contextUndefined();
		}

		int last = ec.getLastPosition();

		assert last != 0;

		return ResultSequenceFactory.create_new(new XSInteger(BigInteger.valueOf(last)));
	}
}
