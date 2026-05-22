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
 *     Jesper Steen Mooller - bug 280555 - Add pluggable collation support
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.util.Collection;

import org.eclipse.wst.xml.xpath2.api.DynamicContext;
import org.eclipse.wst.xml.xpath2.api.EvaluationContext;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.ResultSequenceFactory;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSBoolean;

/**
 * Class for the Greater than or equal to function.
 */
public class FsGe extends Function {
	/**
	 * Constructor for FsGe.
	 */
	public FsGe() {
		super(new QName("ge"), 2);
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
		assert args.size() >= min_arity() && args.size() <= max_arity();

		return fs_ge_value(args, ec.getDynamicContext());
	}

	/**
	 * Greater than or equal to operation on the values of the arguments.
	 * 
	 * @param args
	 *            input arguments.
	 * @param dc 
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of the operation.
	 */
	public static ResultSequence fs_ge_value(Collection args, DynamicContext dc)
			throws DynamicError {
		ResultSequence greater = FsGt.fs_gt_value(args, dc);

		if (((XSBoolean) greater.first()).value())
			return greater;

		ResultSequence equal = FsEq.fs_eq_value(args, dc);

		if (((XSBoolean) equal.first()).value())
			return equal;

		return ResultSequenceFactory.create_new(new XSBoolean(false));
	}

	/**
	 * General greater than or equal to operation.
	 * 
	 * @param args
	 *            input arguments.
	 * @param dc 
	 *             The dynamic context
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of the operation.
	 */
	public static ResultSequence fs_ge_general(Collection args, DynamicContext dc)
			throws DynamicError {
		return FsEq.do_cmp_general_op(args, FsGe.class, "fs_ge_value", dc);
	}
}
