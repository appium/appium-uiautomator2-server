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
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;

/**
 * <p>
 * Sequence reverse function.
 * </p>
 * 
 * <p>
 * Usage: fn:reverse($arg as item()*) as item()*
 * </p>
 * 
 * <p>
 * This class reverses the order of items in a sequence. If $arg is the empty
 * sequence, the empty sequence is returned.
 * </p>
 */
public class FnReverse extends Function {

	/**
	 * Constructor for FnReverse.
	 */
	public FnReverse() {
		super(new QName("reverse"), 1);
	}

	/**
	 * Evaluate the arguments.
	 * 
	 * @param args
	 *            are evaluated.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return The evaluation of the reversal of the arguments.
	 */
	public ResultSequence evaluate(Collection args, org.eclipse.wst.xml.xpath2.api.EvaluationContext ec) throws DynamicError {
		return reverse(args);
	}

	/**
	 * Reverse the arguments.
	 * 
	 * @param args
	 *            are reversed.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return The result of the reversal of the arguments.
	 */
	public static ResultSequence reverse(Collection args) throws DynamicError {

		assert args.size() == 1;

		// get args
		Iterator citer = args.iterator();
		ResultSequence arg = (ResultSequence) citer.next();

		if (arg.size() <= 1)
			return arg;

		ResultBuffer rs = new ResultBuffer();

		for (int i = arg.size()-1; i >= 0; --i)
			rs.add(arg.item(i));

		return rs.getSequence();
	}
}
