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
 * Error caused by static function name.
 */
public class StaticFunctNameError extends StaticNameError {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3804565876770376444L;
	public static final String FUNCTION_NOT_FOUND = "XPST0017";

	/**
	 * Constructor for static function name error
	 * 
	 * @param reason
	 *            is the reason for the error.
	 */
	public StaticFunctNameError(String reason) {
		super(FUNCTION_NOT_FOUND, reason);
	}
}
