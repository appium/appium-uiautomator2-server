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
 *     Jesper Steen Moller  - bug 290337 - Revisit use of ICU
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor;

/**
 * This exception is thrown when there is a problem with an XPath exception.
 */
public class XPathException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1380394170163983863L;
	private String _reason;

	/**
	 * Constructor for XPathException
	 * 
	 * @param reason
	 *            Is the reason why the exception has been thrown.
	 */
	public XPathException(String reason) {
		_reason = reason;
	}

	/**
	 * The reason why the exception has been thrown.
	 * 
	 * @return the reason why the exception has been throw.
	 */
	public String reason() {
		return _reason;
	}
	
	public String getMessage() {
		return _reason;
	}
}
