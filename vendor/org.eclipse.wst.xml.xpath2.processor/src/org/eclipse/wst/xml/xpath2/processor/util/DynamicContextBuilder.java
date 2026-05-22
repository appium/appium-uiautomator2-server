/*******************************************************************************
 * Copyright (c) 2011, Jesper Steen Moller, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Jesper Steen Moller - initial API and implementation
 *     Jesper Steen Moller  - bug 340933 - Migrate to new XPath2 API
 *     Jesper Steen Moller - bug 343804 - Updated API information
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;
import javax.xml.namespace.QName;

import org.eclipse.wst.xml.xpath2.api.CollationProvider;
import org.eclipse.wst.xml.xpath2.api.DynamicContext;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.api.StaticContext;
import org.eclipse.wst.xml.xpath2.processor.DOMLoader;
import org.eclipse.wst.xml.xpath2.processor.XercesLoader;
import org.eclipse.wst.xml.xpath2.processor.internal.function.FnCollection;
import org.w3c.dom.Document;
import org.w3c.dom.Node;


/**
 * An implementation of a Dynamic Context.
 * 
 * Initializes and provides functionality of a dynamic context according to the
 * XPath 2.0 specification.
 * 
 * @since 2.0
 */
public class DynamicContextBuilder implements DynamicContext {

	private static DatatypeFactory _datatypeFactory;
	static {
		try {
			_datatypeFactory = DatatypeFactory.newInstance();
		}
		catch (DatatypeConfigurationException e) {
			throw new RuntimeException("Cannot initialize XML datatypes", e);
		}
	}
	private TimeZone _systemTimezone = TimeZone.getDefault();
	
	private Duration _tz = _datatypeFactory.newDuration(_systemTimezone.getRawOffset());
	private GregorianCalendar _currentDateTime;
	
	private Map<QName,ResultSequence> _variables = new HashMap<QName,ResultSequence>();
	private final StaticContext _staticContext;

	private Map<String, List<Document>> _collections;

	private Map<URI, Document> _loaded_documents = new HashMap<URI, Document>();

	public DynamicContextBuilder(StaticContext sc) {
		_staticContext = sc;
	}
	
	/**
	 * Reads the day from a TimeDuration type
	 * 
	 * @return an xs:integer _tz
	 */
	public Duration getTimezoneOffset() {
		return _tz;
	}

	/**
	 * Gets the Current stable date time from the dynamic context.
	 */
	public GregorianCalendar getCurrentDateTime() {
		if (_currentDateTime == null) {
			_currentDateTime = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
		}
		return _currentDateTime;
	}

	public Node getLimitNode() {
		return null;
	}

	public ResultSequence getVariable(QName name) {
		return _variables.get(name);
	}

	public Document getDocument(URI resolved) {
		Document doc = null;
		if (_loaded_documents.containsKey(resolved)) {
			 //tried before
			doc = _loaded_documents.get(resolved);
		} else {
			doc = retrieve_doc(resolved);
			_loaded_documents.put(resolved, doc);
		}
		return doc;
	}

	// XXX make it nice, and move it out as a utility function
	private Document retrieve_doc(URI uri) {
		try {
			DOMLoader loader = new XercesLoader();
			loader.set_validating(false);

			Document doc = loader.load(new URL(uri.toString()).openStream());
			doc.setDocumentURI(uri.toString());
			return doc;
		} catch (FileNotFoundException e) {
			return null;
		} catch (MalformedURLException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	public URI resolveUri(String uri) {
		try {
			URI realURI = URI.create(uri);
			if (realURI.isAbsolute()) {
				return realURI;
			}
			return _staticContext.getBaseUri().resolve(uri);
		} catch (IllegalArgumentException iae) {
			return null;
		}
	}

	public Map<String, List<Document>> getCollections() {
		return _collections;
	}

	public List<Document> getDefaultCollection() {
		return getCollections().get(FnCollection.DEFAULT_COLLECTION_URI);
	}

	public DynamicContextBuilder withVariable(javax.xml.namespace.QName qName, ResultSequence values) {
		this._variables.put(qName, values);
		return this;
	}
	
	public DynamicContextBuilder withTimezoneOffset(Duration d) {
		this._tz = d;
		return this;
	}

	public void withCollections(Map<String, List<Document>> map) {
		this._collections = map;
	}
	
	public CollationProvider getCollationProvider() {
		return _staticContext.getCollationProvider();
	}
}
