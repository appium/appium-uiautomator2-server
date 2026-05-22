/*******************************************************************************
 * Copyright (c) 2012, 2018 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xml.xpath2.processor.internal;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.BigInteger;

import java_cup.runtime.Symbol;

import junit.framework.TestCase;

public class XPathFlexTest extends TestCase {
	
	void assertSymbolEquals(int expectedId, Object expectedValue, Symbol symbol) {
		assertEquals("Expected symbol type for " + symbol.value, expectedId, symbol.sym);
		assertEquals("Expected symbol value for symbol type", expectedValue, symbol.value);
	}
	
	Symbol tokenizeFirst(String source) throws IOException {
		XPathFlex lexer = new XPathFlex(new StringReader(source));
		return lexer.next_token();
	}

	public void testDigits() throws IOException {
		assertSymbolEquals(XpathSym.INTEGER, BigInteger.valueOf(1234), tokenizeFirst("1234"));
		assertSymbolEquals(XpathSym.DECIMAL, BigDecimal.valueOf(1234.0), tokenizeFirst("1234.0"));
	}

	public void testNCNAME() throws IOException {
		assertSymbolEquals(XpathSym.NCNAME, "beef", tokenizeFirst("beef"));
		assertSymbolEquals(XpathSym.NCNAME, "_beef", tokenizeFirst("_beef"));
	}

	public void testNonAsciiIdentifiers() throws IOException {
		assertSymbolEquals(XpathSym.NCNAME, "M\u00e8ller", tokenizeFirst("M\u00e8ller"));
		assertSymbolEquals(XpathSym.NCNAME, "\uAC20", tokenizeFirst("\uAC20"));
	}

	public void testSimpleIdentifiersInSpace() throws IOException {
		assertEquals("myElement", tokenizeFirst(" myElement ").value);
	}
	public void testUTF16_SurogatePair_valid() throws IOException {
		// SPEAK-NO-EVIL MONKEY is a valid XML name
		// Unicode: U+1F64A (U+D83D U+DE4A)
		XPathFlex lexer = new XPathFlex(new StringReader(" monkey\uD83D\uDE4Ame "));
		Symbol symbol = lexer.next_token();
		
		assertEquals("monkey\uD83D\uDE4Ame", symbol.value);
	}

	public void testUTF16_SurogatePair_invalid() throws IOException {
		// Lets get the surrogate order wrong
		try {
			XPathFlex lexer = new XPathFlex(new StringReader("\uDE4A\uD83D"));
			lexer.next_token();
			fail("Should have gotten an exception");
		}
		catch (JFlexError e) {
			// hooray!
		}			
	}

}
