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
 *     Jesper Moller - bug 280555 - Add pluggable collation support
 *     David Carver (STAR) - bug 262765 - fixed promotion issue
 *     Jesper Moller - bug 281028 - fix promotion rules for fn:max
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *    Lukasz Wycisk - bug 361060 - Aggregations with nil=�true� throw exceptions.
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.wst.xml.xpath2.api.DynamicContext;
import org.eclipse.wst.xml.xpath2.api.EvaluationContext;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.ResultSequenceFactory;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyAtomicType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSDouble;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSFloat;
import org.eclipse.wst.xml.xpath2.processor.internal.utils.ComparableTypePromoter;
import org.eclipse.wst.xml.xpath2.processor.internal.utils.TypePromoter;

/**
 * Selects an item from the input sequence $arg whose value is greater than or
 * equal to the value of every other item in the input sequence. If there are
 * two or more such items, then the specific item whose value is returned is
 * implementation dependent.
 */
public class FnMax extends Function {
	/**
	 * Constructor for FnMax.
	 */
	public FnMax() {
		super(new QName("max"), 1);
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
		return max(args, ec.getDynamicContext());
	}

	/**
	 * Max operation.
	 * 
	 * @param args
	 *            Result from the expressions evaluation.
	 * @param context 
	 *            Relevant dynamic context
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of fn:max operation.
	 */
	public static ResultSequence max(Collection args, DynamicContext dynamicContext) throws DynamicError {

		ResultSequence arg = get_arg(args, CmpGt.class);
		if (arg.empty())
			return ResultSequenceFactory.create_new();

		CmpGt max = null;

		TypePromoter tp = new ComparableTypePromoter();
		tp.considerSequence(arg);

		for (Iterator i = arg.iterator(); i.hasNext();) {
			AnyAtomicType conv = tp.promote((AnyType) i.next());
			
			if( conv != null ){
				
				if (conv instanceof XSDouble && ((XSDouble)conv).nan() || conv instanceof XSFloat && ((XSFloat)conv).nan()) {
					return ResultSequenceFactory.create_new(tp.promote(new XSFloat(Float.NaN)));
				}
				if (max == null || ((CmpGt)conv).gt((AnyType)max, dynamicContext)) {
					max = (CmpGt)conv;
				}
			}
		}
		return ResultSequenceFactory.create_new((AnyType) max);
	}

	/**
	 * Obtain arguments.
	 * 
	 * @param args
	 *            input expressions.
	 * @param op
	 *            input class.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of operation.
	 */
	public static ResultSequence get_arg(Collection args, Class op)
			throws DynamicError {
		assert args.size() == 1;

		ResultSequence arg = (ResultSequence) args.iterator().next();

		return arg;
	}
}
