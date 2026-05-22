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
 *     Jesper Moller - bug 280555 - Add pluggable collation support
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.util.Collection;

import org.eclipse.wst.xml.xpath2.api.DynamicContext;
import org.eclipse.wst.xml.xpath2.api.EvaluationContext;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;

/**
 * Class for Less than function.
 */
public class FsLt extends Function {
	/**
	 * Constructor for FsLt.
	 */
	public FsLt() {
		super(new QName("lt"), 2);
	}

	/**
	 * Evaluate arguments.
	 * 
	 * @param args
	 *            argument expressions.
	 * @return Result of evaluation.
	 */
	public ResultSequence evaluate(Collection args, EvaluationContext ec) {
		assert args.size() >= min_arity() && args.size() <= max_arity();

		return fs_lt_value(args, ec.getDynamicContext());
	}

	/**
	 * Operation on the values of the arguments.
	 * 
	 * @param args
	 *            input arguments.
	 * @param context 
	 *             Dynamic context
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of the operation.
	 */
	public static ResultSequence fs_lt_value(Collection args, DynamicContext dc) {
		return FsEq.do_cmp_value_op(args, CmpLt.class, "lt", dc);
	}

	/**
	 * General operation on the arguments.
	 * 
	 * @param args
	 *            input arguments.
	 * @param dc 
	 *             The dynamic context
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of the operation.
	 */
	public static ResultSequence fs_lt_general(Collection args, DynamicContext dc)
			throws DynamicError {
		return FsEq.do_cmp_general_op(args, FsLt.class, "fs_lt_value", dc);
	}
}
