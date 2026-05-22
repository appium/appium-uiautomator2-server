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
 *     Mukul Gandhi - bug 280554 - improvements to the function implementation
 *     David Carver - bug 282096 - improvements for surrogate handling  
 *     Jesper Steen Moeller - bug 282096 - clean up string storage and fix surrogate handling
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.SeqType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSInteger;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSString;
import org.eclipse.wst.xml.xpath2.processor.internal.utils.CodePointIterator;
import org.eclipse.wst.xml.xpath2.processor.internal.utils.StringCodePointIterator;

/**
 * Returns the sequence of code points that constitute an xs:string. If $arg is
 * a zero-length string or the empty sequence, the empty sequence is returned.
 */
public class FnStringToCodepoints extends Function {
	private static Collection _expected_args = null;

	/**
	 * Constructor for FnStringToCodepoints.
	 */
	public FnStringToCodepoints() {
		super(new QName("string-to-codepoints"), 1);
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
		return string_to_codepoints(args);
	}

	/**
	 * Base-Uri operation.
	 * 
	 * @param args
	 *            Result from the expressions evaluation.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of fn:base-uri operation.
	 */
	public static ResultSequence string_to_codepoints(Collection args)
			throws DynamicError {
		Collection cargs = Function.convert_arguments(args, expected_args());


		ResultSequence arg1 = (ResultSequence) cargs.iterator().next();
		if (arg1.empty())
		   return ResultBuffer.EMPTY;

		XSString xstr = (XSString) arg1.first();

		CodePointIterator cpi = new StringCodePointIterator(xstr.value());
		
		ResultBuffer rs = new ResultBuffer();
		for (int codePoint = cpi.current(); codePoint != CodePointIterator.DONE; codePoint = cpi.next()) {
           	rs.add(new XSInteger(BigInteger.valueOf(codePoint)));
		}
		return rs.getSequence();
	}

	/**
	 * Obtain a list of expected arguments.
	 * 
	 * @return Result of operation.
	 */
	public synchronized static Collection expected_args() {
		if (_expected_args == null) {
			_expected_args = new ArrayList();
			_expected_args.add(new SeqType(new XSString(), SeqType.OCC_QMARK));
		}

		return _expected_args;
	}
}
