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
 *    Andrea Bittau - initial API and implementation from the PsychoPath XPath 2.0 
 *    Jesper Moller - bug 280555 - Add pluggable collation support
 *    David Carver (STAR) - bug 262765 - fixed promotion issue 
 *    Jesper Moller - bug 281028 - fix promotion rules for fn:min
 *    Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
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
 * selects an item from the input sequence $arg whose value is less than or
 * equal to the value of every other item in the input sequence. If there are
 * two or more such items, then the specific item whose value is returned is
 * implementation independent.
 */
public class FnMin extends Function {
	/**
	 * Constructor for FnMin.
	 */
	public FnMin() {
		super(new QName("min"), 1);
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
		return min(args, ec.getDynamicContext());
	}

	/**
	 * Min operation.
	 * 
	 * @param args
	 *            Result from the expressions evaluation.
	 * @param dynamic 
	 *            Dynamic context
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of fn:min operation.
	 */
	public static ResultSequence min(Collection args, DynamicContext context) throws DynamicError {

		ResultSequence arg = FnMax.get_arg(args, CmpLt.class);
		if (arg.empty())
			return ResultSequenceFactory.create_new();

		CmpLt max = null;

		TypePromoter tp = new ComparableTypePromoter();
		tp.considerSequence(arg);

		for (Iterator i = arg.iterator(); i.hasNext();) {
			AnyAtomicType conv = tp.promote((AnyType) i.next());
			
			if( conv != null ){
				
				if (conv instanceof XSDouble && ((XSDouble)conv).nan() || conv instanceof XSFloat && ((XSFloat)conv).nan()) {
					return ResultSequenceFactory.create_new(tp.promote(new XSFloat(Float.NaN)));
				}
				if (max == null || ((CmpLt)conv).lt((AnyType)max, context)) {
					max = (CmpLt)conv;
				}
			}
		}
		return ResultSequenceFactory.create_new((AnyType) max);
	}

}
