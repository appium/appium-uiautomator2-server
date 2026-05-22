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
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *     Jesper Steen Moller  - bug 340933 - Migrate to new XPath2 API
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.util.Collection;

import org.eclipse.wst.xml.xpath2.api.Item;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.types.NumericType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;

/**
 * Class for Minus function.
 */
public class FsMinus extends Function {
	/**
	 * Constructor for FsMinus.
	 */
	public FsMinus() {
		super(new QName("minus"), 2);
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
		assert args.size() >= min_arity() && args.size() <= max_arity();

		return fs_minus(args);
	}

	/**
	 * General operation on the arguments.
	 * 
	 * @param args
	 *            input arguments.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of the operation.
	 */
	public static ResultSequence fs_minus(Collection args) throws DynamicError {
		return FsPlus.do_math_op(args, MathMinus.class, "minus");
	}

	/**
	 * Unary operation on the values of the arguments.
	 * 
	 * @param args
	 *            input arguments.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of the operation.
	 */
	public static ResultSequence fs_minus_unary(Collection args)
			throws DynamicError {
		// make sure we got only one arg
		if (args.size() != 1)
			DynamicError.throw_type_error();
		ResultSequence arg = (org.eclipse.wst.xml.xpath2.api.ResultSequence) args.iterator().next();

		// make sure we got only one numeric atom
		if (arg.size() != 1)
			DynamicError.throw_type_error();
		Item at = arg.first();
		if (!(at instanceof NumericType))
			DynamicError.throw_type_error();

		NumericType nt = (NumericType) at;

		return nt.unary_minus();
	}
}
