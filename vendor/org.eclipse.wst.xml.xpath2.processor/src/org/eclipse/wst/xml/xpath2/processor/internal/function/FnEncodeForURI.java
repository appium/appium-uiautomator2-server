/*******************************************************************************
 * Copyright (c) 2009, 2011 David Carver, University College London, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     David Carver - STAR - bug 285321 - initial api and implementation 
 *     Jesper Steen Moeller - bug 285319 - fix UTF-8 escaping
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.util.Collection;

import org.eclipse.wst.xml.xpath2.api.EvaluationContext;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;

/**
 * <p>
 * Function to apply URI escaping rules.
 * </p>
 * 
 * <p>
 * Usage: fn:encode-for-uri($uri-part as xs:string?)
 * as xs:string
 * </p>
 * 
 * <p>
 * This class applies the URI escaping rules (with one exception), to the string
 * supplied as $uri-part, which typically represents all or part of a URI. The
 * effect of the function is to escape a set of identified characters in the
 * string. Each such character is replaced in the string by an escape sequence,
 * which is formed by encoding the character as a sequence of octets in UTF-8,
 * and then representing each of these octets in the form %HH, where HH is the
 * hexadecimal representation of the octet.
 * </p>
 * 
 * <p>
 * If $uri-part is the empty sequence, returns the zero-length string.
 * </p>
 * 
 * 
 * <p>
 * To ensure that escaped URIs can be compared using string comparison
 * functions, this function must always generate hexadecimal values using the
 * upper-case letters A-F.
 * </p>
 */
public class FnEncodeForURI extends AbstractURIFunction {
	/**
	 * Constructor for FnEscape-for-Uri.
	 */
	public FnEncodeForURI() {
		super(new QName("encode-for-uri"), 1, 1);
	}

	/**
	 * Evaluate the arguments.
	 * 
	 * @param args
	 *            are evaluated.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return The evaluation of the arguments after application of the URI
	 *         escaping rules.
	 */
	public ResultSequence evaluate(Collection args, EvaluationContext ec) throws DynamicError {
		return escape_uri(args, true, true);
	}
	
}
