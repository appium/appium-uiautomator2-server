/*******************************************************************************
 * Copyright (c) 2009, 2017 Jesper Steen M�ller and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Jesper Steen Moller - initial API and implementation
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.test;

import junit.framework.TestCase;

import org.eclipse.wst.xml.xpath2.processor.internal.utils.LiteralUtils;

public class LiteralUtilsTest extends TestCase {

	public void testUnquoteDouble() {
		String original = "\"Henry \"\"Billie The Kid\"\" McCarty\"";
		
		assertEquals("Henry \"Billie The Kid\" McCarty", LiteralUtils.unquote(original));
	}

	public void testDoubleFirst() {
		String original = "\"\"\"Billie\"\" is the name\"";
		
		assertEquals("\"Billie\" is the name", LiteralUtils.unquote(original));
	}

	public void testDoubleLast() {
		String original = "\"The name is \"\"Billie\"\"\"";
		
		assertEquals("The name is \"Billie\"", LiteralUtils.unquote(original));
	}

	public void testUnquoteSingle() {
		String original = "'Don''t shoot the messenger'";
		assertEquals("Don't shoot the messenger", LiteralUtils.unquote(original));
	}

	public void testSingleFirst() {
		String original = "'''s-Hertogenbosch is in The Netherlands'";
		// http://en.wikipedia.org/wiki/'s-Hertogenbosch   (it is nice there, actually)
		assertEquals("'s-Hertogenbosch is in The Netherlands", LiteralUtils.unquote(original));
	}

	public void testSingleLast() {
		String original = "'He''s so tall, over 6'''";
		assertEquals("He's so tall, over 6'", LiteralUtils.unquote(original));
	}
}
