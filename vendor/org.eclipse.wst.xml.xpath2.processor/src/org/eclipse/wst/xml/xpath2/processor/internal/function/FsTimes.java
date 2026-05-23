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
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.util.Collection;

import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;

/**
 * Class for Multiplication function.
 */
public class FsTimes extends Function {
	/**
	 * Constructor for FsTimes.
	 */
	public FsTimes() {
		super(new QName("times"), 2);
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

		return fs_times(args);
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
	public static ResultSequence fs_times(Collection args) throws DynamicError {
		return FsPlus.do_math_op(args, MathTimes.class, "times");
	}
}
