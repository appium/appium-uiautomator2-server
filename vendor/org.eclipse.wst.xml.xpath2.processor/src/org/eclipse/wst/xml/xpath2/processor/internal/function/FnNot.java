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
 *     Jesper Steen Moeller - bug 285145 - implement full arity checking
 *     Jesper Steen Moller  - bug 262765 - propagate possible errors from xs:boolean
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.util.Collection;

import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.ResultSequenceFactory;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSBoolean;

/**
 * $arg is first reduced to an effective boolean value by applying the
 * fn:boolean() function. Returns true if the effective boolean value is false,
 * and false if the effective boolean value is true.
 */
public class FnNot extends Function {
	/**
	 * Constructor for FnNot.
	 */
	public FnNot() {
		super(new QName("not"), 1);
	}

	/**
	 * Evaluate arguments.
	 * 
	 * @param args
	 *            argument expressions.
	 * @return Result of evaluation.
	 * @throws DynamicError 
	 */
	public ResultSequence evaluate(Collection args, org.eclipse.wst.xml.xpath2.api.EvaluationContext ec) throws DynamicError {
		// 1 argument only!
		assert args.size() >= min_arity() && args.size() <= max_arity();

		ResultSequence argument = (ResultSequence) args.iterator().next();

		return fn_not(argument);
	}

	/**
	 * Not operation.
	 * 
	 * @param arg
	 *            Result from the expressions evaluation.
	 * @return Result of fn:note operation.
	 * @throws DynamicError 
	 */
	public static ResultSequence fn_not(ResultSequence arg) throws DynamicError {
		XSBoolean ret = FnBoolean.fn_boolean(arg);

		boolean answer = false;

		if (ret.value() == false)
			answer = true;

		return ResultSequenceFactory.create_new(new XSBoolean(answer));
	}

}
