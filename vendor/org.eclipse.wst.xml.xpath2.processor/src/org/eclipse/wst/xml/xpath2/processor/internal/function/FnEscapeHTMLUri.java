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
 *     David Carver - STAR - bug 262765 - renamed to correct function name. 
 *     Jesper Steen Moeller - bug 285145 - implement full arity checking
 *     Jesper Steen Moeller - bug 285319 - fix UTF-8 escaping, and fix arity bug
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
 * Usage: fn:escape-html-uri($uri-part as xs:string?, $escape-reserved as xs:boolean)
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
 * The set of characters that are escaped depends on the setting of the boolean
 * argument $escape-reserved.
 * </p>
 * 
 * <p>
 * If $uri-part is the empty sequence, returns the zero-length string.
 * </p>
 * 
 * <p>
 * If $escape-reserved is true, all characters are escaped other than the lower
 * case letters a-z, the upper case letters A-Z, the digits 0-9, the PERCENT
 * SIGN "%" and the NUMBER SIGN "#", as well as certain other characters:
 * specifically, HYPHEN-MINUS ("-"), LOW LINE ("_"), FULL STOP ".", EXCLAMATION
 * MARK "!", TILDE "~", ASTERISK "*", APOSTROPHE "'", LEFT PARENTHESIS "(", and
 * RIGHT PARENTHESIS ")".
 * </p>
 * 
 * <p>
 * If $escape-reserved is false, additional characters are added to the list of
 * characters that are not escaped. These are the following: SEMICOLON ";",
 * SOLIDUS "/", QUESTION MARK "?", COLON ":", COMMERCIAL AT "@", AMPERSAND "&",
 * EQUALS SIGN "=", PLUS SIGN "+", DOLLAR SIGN "$", COMMA ",", LEFT SQUARE
 * BRACKET "[" and RIGHT SQUARE BRACKET "]".
 * </p>
 * 
 * <p>
 * To ensure that escaped URIs can be compared using string comparison
 * functions, this function must always generate hexadecimal values using the
 * upper-case letters A-F.
 * </p>
 * 
 * <p>
 * Generally, $escape-reserved should be set to true when escaping a string that
 * is to form a single part of a URI, and to false when escaping an entire URI
 * or URI reference.
 * </p>
 * 
 * <p>
 * Since this function does not escape the PERCENT SIGN "%" and this character
 * is not allowed in a URI, users wishing to convert character strings, such as
 * file names, that include "%" to a URI should manually escape "%" by replacing
 * it with "%25".
 * </p>
 */
public class FnEscapeHTMLUri extends AbstractURIFunction {
	/**
	 * Constructor for FnEscape-html-Uri.
	 */
	public FnEscapeHTMLUri() {
		super(new QName("escape-html-uri"), 1);
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
		return escape_uri(args, false, false);
	}
}
