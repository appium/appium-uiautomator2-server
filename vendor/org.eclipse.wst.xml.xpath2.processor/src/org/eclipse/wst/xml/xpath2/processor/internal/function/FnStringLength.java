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
 *     Mukul Gandhi - bug 274471 - improvements to string-length function (support for arity 0)
 *     Mukul Gandhi - bug 274805 - improvements to xs:integer data type  
 *     Jesper Steen Moeller - bug 285145 - implement full arity checking
 *     David Carver - bug 282096 - improvements for surrogate handling 
 *     Jesper Steen Moeller - bug 282096 - clean up string storage
 *     Jesper Steen Moller  - bug 281938 - handle context and empty sequences correctly
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.wst.xml.xpath2.api.EvaluationContext;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.SeqType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSInteger;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSString;

import com.ibm.icu.text.UTF16;

/**
 * <p>
 * Function to calculate string length.
 * </p>
 * 
 * <p>
 * Usage: fn:string-length($arg as xs:string?) as xs:integer
 * </p>
 * 
 * <p>
 * This class returns an xs:integer equal to the length in characters of the
 * value of $arg.
 * </p>
 * 
 * <p>
 * If the value of $arg is the empty sequence, the xs:integer 0 is returned.
 * </p>
 */
public class FnStringLength extends Function {
	private static Collection _expected_args = null;

	/**
	 * Constructor for FnStringLength
	 */
	public FnStringLength() {
		super(new QName("string-length"), 0, 1);
	}

	/**
	 * Evaluate the arguments.
	 * 
	 * @param args
	 *            are evaluated.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return The evaluation of the string length of the arguments.
	 */
	public ResultSequence evaluate(Collection args, EvaluationContext ec) throws DynamicError {
		return string_length(args, ec);
	}

	/**
	 * Obtain the string length of the arguments.
	 * 
	 * @param args
	 *            are used to obtain the string length.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return The result of obtaining the string length from the arguments.
	 */
	public static ResultSequence string_length(Collection args, EvaluationContext ec)
			throws DynamicError {
		Collection cargs = Function.convert_arguments(args, expected_args());

		ResultSequence arg1 = null;

		if (cargs.isEmpty()) {
		  // support for arity = 0
		  return getResultSetForArityZero(ec);
		}
		else {
		  arg1 = (ResultSequence) cargs.iterator().next();
		}

		String str = "";
		if (! arg1.empty()) {
			str = ((XSString) arg1.first()).value();
		}
		return new XSInteger(BigInteger.valueOf(UTF16.countCodePoint(str)));
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
