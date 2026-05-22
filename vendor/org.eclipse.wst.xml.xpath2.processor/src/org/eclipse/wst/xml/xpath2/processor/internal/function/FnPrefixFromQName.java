/*******************************************************************************
 * Copyright (c) 2009, 2011 Mukul Gandhi, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Mukul Gandhi - initial API and implementation
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.XMLConstants;

import org.eclipse.wst.xml.xpath2.api.EvaluationContext;
import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.api.StaticContext;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.SeqType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSNCName;

/**
 * Returns an xs:NCName representing the prefix for $arg. If $arg is the empty
 * sequence, or $arg has no prefix, an empty sequence is returned.
 */
public class FnPrefixFromQName extends Function {
	private static Collection _expected_args = null;

	/**
	 * Constructor for FnPrefixFromQName
	 */
	public FnPrefixFromQName() {
		super(new QName("prefix-from-QName"), 1);
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
		return prefix(args, ec.getStaticContext());
	}

	/**
	 * Prefix-from-QName operation.
	 * 
	 * @param args
	 *            Result from the expressions evaluation.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of fn:prefix-from-QName operation.
	 */
	public static ResultSequence prefix(Collection args, StaticContext sc) throws DynamicError {

		Collection cargs = Function.convert_arguments(args, expected_args());

		// get arg
		ResultSequence arg1 = (ResultSequence) cargs.iterator().next();

		if (arg1.empty())
		  return ResultBuffer.EMPTY;

		QName qname = (QName) arg1.first();

		String prefix = qname.prefix();
		
		if (prefix != null) {
			if (! XMLConstants.NULL_NS_URI.equals(sc.getNamespaceContext().getNamespaceURI(prefix))) {
				  return new XSNCName(prefix);
			} else {
				throw DynamicError.invalidPrefix();
			}
		} 
		return ResultBuffer.EMPTY;
	}
	
	/**
	 * Obtain a list of expected arguments.
	 * 
	 * @return Result of operation.
	 */
	public synchronized static Collection expected_args() {
		if (_expected_args == null) {
			_expected_args = new ArrayList();
			SeqType arg = new SeqType(new QName(), SeqType.OCC_QMARK);
			_expected_args.add(arg);
		}

		return _expected_args;
	}
}
