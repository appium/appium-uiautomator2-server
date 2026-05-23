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


/**
 * Base class for all static errors as defined by the XPath 2.0 specification
 * 
 */
public class StaticError extends XPathException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7870866130837870971L;
	// errorcode specified in http://www.w3.org/2004/10/xqt-errors i fink
	private String _code;

	/**
	 * Constructor for a generic static error
	 * 
	 * @param code
	 *            The error code as specified in XPath 2.0
	 * @param err
	 *            Humar readable error message
	 */
	public StaticError(String code, String err) {
		super(err);
		_code = code;
	}

	/**
	 * @return error code which represents the error
	 */
	public String code() {
		return _code;
	}
}
