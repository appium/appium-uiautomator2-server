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
 *     Mukul Gandhi - bug 274805 - improvements to xs:integer data type 
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.wst.xml.xpath2.api.Item;
import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSInteger;

/**
 * Returns a new sequence constructed from the value of $target with the item at
 * the position specified by the value of $position removed. If $position is
 * less than 1 or greater than the number of items in $target, $target is
 * returned. Otherwise, the value returned by the function consists of all items
 * of $target whose index is less than $position, followed by all items of
 * $target whose index is greater than $position. If $target is the empty
 * sequence, the empty sequence is returned.
 */
public class FnRemove extends Function {
	/**
	 * Constructor for FnRemove.
	 */
	public FnRemove() {
		super(new QName("remove"), 2);
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
		return remove(args);
	}

	/**
	 * Remove operation.
	 * 
	 * @param args
	 *            Result from the expressions evaluation.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of fn:remove operation.
	 */
	public static ResultSequence remove(Collection args) throws DynamicError {

		assert args.size() == 2;

		ResultBuffer rs = new ResultBuffer();

		// get args
		Iterator citer = args.iterator();
		ResultSequence target = (ResultSequence) citer.next();
		ResultSequence arg2 = (ResultSequence) citer.next();

		// sanity chex
		if (arg2.size() != 1)
			DynamicError.throw_type_error();

		Item at = arg2.first();
		if (!(at instanceof XSInteger))
			DynamicError.throw_type_error();

		int position = ((XSInteger) at).int_value().intValue();

		if (position < 1)
			return target;

		if (position > target.size())
			return target;

		if (target.empty())
			return rs.getSequence();

		int curpos = 1;

		for (Iterator i = target.iterator(); i.hasNext();) {
			at = (AnyType) i.next();

			if (curpos != position)
				rs.add(at);

			curpos++;
		}

		return rs.getSequence();
	}
}
