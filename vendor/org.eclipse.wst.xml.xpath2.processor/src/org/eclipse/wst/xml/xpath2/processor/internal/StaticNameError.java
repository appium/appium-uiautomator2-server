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

import org.eclipse.wst.xml.xpath2.processor.StaticError;

/**
 * Error caused by static name.
 */
public class StaticNameError extends StaticError {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4363370082563106074L;
	public static final String NAME_NOT_FOUND = "XPST0008";
	public static final String PREFIX_NOT_FOUND = "XPST0081";

	/**
	 * Constructor for static name error
	 * 
	 * @param code
	 *            is the code.
	 * @param reason
	 *            is the reason for the error.
	 */
	public StaticNameError(String code, String reason) {
		super(code, reason);
	}

	/**
	 * Constructor for static name error
	 * 
	 * @param reason
	 *            is the reason for the error.
	 */
	public StaticNameError(String reason) {
		this(NAME_NOT_FOUND, reason);
	}

}
