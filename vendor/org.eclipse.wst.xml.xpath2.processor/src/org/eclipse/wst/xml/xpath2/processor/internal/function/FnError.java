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
 *     Jesper Steen Moeller - bug 28149 - add remaining fn:error functionality
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.SeqType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSString;

/**
 * The fn:error function causes the evaluation of the outermost XQuery or
 * transformation to stop. While this function never returns a value, an error,
 * if it occurs, is returned to the external processing environment as an
 * xs:anyURI or an xs:QName. The error xs:anyURI is derived from the error
 * xs:QName. An error xs:QName with namespace URI NS and local part LP will be
 * returned as the xs:anyURI NS#LP. The method by which the xs:anyURI or
 * xs:QName is returned to the external processing environment is implementation
 * dependent.
 */
public class FnError extends Function {

	private static ArrayList _expected_args;
	private static ArrayList _expected_args1;

	// XXX overloaded...
	/**
	 * Constructor for FnError.
	 */
	public FnError() {
		super(new QName("error"), 0, 3);
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
		// Differentiate depending on whether there is one (required) argument or whatever.
		Collection cargs = Function.convert_arguments(args, args.size() == 1 ? expected_args1() : expected_args());

		QName code = null;
		ResultSequence items = null;
		String description = null;
		
		// Iterate over the args
		Iterator it = cargs.iterator();
		if (it.hasNext()) {
			ResultSequence rsQName = (ResultSequence)it.next();
			// for arity 2 and 3, the code is not mandatory, as in fn:code((), "description). Handle this:
			if (! rsQName.empty()) code = (QName)rsQName.first();
		}
		// Next arg (if present) is the description
		if (it.hasNext()) {
			ResultSequence rsDescription = (ResultSequence)it.next();
			description = ((XSString)rsDescription.first()).value();
		}
		// Final arg (if present) is the list of items
		if (it.hasNext()) {
			items = (ResultSequence)it.next();
		}
	
		// Handle the code if missing
		if (code == null) code = new QName("err", "FOER0000", "http://www.w3.org/2005/xqt-errors");
		
		return error(code, description, items);
	}

	/**
	 * Error operation.
	 * 
	 * @param args
	 *            Result from the expressions evaluation.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return Result of fn:error operation.
	 */
	public static ResultSequence error(QName error, String description, ResultSequence items) throws DynamicError {

		throw DynamicError.user_error(error.namespace(), error.local(), description);
	}

	/**
	 * Obtain a list of expected arguments.
	 * 
	 * @return Result of operation.
	 */
	public synchronized static Collection expected_args() {
		if (_expected_args == null) {
			_expected_args = new ArrayList();
			_expected_args.add(new SeqType(new QName(), SeqType.OCC_QMARK));
			_expected_args.add(new SeqType(new XSString(), SeqType.OCC_NONE));
			_expected_args.add(new SeqType(AnyType.class, SeqType.OCC_STAR));
		}

		return _expected_args;
	}

	/**
	 * Obtain a list of expected arguments.
	 * 
	 * @return Result of operation.
	 */
	public synchronized static Collection expected_args1() {
		if (_expected_args1 == null) {
			_expected_args1 = new ArrayList();
			_expected_args1.add(new SeqType(new QName(), SeqType.OCC_NONE));
		}

		return _expected_args1;
	}
}
