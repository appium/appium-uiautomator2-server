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
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor;


/**
 * Exception caused by DOM loader.
 * @deprecated Only used in deprecated APIs
 */
public class DOMLoaderException extends XPathException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7652865222211067201L;

	/**
	 * Constructor for DOM loader exception.
	 * 
	 * @param reason
	 *            is the reason for the exception.
	 */
	public DOMLoaderException(String reason) {
		super(reason);
	}
}
