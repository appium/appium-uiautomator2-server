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
 *     Jesper Steen Moeller - bug 282096 - clean up string storage and make
 *                                         translate function surrogate aware
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.SeqType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSString;
import org.eclipse.wst.xml.xpath2.processor.internal.utils.CodePointIterator;
import org.eclipse.wst.xml.xpath2.processor.internal.utils.StringCodePointIterator;

import com.ibm.icu.lang.UCharacter;

/**
 * <p>
 * Translation function.
 * </p>
 * 
 * <p>
 * Usage: fn:translate($arg as xs:string?, $mapString as xs:string, $transString
 * as xs:string) as xs:string
 * </p>
 * 
 * <p>
 * This class returns the value of $arg modified so that every character in the
 * value of $arg that occurs at some position N in the value of $mapString has
 * been replaced by the character that occurs at position N in the value of
 * $transString.
 * </p>
 * 
 * <p>
 * If the value of $arg is the empty sequence, the zero-length string is
 * returned.
 * </p>
 * 
 * <p>
 * Every character in the value of $arg that does not appear in the value of
 * $mapString is unchanged.
 * </p>
 * 
 * <p>
 * Every character in the value of $arg that appears at some position M in the
 * value of $mapString, where the value of $transString is less than M
 * characters in length, is omitted from the returned value. If $mapString is
 * the zero-length string $arg is returned.
 * </p>
 * 
 * <p>
 * If a character occurs more than once in $mapString, then the first occurrence
 * determines the replacement character. If $transString is longer than
 * $mapString, the excess characters are ignored.
 * </p>
 */
public class FnTranslate extends Function {
	private static Collection _expected_args = null;

	/**
	 * Constructor for FnTranslate.
	 */
	public FnTranslate() {
		super(new QName("translate"), 3);
	}

	/**
	 * Evaluate the arguments.
	 * 
	 * @param args
	 *            are evaluated.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return The evaluation of the arguments being translated.
	 */
	public ResultSequence evaluate(Collection args, org.eclipse.wst.xml.xpath2.api.EvaluationContext ec) throws DynamicError {
		return translate(args);
	}

	/**
	 * Translate arguments.
	 * 
	 * @param args
	 *            are translated.
	 * @throws DynamicError
	 *             Dynamic error.
	 * @return The result of translating the arguments.
	 */
	public static ResultSequence translate(Collection args) throws DynamicError {
		Collection cargs = Function.convert_arguments(args, expected_args());

		Iterator argi = cargs.iterator();
		ResultSequence arg1 = (ResultSequence) argi.next();
		ResultSequence arg2 = (ResultSequence) argi.next();
		ResultSequence arg3 = (ResultSequence) argi.next();

		if (arg1.empty()) {
			return new XSString("");
		}

		String str = ((XSString) arg1.first()).value();
		String mapstr = ((XSString) arg2.first()).value();
		String transstr = ((XSString) arg3.first()).value();

		Map replacements = buildReplacementMap(mapstr, transstr);
		
		StringBuffer sb = new StringBuffer(str.length());
		CodePointIterator strIter = new StringCodePointIterator(str);
		for (int input = strIter.current(); input != CodePointIterator.DONE; input = strIter.next()) {
			Integer inputCodepoint = new Integer(input);
			if (replacements.containsKey(inputCodepoint)) {
				Integer replaceWith = (Integer)replacements.get(inputCodepoint);
				if (replaceWith != null) {
					sb.append(UCharacter.toChars(replaceWith.intValue()));
				}					
			} else {
				sb.append(UCharacter.toChars(input));
			}
		}
		
		return new XSString(sb.toString());
	}

	/**
	 * Build a replacement map from the mapstr and the transstr for translation. The function returns a Map<Integer, Integer> mapping each codepoint
	 * mentioned in the mapstr into the corresponding codepoint in transstr, or null if there is no matching mapping in transstr.
	 * 
	 * @param mapstr The "mapping from" string
	 * @param transstr The "mapping into" string
	 * @return A map which maps input codepoint to output codepoint (or null)
	 */
	private static Map buildReplacementMap(String mapstr, String transstr) {
		// Build mapping (map from codepoint -> codepoint)		
		Map replacements = new HashMap(mapstr.length() * 4);

		CodePointIterator mapIter = new StringCodePointIterator(mapstr);
		CodePointIterator transIter = new StringCodePointIterator(transstr);
		// Iterate through both mapIter and transIter and produce the mapping
		int mapFrom = mapIter.current();
		int mapTo = transIter.current();
		while (mapFrom != CodePointIterator.DONE) {
			Integer codepointFrom = new Integer(mapFrom);
			if (! replacements.containsKey(codepointFrom)) {
				// only overwrite if it doesn't exist already
				Integer replacement = mapTo != CodePointIterator.DONE ? new Integer(mapTo) : null;
				replacements.put(codepointFrom, replacement);
			}
			mapFrom = mapIter.next();
			mapTo = transIter.next();	
		}
		return replacements;
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
			_expected_args.add(new SeqType(new XSString(), SeqType.OCC_NONE));
			_expected_args.add(new SeqType(new XSString(), SeqType.OCC_NONE));
		}

		return _expected_args;
	}
}
