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

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.SeqType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSAnyURI;

/**
 * Returns the namespace URI for $arg as an xs:string. If $arg is the empty
 * sequence, the empty sequence is returned. If $arg is in no namespace, the
 * zero-length string is returned.
 */
public class FnNamespaceUriFromQName extends Function {
	private static Collection _expected_args = null;

	/**
	 * Constructor for FnNamespaceUriFromQName.
	 */
	public FnNamespaceUriFromQName() {
		super(new QName("namespace-uri-from-QName"), 1);
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
		return namespace(args);
	}

	/**
	 * Namespace-uri-from-QName operation.
	 * 
	 * @param args
	 *            Result from the expressions evaluation.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of fn:namespace-uri-from-QName operation.
	 */
	public static ResultSequence namespace(Collection args) throws DynamicError {

		Collection cargs = Function.convert_arguments(args, expected_args());

		// get arg
		ResultSequence arg1 = (ResultSequence) cargs.iterator().next();

		if (arg1.empty())
			return ResultBuffer.EMPTY;

		QName qname = (QName) arg1.first();

		String ns = qname.namespace();

		if (ns == null)
			ns = "";
		return new XSAnyURI(ns);
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
