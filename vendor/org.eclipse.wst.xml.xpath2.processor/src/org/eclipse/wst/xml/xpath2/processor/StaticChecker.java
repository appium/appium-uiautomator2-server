/*******************************************************************************
 * Copyright (c) 2005, 2009 Andrea Bittau, University College London, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Andrea Bittau - initial API and implementation from the PsychoPath XPath 2.0 
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor;

import org.eclipse.wst.xml.xpath2.processor.internal.ast.*;

/**
 * Interface to static checker.
 */
public interface StaticChecker {

	/**
	 * checks XPathNode
	 * 
	 * @throws static error.
	 * @param root
	 *            is an XPath node.
	 */
	public void check(XPathNode root) throws StaticError;
}
