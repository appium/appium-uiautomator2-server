/*******************************************************************************
 * Copyright (c) 2009, 2011 Jesper Steen M�ller and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Jesper Steen Moller - initial API and implementation
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.utils;

/**
 * String literal utilities
 * 
 *
 */
public class LiteralUtils {
	
	/**
	 * Unquotes a quoted string, changing double quotes into single quotes as well.
     * Examples (string delimited by > and <):
     *  >"A"< becomes >A< 
     *  >'B'< becomes >B< 
	 *  >"A""B"< becomes >A"B<
	 *  >"A""B"< becomes >A"B<
	 *  >'A''''B'< becomes >A''B<
	 *  >"A''''B"< becomes >A''''B<
	 * @param quotedString A quoted string possibly containing escaped quotes
	 * @return unquoted and unescaped string
	 */
	public static String unquote(String quotedString) {
		int inputLength = quotedString.length();
		char quoteChar = quotedString.charAt(0);
		if (quotedString.indexOf(quoteChar, 1) == inputLength-1) {
			// The trivial case where there's no quotes in the middle of the string.
			return quotedString.substring(1, inputLength-1);
		}
		
		StringBuffer sb = new StringBuffer();
		for (int i = 1; i < inputLength-1; ++i) {
			char ch = quotedString.charAt(i);
			sb.append(ch);
			if (ch == quoteChar) ++i; // Skip past second quote char (ensured by the lexer)
		}
		return sb.toString();
	}
}
