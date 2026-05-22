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
 *     Mukul Gandhi - bug 274471 - improvements to normalize-space function (support for arity 0)
 *     David Carver (STAR) - bug 262765 - correct implementation to correctly get context node 
 *     Jesper Steen Moeller - bug 285145 - implement full arity checking
 *     Jesper Steen Moller  - bug 281938 - handle context and empty sequences correctly
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.wst.xml.xpath2.api.EvaluationContext;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.SeqType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSString;

/**
 * <p>
 * Function to normalize whitespace.
 * </p>
 * 
 * <p>
 * Usage: fn:normalize-space($arg as xs:string?) as xs:string
 * </p>
 * 
 * <p>
 * This class returns the value of $arg with whitespace normalized by stripping
 * leading and trailing whitespace and replacing sequences of one or more than
 * one whitespace character with a single space, #x20.
 * </p>
 * 
 * <p>
 * The whitespace characters are defined as TAB (#x9), LINE FEED (#xA), CARRIAGE
 * RETURN (#xD) and SPACE (#x20). If the value of $arg is the empty sequence,
 * the class returns the zero-length string.
 * </p>
 */
public class FnNormalizeSpace extends Function {
	private static Collection _expected_args = null;

	/**
	 * Constructor for FnNormalizeSpace.
	 */
	public FnNormalizeSpace() {
		super(new QName("normalize-space"), 0, 1);
	}

	/**
	 * Evaluate the arguments.
	 * 
	 * @param args
	 *            are evaluated.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return The evaluation of the space in the arguments being normalized.
	 */
	public ResultSequence evaluate(Collection args, EvaluationContext ec) throws DynamicError {
		return normalize_space(args, ec);
	}

	/**
	 * Normalize space in the arguments.
	 * 
	 * @param args
	 *            are used to obtain space from, in order to be normalized.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return The result of normalizing the space in the arguments.
	 */
	public static ResultSequence normalize_space(Collection args, EvaluationContext ec)
			throws DynamicError {
		Collection cargs = Function.convert_arguments(args, expected_args());

		ResultSequence arg1 = null;
		
		if (cargs.isEmpty()) {
		  // support for arity = 0
		  arg1 = getResultSetForArityZero(ec);
		}
		else {
		  arg1 = (ResultSequence) cargs.iterator().next();
		}

		String str = "";
		if (! arg1.empty()) {
			str = ((XSString) arg1.first()).value();
		} 
		return new XSString(normalize(str));
	}

	/**
	 * The normalizing process.
	 * 
	 * @param str
	 *            is the string that space will be normalized in.
	 * @return The result of the normalizing operation.
	 */
	// XXX fix this
	public static String normalize(String str) {
		StringBuffer sb = new StringBuffer();

		int state = 0; // 0 begin
		// 1 middle
		// 2 end
		// 3 skipping

		for (int i = 0; i < str.length(); i++) {
			char x = str.charAt(i);

			boolean white = is_whitespace(x);

			switch (state) {
			// doing the beginning
			case 0:
				if (white)
					continue;
				else {
					sb.append(x);
					state = 1;
				}
				break;

			// doing the middle
			case 1:
				if (white) {
					state = 3;
					sb.append(' ');
				} else
					sb.append(x);
				break;

			case 3:
				if (!white) {
					state = 1;
					sb.append(x);
				}
				break;

			default:
				assert false;
			}
		}

		// now basically we can only have a whitespace at the end...
		String result = sb.toString();
		int len = result.length();

		if (len == 0)
			return result;
		if (result.charAt(len - 1) == ' ')
			return result.substring(0, len - 1);

		return result;
	}

	/**
	 * Determine whether a character is whitespace or not.
	 * 
	 * @param x
	 *            is the character this operation will take place on.
	 * @return Whether or not the character is whitespace.
	 */
	public static boolean is_whitespace(char x) {
		switch (x) {
		case ' ':
		case '\r':
		case '\t':
		case '\n':
			return true;
		default:
			return false;
		}
	}

	/**
	 * Calculate the expected arguments.
	 * 
	 * @return The expected arguments.
	 */
	public synchronized static Collection expected_args() {
		if (_expected_args == null) {
			_expected_args = new ArrayList();
			_expected_args.add(new SeqType(new XSString(), SeqType.OCC_QMARK));
		}

		return _expected_args;
	}
}
