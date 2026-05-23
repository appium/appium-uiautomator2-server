/*******************************************************************************
 * Copyright (c) 2011, 2018 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xml.xpath2.processor.internal;

import java.net.URI;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.Duration;

import org.eclipse.wst.xml.xpath2.api.CollationProvider;
import org.eclipse.wst.xml.xpath2.api.Item;
import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.internal.function.FnCollection;
import org.eclipse.wst.xml.xpath2.processor.internal.types.DocType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSDuration;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class DynamicContextAdapter implements
		org.eclipse.wst.xml.xpath2.api.DynamicContext {
	private final org.eclipse.wst.xml.xpath2.processor.DynamicContext dc;
	private StaticContextAdapter sca;

	public DynamicContextAdapter(
			org.eclipse.wst.xml.xpath2.processor.DynamicContext dc) {
		this.dc = dc;
		this.sca = new StaticContextAdapter(dc);
	}

	public Node getLimitNode() {
		return null;
	}

	public ResultSequence getVariable(javax.xml.namespace.QName name) {
		Object var = dc.get_variable(new QName(name));
		if (var == null) return ResultBuffer.EMPTY;
		if (var instanceof ResultSequence) return (ResultSequence)var;
		return ResultBuffer.wrap((Item)var);
	}

	public URI resolveUri(String uri) {
		return dc.resolve_uri(uri);
	}

	public GregorianCalendar getCurrentDateTime() {
		return dc.current_date_time();
	}

	public Duration getTimezoneOffset() {
		XSDuration tz = dc.tz();
		try {
			return DatatypeFactory.newInstance().newDuration(! tz.negative(), 0, 0, 0, tz.hours(), tz.minutes(), 0);
		} catch (DatatypeConfigurationException e) {
			throw new RuntimeException(e);
		}
	}

	public Document getDocument(URI uri) {
		org.eclipse.wst.xml.xpath2.processor.ResultSequence rs = dc.get_doc(uri);
		if (rs == null || rs.empty()) return null;
		return ((DocType)(rs.get(0))).value();
	}

	public Map<String, List<Document>> getCollections() {
		return dc.get_collections();
	}

	public List<Document> getDefaultCollection() {
		return getCollections().get(FnCollection.DEFAULT_COLLECTION_URI);
	}

	public CollationProvider getCollationProvider() {
		return sca.getCollationProvider();
	}

}