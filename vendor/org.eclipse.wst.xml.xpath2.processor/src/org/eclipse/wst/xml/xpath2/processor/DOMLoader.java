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
 *     Jesper Steen Moller - Documented namespace awareness
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor;

import org.w3c.dom.Document;
import java.io.InputStream;

/**
 * The DOM loader loads the XML document.
 */
public interface DOMLoader {

	/**
	 * The DOM loader loads the XML docuemnt
	 * 
	 * @param in
	 *            is the input stream.
	 * @throws DOMLoaderException
	 *             DOM loader exception.
	 * @return The loaded document. The document is always loaded as namespace-aware
	 */
	public Document load(InputStream in) throws DOMLoaderException;

	/**
	 * Set validating boolean.
	 * 
	 * @param val
	 *            is the validating boolean.
	 */
	// XXX: default is false ?! [document it ?]
	public void set_validating(boolean val);

}
