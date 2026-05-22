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

package org.eclipse.wst.xml.xpath2.processor.internal;

/**
 * This error is thrown when there is a problem with an XPath exception.
 */
public class XPathError extends Error {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6624631792087303209L;
	private String _reason;

	/**
	 * Constructor for XPathError
	 * 
	 * @param reason
	 *            Is the reason why the error has been thrown.
	 */
	public XPathError(String reason) {
		_reason = reason;
	}

	/**
	 * The reason why the error has been thrown.
	 * 
	 * @return the reason why the error has been throw.
	 */
	public String reason() {
		return _reason;
	}
}
