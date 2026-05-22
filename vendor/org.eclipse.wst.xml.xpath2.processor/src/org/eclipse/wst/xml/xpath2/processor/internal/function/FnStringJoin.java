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
import java.util.Iterator;

import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.SeqType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSString;

/**
 * <p>
 * Function to join strings together.
 * </p>
 * 
 * <p>
 * Usage: fn:string-join($arg1 as xs:string*, $arg2 as xs:string) as xs:string
 * </p>
 * 
 * <p>
 * This class returns a xs:string created by concatenating the members of the
 * $arg1 sequence using $arg2 as a separator. If the value of $arg2 is the
 * zero-length string, then the members of $arg1 are concatenated without a
 * separator.
 * </p>
 * 
 * <p>
 * If the value of $arg1 is the empty sequence, the zero-length string is
 * returned.
 * </p>
 */
public class FnStringJoin extends Function {
	private static Collection _expected_args = null;

	/**
	 * Constructor for FnStringJoin
	 */
	public FnStringJoin() {
		super(new QName("string-join"), 2);
	}

	/**
	 * Evaluate the arguments.
	 * 
	 * @param args
	 *            are evaluated.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return The evaluation of the joining of the arguments.
	 */
	public ResultSequence evaluate(Collection args, org.eclipse.wst.xml.xpath2.api.EvaluationContext ec) throws DynamicError {
		return string_join(args);
	}

	/**
	 * Join the arguments.
	 * 
	 * @param args
	 *            are joined.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return The result of the arguments being joined together.
	 */
	public static ResultSequence string_join(Collection args)
			throws DynamicError {
		Collection cargs = Function.convert_arguments(args, expected_args());

		Iterator argi = cargs.iterator();
		ResultSequence arg1 = (ResultSequence) argi.next();
		ResultSequence arg2 = (ResultSequence) argi.next();

		String result = "";
		String separator = ((XSString) arg2.first()).value();

		StringBuffer buf = new StringBuffer();
		for (Iterator i = arg1.iterator(); i.hasNext();) {
			XSString item = (XSString) i.next();
			buf.append(item.value());
			
			if (i.hasNext())
				buf.append(separator);
		}
		
		result = buf.toString();
		return new XSString(result);
	}

	/**
	 * Calculate the expected arguments.
	 * 
	 * @return The expected arguments.
	 */
	public synchronized static Collection expected_args() {
		if (_expected_args == null) {
			_expected_args = new ArrayList();
			_expected_args.add(new SeqType(new XSString(), SeqType.OCC_STAR));
			_expected_args.add(new SeqType(new XSString(), SeqType.OCC_NONE));
		}

		return _expected_args;
	}
}
