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
 *     David Carver - STAR - bug 262765 - Fixed arguments for Name function. 
 *     Jesper Steen Moeller - bug 285145 - implement full arity checking
 *     Mukul Gandhi - bug 301539 - fixed "context undefined" bug in case of zero
 *                                 arity.
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.wst.xml.xpath2.api.EvaluationContext;
import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.SeqType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.NodeType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSString;

/**
 * Returns the name of a node, as an xs:string that is either the zero-length
 * string, or has the lexical form of an xs:QName.
 */
public class FnName extends Function {
	private static Collection _expected_args = null;

	/**
	 * Constructor for FnName.
	 */
	public FnName() {
		super(new QName("name"), 0, 1);
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
		return name(args, ec);
	}

	/**
	 * Name operation.
	 * 
	 * @param args
	 *            Result from the expressions evaluation.
	 * @param context
	 *            Dynamic context.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of fn:name operation.
	 */
	public static ResultSequence name(Collection args, EvaluationContext ec) throws DynamicError {

		Collection cargs = Function.convert_arguments(args, expected_args());

		// get arg
		ResultSequence arg1 = null;
		
		if (cargs.isEmpty()) {
			if (ec.getContextItem() == null)
				throw DynamicError.contextUndefined();
			else {
				arg1 = ResultBuffer.wrap(ec.getContextItem());
			}
		} else {
			arg1 = (ResultSequence) cargs.iterator().next();
		}
		
		if (arg1.empty()) {
		   return new XSString("");
		}

		NodeType an = (NodeType) arg1.first();
		
		QName name = an.node_name();

		String sname = "";
		if (name != null)
		  sname = name.getStringValue();

		return new XSString(sname);
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
