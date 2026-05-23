/*******************************************************************************
 * Copyright (c) 2009, 2011 Standards for Technology in Automotive Retail and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     David Carver - STAR - bug 262765 - renamed to correct function name. 
 *     Jesper Steen Moeller - bug 285145 - implement full arity checking
 *     Jesper Steen Moeller - bug 285319 - fix UTF-8 escaping
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.SeqType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSString;

public abstract class AbstractURIFunction extends Function {

	private static final Charset UTF_8 = Charset.forName("UTF-8");
	
	static Collection _expected_args = null;

	protected static boolean needs_escape(byte x, boolean escape_delimiters, boolean escape_space) {
		
		// These are identified as "unreserved" by [RFC 3986]: 
		if ('A' <= x && x <= 'Z')
			return false;
		if ('a' <= x && x <= 'z')
			return false;
		if ('0' <= x && x <= '9')
			return false;
		
		switch (x) {
		// These are identified as "unreserved" by [RFC 3986]: 
		case '-':
		case '_':
		case '.':
		case '~':
			return false;
	
		// These are URI/IRI delimiters 	
		case '(':
		case ')':
		case '\'':
		case '*':
		case '!':
		case '#':
		case '%':
		case ';':
		case '/':
		case '?':
		case ':':
		case '@':
		case '&':
		case '=':
		case '+':
		case '$':
		case ',':
		case '[':
		case ']':
			return escape_delimiters;

		case ' ':
			return escape_space;

			// The rest should always be escaped: < > " space | ^ - and all the UTF-8 bytes
		default:
			return true;
		}
	}

	/**
	 * Apply the URI escaping rules to the arguments.
	 * 
	 * @param args
	 *            have the URI escaping rules applied to them.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return The result of applying the URI escaping rules to the arguments.
	 */
	public static ResultSequence escape_uri(Collection args, boolean escape) throws DynamicError {
		return escape_uri(args, escape, true);
	}

	/**
	 * Apply the URI escaping rules to the arguments.
	 * 
	 * @param args
	 *            have the URI escaping rules applied to them.
	 * @param escape_space TODO
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return The result of applying the URI escaping rules to the arguments.
	 */
	public static ResultSequence escape_uri(Collection args, boolean escape_delimiters, boolean escape_space) throws DynamicError {
		Collection cargs = Function.convert_arguments(args, expected_args());
	
		Iterator argi = cargs.iterator();
		ResultSequence arg1 = (ResultSequence) argi.next();
	
		if (arg1.empty()) {
			return new XSString("");
		}
				
		AnyType aat = (AnyType) arg1.item(0);
		String str = aat.getStringValue();

		ByteBuffer buffer = UTF_8.encode(str);
		StringBuffer sb = new StringBuffer();
	
		for (int i = 0; i < buffer.limit(); i++) {
			byte x = buffer.get(i);
	
			if (needs_escape(x, escape_delimiters, escape_space)) {
				sb.append("%");
				sb.append(Integer.toHexString(x & 0xFF).toUpperCase());
			} else
				sb.append((char)x);
		}
	
		return new XSString(sb.toString());
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

	public AbstractURIFunction(QName name, int arity) {
		super(name, arity);
	}

	public AbstractURIFunction(QName name, int min_arity, int max_arity) {
		super(name, min_arity, max_arity);
	}

}
