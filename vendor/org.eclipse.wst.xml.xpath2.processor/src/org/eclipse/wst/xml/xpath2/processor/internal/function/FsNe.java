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
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;

/**
 * Class for Not equal function.
 */
public class FsNe extends Function {
	/**
	 * Constructor for FsNe.
	 */
	public FsNe() {
		super(new QName("ne"), 2);
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
	public ResultSequence evaluate(Collection args, DynamicContext ec) throws DynamicError {
		assert args.size() >= min_arity() && args.size() <= max_arity();

		return fs_ne_value(args, ec);
	}

	/**
	 * Operation on the values of the arguments.
	 * 
	 * @param args
	 *            input arguments.
	 * @param context 
	 *             The dynamic context
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of the operation.
	 */
	public static ResultSequence fs_ne_value(Collection args, DynamicContext context)
			throws DynamicError {
		return FnNot.fn_not(FsEq.fs_eq_value(args, context));
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
	public static ResultSequence fs_ne_general(Collection args, DynamicContext ec)
			throws DynamicError {
		return FsEq.do_cmp_general_op(args, FsNe.class, "fs_ne_value", ec);
	}

}
