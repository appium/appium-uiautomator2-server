/*******************************************************************************
 * Copyright (c) 2005, 2012 Andrea Bittau, University College London, and others
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
 *     Lukasz Wycisk - bug 261059 - FnRoundHalfToEven is wrong in case of 2 arguments
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.TypeError;
import org.eclipse.wst.xml.xpath2.processor.internal.types.NumericType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;

/**
 * The value returned is the nearest (that is, numerically closest) numeric to
 * $arg that is a multiple of ten to the power of minus $precision. If two such
 * values are equally near (e.g. if the fractional part in $arg is exactly
 * .500...), returns the one whose least significant digit is even. If type of
 * $arg is one of the four numeric types xs:float, xs:double, xs:decimal or
 * xs:integer the type of the return is the same as the type of $arg. If the
 * type of $arg is a type derived from one of the numeric types, the type of the
 * return is the base numeric type.
 */
public class FnRoundHalfToEven extends Function {
	/**
	 * Constructor for FnRoundHalfToEven.
	 */
	public FnRoundHalfToEven() {
		super(new QName("round-half-to-even"), 1, 2);
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
		ResultSequence argument = (ResultSequence) args.iterator().next();
		if (args.size() == 2) {
			return fn_round_half_to_even(args);
		}
		
		return fn_round_half_to_even(argument);
	}

	/**
	 * Round-Half-to-Even operation.
	 * 
	 * @param arg
	 *            Result from the expressions evaluation.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of fn:round-half-to-even operation.
	 */
	public static ResultSequence fn_round_half_to_even(ResultSequence arg)
			throws DynamicError {

		NumericType nt = FnAbs.get_single_numeric_arg(arg);

		// empty arg
		if (nt == null)
			return ResultBuffer.EMPTY;
		
		return nt.round_half_to_even();
	}
	
	public static ResultSequence fn_round_half_to_even(Collection args) throws DynamicError {
		
		if (args.size() > 2 || args.size() <= 1) {
			throw new DynamicError(TypeError.invalid_type(null));
		}
		
		Iterator argIt = args.iterator();
		ResultSequence rsArg1 =  (ResultSequence) argIt.next();
		ResultSequence rsPrecision = (ResultSequence) argIt.next();
		
		NumericType nt = FnAbs.get_single_numeric_arg(rsArg1);

		// empty arg
		if (nt == null)
			return ResultBuffer.EMPTY;
		
		NumericType ntPrecision = (NumericType) rsPrecision.first();
		
		return nt.round_half_to_even(Integer.parseInt(ntPrecision.getStringValue()));
	}
}
