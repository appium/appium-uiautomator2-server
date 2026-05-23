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
import java.util.Iterator;

import org.eclipse.wst.xml.xpath2.api.Item;
import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSString;

/**
 * <p>
 * Sequence concatenation function.
 * </p>
 * 
 * <p>
 * Usage: fn:concat($arg1 as xdt:anyAtomicType?, $arg2 as xdt:anyAtomicType?,
 * ... ) as xs:string
 * </p>
 * 
 * <p>
 * This class accepts two or more xdt:anyAtomicType arguments and converts them
 * to xs:string. It then returns the xs:string that is the concatenation of the
 * values of its arguments after conversion. If any of the arguments is the
 * empty sequence, the argument is treated as the zero-length string.
 * </p>
 * 
 * <p>
 * The concat() function is specified to allow an arbitrary number of arguments
 * that are concatenated together.
 * </p>
 */
public class FnConcat extends Function {

	/**
	 * Constructor for FnConcat.
	 */
	public FnConcat() {
		super(new QName("concat"), 2, Integer.MAX_VALUE);
	}

	/**
	 * Evaluate the arguments.
	 * 
	 * @param args
	 *            is evaluated.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return The evaluation of the concatenation of the arguments.
	 */
	public ResultSequence evaluate(Collection args, org.eclipse.wst.xml.xpath2.api.EvaluationContext ec) throws DynamicError {
		return concat(args);
	}

	/**
	 * Concatenate the arguments.
	 * 
	 * @param args
	 *            are concatenated.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return The result of the concatenation of the arguments.
	 */
	public static ResultSequence concat(Collection args) throws DynamicError {

		// sanity check
		if (args.size() < 2)
			DynamicError.throw_type_error();

		ResultBuffer rs = new ResultBuffer();

		String result = "";

		// go through args
		StringBuffer buf = new StringBuffer();
		for (Iterator argi = args.iterator(); argi.hasNext();) {
			ResultSequence arg = (ResultSequence) argi.next();

			int size = arg.size();

			// sanity check
			if (size > 1)
				DynamicError.throw_type_error();

			if (size == 0) {
				continue;
			}

			Item at = arg.first();
			
			buf.append(at.getStringValue());

		}
		result = buf.toString();
		
		rs.add(new XSString(result));

		return rs.getSequence();
	}
}
