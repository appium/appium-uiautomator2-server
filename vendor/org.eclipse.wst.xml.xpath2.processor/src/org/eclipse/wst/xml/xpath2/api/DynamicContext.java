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
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.api;

import java.net.URI;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.Duration;
import javax.xml.namespace.QName;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * Interface for dynamic context. This covers the Dynamic Context as defined by the XPath2 specification, EXCEPT for 
 * context item and size (handled in EvaluationContext) 
 * @since 2.0
 */
public interface DynamicContext {

	/**
	 * Returns a "top" node which the XPath2 engine is not allowed to
	 * leave
	 * 
	 * @return DOM node which limits axis navigation.
	 */
	public Node getLimitNode();

	/**
	 * Get variable.
	 * 
	 * @param name
	 *            is the name of the variable.
	 * @return variable.
	 */
	public ResultSequence getVariable(QName name);

	/**
	 * Resolve an URI
	 * 
	 * @param uri
	 *            is the possibly relative URI to resolve
	 * @return the absolutized, resolved URI.
	 */
	public URI resolveUri(String uri);

	/**
	 * [Definition: Current dateTime. This
	 * information represents an implementation-dependent point in time during
	 * the processing of an expression, and includes an explicit timezone. It
	 * can be retrieved by the fn:current-dateTime function. If invoked
	 * multiple times during the execution of an expression, this function
	 * always returns the same result.]
     *
	 * Returns the current date time using the GregorianCalendar.
	 * 
	 * @return The current date and time, which will always be same for the dynamic context.
	 */
	public GregorianCalendar getCurrentDateTime();
	/**
	 * [Definition: Implicit timezone. This
	 * is the timezone to be used when a date, time, or dateTime value that
	 * does not have a timezone is used in a comparison or arithmetic
	 * operation. The implicit timezone is an implementation-defined value of
	 * type xs:dayTimeDuration. See [XML Schema] for the range of legal values
	 * of a timezone.]
	 * 
	 * @return current date time and implicit timezone.
	 */
	public Duration getTimezoneOffset();

	/**
	 * [Definition: Available documents. This is a mapping of
	 * strings onto document nodes. The string represents the absolute URI of
	 * a resource. The document node is the root of a tree that represents
	 * that resource using the data model. The document node is returned by
	 * the fn:doc function when applied to that URI.] The set of available
	 * documents is not limited to the set of statically known documents, and
	 * it may be empty. If there are one or more URIs in available documents
	 * that map to a document node D, then the document-uri property of D must
	 * either be absent, or must be one of these URIs. Note: This means that
	 * given a document node $N, the result of fn:doc(fn:document-uri($N)) is
	 * $N will always be True, unless fn:document-uri($N) is an empty
	 * sequence.
	 * 
	 * @param uri
	 *            is the URI of the document.
	 * @return document.
	 */
	public org.w3c.dom.Document getDocument(URI uri);
	
	/**
	 * [Definition: Available collections. This is a mapping of
	 * strings onto sequences of nodes. The string represents the absolute URI
	 * of a resource. The sequence of nodes represents the result of the
	 * fn:collection function when that URI is supplied as the argument. ] The
	 * set of available collections is not limited to the set of statically
	 * known collections, and it may be empty. For every document node D that
	 * is in the target of a mapping in available collections, or that is the
	 * root of a tree containing such a node, the document-uri property of D
	 * must either be absent, or must be a URI U such that available documents
	 * contains a mapping from U to D." Note: This means that for any document
	 * node $N retrieved using the fn:collection function, either directly or
	 * by navigating to the root of a node that was returned, the result of
	 * fn:doc(fn:document-uri($N)) is $N will always be True, unless
	 * fn:document-uri($N) is an empty sequence. This implies a requirement
	 * for the fn:doc and fn:collection functions to be consistent in their
	 * effect. If the implementation uses catalogs or user-supplied URI
	 * resolvers to dereference URIs supplied to the fn:doc function, the
	 * implementation of the fn:collection function must take these mechanisms
	 * into account. For example, an implementation might achieve this by
	 * mapping the collection URI to a set of document URIs, which are then
	 * resolved using the same catalog or URI resolver that is used by the
	 * fn:doc function.*/
	public Map<String, List<Document>> getCollections();
	/**
	 * [Definition: Default collection. This is the sequence
	 * of nodes that would result from calling the fn:collection function with
	 * no arguments.] The value of default collection may be initialized by
	 * the implementation.
	 */	
	public List<Document> getDefaultCollection();

	/** 
	 * Actual collation providers available for use dynamically. This could
	 * differ from the collations available statically, but would give
	 * unexpected results.
	 */
	public CollationProvider getCollationProvider();
}
