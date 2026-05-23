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
 *     Jesper Steen Moller - Doc fixes
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor;

import java.io.*;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import org.xml.sax.*;

/**
 * The DOM builder loads an DOM from an InputStream. The loading is always namespace aware.
 */
public class DOMBuilder implements DOMLoader {
	boolean _validating;
	boolean _namespace_aware;

	/**
	 * Constructor for DOM builder.
	 */
	public DOMBuilder() {
		_validating = false;
	}

	/**
	 * Loads The XML document.
	 * 
	 * @param in
	 *            is the input stream.
	 * @throws DOMLoaderException
	 *             DOM loader exception.
	 * @return The loaded document.
	 */
	// XXX: fix error reporting
	public Document load(InputStream in) throws DOMLoaderException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		factory.setNamespaceAware(true);
		factory.setValidating(_validating);

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();

			// if(_validating) {
			builder.setErrorHandler(new ErrorHandler() {
				public void fatalError(SAXParseException e) throws SAXException {
					throw e;
				}

				public void error(SAXParseException e) throws SAXParseException {
					throw e;
				}

				public void warning(SAXParseException e)
						throws SAXParseException {
					throw e; // XXX
				}
			});
			// }
			return builder.parse(in);
		} catch (SAXException e) {
			throw new DOMLoaderException("SAX exception: " + e.getMessage());
		} catch (ParserConfigurationException e) {
			throw new DOMLoaderException("Parser configuration exception: "
					+ e.getMessage());
		} catch (IOException e) {
			throw new DOMLoaderException("IO exception: " + e.getMessage());
		}
	}

	/**
	 * Set validating boolean.
	 * 
	 * @param x
	 *            is the value to set the validating boolean to.
	 */
	public void set_validating(boolean x) {
		_validating = x;
	}
}
