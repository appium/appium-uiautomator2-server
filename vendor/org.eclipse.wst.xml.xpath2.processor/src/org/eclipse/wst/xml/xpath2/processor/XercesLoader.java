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
 *     Mukul Gandhi - bug 276134 - improvements to schema aware primitive type support
 *                                 for attribute/element nodes  
 *     Jesper Steen Moller - Fixed namespace awareness
 *     David Carver  - bug 281186 - implementation of fn:id and fn:idref.  Correct
 *                                  loading of grammars if non-validating.
 *     Mukul Gandhi - bug 280798 -  PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor;

import java.io.*;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.validation.Schema;

import org.xml.sax.*;

/**
 * Xerces loader class. The loading is always namespace aware.
 */
public class XercesLoader implements DOMLoader {

	private static final String NONVALIDATING_LOAD_DTD_GRAMMAR = "http://apache.org/xml/features/nonvalidating/load-dtd-grammar";

	public static final String NAMESPACES_FEATURE = "http://xml.org/sax/features/namespaces";

	public static final String VALIDATION_FEATURE = "http://xml.org/sax/features/validation";

	public static final String SCHEMA_VALIDATION_FEATURE = "http://apache.org/xml/features/validation/schema";

	public static final String SCHEMA_FULL_CHECKING_FEATURE = "http://apache.org/xml/features/validation/schema-full-checking";

	public static final String DYNAMIC_VALIDATION_FEATURE = "http://apache.org/xml/features/validation/dynamic";

	public static final String LOAD_EXTERNAL_DTD_FEATURE = "http://apache.org/xml/features/nonvalidating/load-external-dtd";

	public static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
	public static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";

	public static final String DOCUMENT_IMPLEMENTATION_PROPERTY = "http://apache.org/xml/properties/dom/document-class-name";
	public static final String DOCUMENT_PSVI_IMPLEMENTATION = "org.apache.xerces.dom.PSVIDocumentImpl";

	boolean _validating;
	
	Schema _schema = null;;

	/**
	 * Constructor for Xerces loader.
	 */
	public XercesLoader() {
		_validating = false;
	}
	
	/**
	 * @since 1.1
	 */
	public XercesLoader(Schema schema) {
		_validating = false;
		_schema = schema;
	}

	/**
	 * The Xerces loader loads the XML document
	 * 
	 * @param in
	 *            is the input stream.
	 * @throws DOMLoaderException
	 *             DOM loader exception.
	 * @return The loaded document.
	 */
	public Document load(InputStream in) throws DOMLoaderException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		factory.setNamespaceAware(true);
		factory.setAttribute(SCHEMA_VALIDATION_FEATURE,
				Boolean.valueOf(_validating));
		factory.setAttribute(LOAD_EXTERNAL_DTD_FEATURE, Boolean.TRUE);
		factory.setAttribute(NONVALIDATING_LOAD_DTD_GRAMMAR, Boolean.TRUE);
		factory.setAttribute(DOCUMENT_IMPLEMENTATION_PROPERTY,
				DOCUMENT_PSVI_IMPLEMENTATION);
		
		if (_schema != null) {
		  factory.setSchema(_schema);
		}
		else {
		  factory.setValidating(_validating);	
		}

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();

			if (_validating) {
				builder.setErrorHandler(new ErrorHandler() {
					public void fatalError(SAXParseException e)
							throws SAXException {
						throw e;
					}

					public void error(SAXParseException e)
							throws SAXParseException {
						throw e;
					}

					public void warning(SAXParseException e)
							throws SAXParseException {
						throw e; // XXX
					}
				});
			}
			return builder.parse(in);
		} catch (SAXException e) {
			//throw new DOMLoaderException("SAX exception: " + e.getMessage());
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			throw new DOMLoaderException("Parser configuration exception: "
					+ e.getMessage());
		} catch (IOException e) {
			throw new DOMLoaderException("IO exception: " + e.getMessage());
		}
		
		return null;

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
