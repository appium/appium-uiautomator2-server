/*******************************************************************************
 * Copyright (c) 2005, 2013 Andrea Bittau, University College London, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Andrea Bittau - initial API and implementation from the PsychoPath XPath 2.0
 *     David Carver (STAR) - bug 273763 - correct error codes 
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor;


/**
 * This exception is thrown if there is a problem with the XPath parser.
 */
public class XPathParserException extends StaticError {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4974805230489762419L;
	/**
	 * The type of exception.
	 */
	public static final String INVALID_XPATH_EXPRESSION = "XPST0003";

	/**
	 * Constructor for XPathParserException.
	 * 
	 * @param reason
	 *            is the reason why the exception has been thrown.
	 */
	public XPathParserException(String reason) {
		super(INVALID_XPATH_EXPRESSION, reason);
	}

	/**
	 * Constructor for XPathParserException.
	 * 
	 * @param code
	 *            the XPath2 standard code for the problem.
	 * @param reason
	 *            is the reason why the exception has been thrown.
	 */
	public XPathParserException(String code, String reason) {
		super(code, reason);
	}
}
