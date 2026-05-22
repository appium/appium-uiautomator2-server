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
 *     David Carver - bug 262765 - corrected implementation according to spec. 
 *     Jesper Steen Moeller - bug 285145 - implement full arity checking
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.wst.xml.xpath2.api.EvaluationContext;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.SeqType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.NodeType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSAnyURI;

/**
 * Returns the namespace URI of the xs:QName of $arg.
 */
public class FnNamespaceUri extends Function {
	private static Collection _expected_args = null;

	/**
	 * Constructor for FnNamespaceUri.
	 */
	public FnNamespaceUri() {
		super(new QName("namespace-uri"), 0, 1);
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
		return namespace_uri(args, ec);
	}

	/**
	 * Namespace-Uri operation.
	 * 
	 * @param args
	 *            Result from the expressions evaluation.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of fn:namespace-uri operation.
	 */
	public static ResultSequence namespace_uri(Collection args,
			EvaluationContext context) throws DynamicError {

		Collection cargs = Function.convert_arguments(args, expected_args());

		ResultSequence arg1 = null;
		if (cargs.isEmpty()) {
			if (context.getContextItem() == null) {
				throw DynamicError.contextUndefined();
			}
			arg1 = (AnyType) context.getContextItem();
		} else {
			// get arg
			arg1 = (ResultSequence) cargs.iterator().next();
		}

		if (arg1.empty()) {
			return new XSAnyURI("");
		}

		NodeType an = (NodeType) arg1.first();

		QName name = an.node_name();

		String sname = "";
		if (name != null)
			sname = name.namespace();

		return new XSAnyURI(sname);
	}

	/**
	 * Obtain a list of expected arguments.
	 * 
	 * @return Result of operation.
	 */
	public synchronized static Collection expected_args() {
		if (_expected_args == null) {
			_expected_args = new ArrayList();
			SeqType arg = new SeqType(SeqType.OCC_QMARK);
			_expected_args.add(arg);
		}

		return _expected_args;
	}
}
