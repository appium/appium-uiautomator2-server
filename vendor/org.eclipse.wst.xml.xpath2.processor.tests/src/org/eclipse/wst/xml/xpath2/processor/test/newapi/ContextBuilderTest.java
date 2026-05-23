/*******************************************************************************
 * Copyright (c) 2011, 2017 Jesper Steen Moller and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Jesper Steen Moller  - initial API and implementation
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.test.newapi;

import junit.framework.TestCase;

import org.eclipse.wst.xml.xpath2.processor.util.StaticContextBuilder;

public class ContextBuilderTest extends TestCase {

	
	public void testReasonableDefaults() {
		StaticContextBuilder scb = new StaticContextBuilder();
		
		assertFalse(scb.isXPath1Compatible());
		assertEquals("", scb.getDefaultNamespace());
		assertEquals(null, scb.getBaseUri());
	}
	
}
