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
 * Static namespace name error class.
 */
public class StaticNsNameError extends StaticNameError {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6873980377966290062L;

	public StaticNsNameError(String reason) {
		super(PREFIX_NOT_FOUND, reason);
	}

	/**
	 * Constructor.
	 * 
	 * @param pref
	 *            is the unknown prefix.
	 * @return the error.
	 */
	public static StaticNsNameError unknown_prefix(String pref) {
		String error = "Unknown prefix";

		if (pref != null)
			error += ": " + pref;
		error += ".";

		return new StaticNsNameError(error);
	}
}
