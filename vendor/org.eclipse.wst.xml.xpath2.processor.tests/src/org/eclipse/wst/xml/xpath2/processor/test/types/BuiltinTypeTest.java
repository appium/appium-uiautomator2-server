/*******************************************************************************
 * Copyright (c) 2011, 2018 IBM Corporation and others.
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
package org.eclipse.wst.xml.xpath2.processor.test.types;

import org.eclipse.wst.xml.xpath2.processor.internal.types.builtin.BuiltinTypeLibrary;

import junit.framework.TestCase;

public class BuiltinTypeTest extends TestCase {

	public void testCreateAtomic() {
		BuiltinTypeLibrary.XS_BOOLEAN.constructNative(Boolean.TRUE);
	}
}
