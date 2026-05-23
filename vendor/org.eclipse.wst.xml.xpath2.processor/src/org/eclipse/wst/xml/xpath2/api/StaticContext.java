/*******************************************************************************
 * Copyright (c) 2011 Jesper Moller, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Jesper Moller - initial API and implementation
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.api;

import java.net.URI;
import java.util.Map;

import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;

import org.eclipse.wst.xml.xpath2.api.typesystem.ItemType;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeDefinition;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeModel;

/**
 * interface to static context
 * @since 2.0
 */
public interface StaticContext {
	/**
	 * XPath 1.0 compatibility mode.
	 * 
	 * @return true if rules for backward compatibility with XPath Version 1.0
	 *         are in effect; otherwise false.
	 */
	public boolean isXPath1Compatible();

	/**
	 * [Definition: In-scope variables. This is a set of (expanded QName,
	 * type) pairs. It defines the set of variables that are available for
	 * reference within an expression. The expanded QName is the name of the
	 * variable, and the type is the static type of the variable.] An
	 * expression that binds a variable (such as a for, some, or every
	 * expression) extends the in-scope variables of its subexpressions with
	 * the new bound variable and its type.
	 */
	StaticVariableResolver getInScopeVariables();

	 /** 
	 * [Definition: Context item static type. This component defines the
	 * static type of the context item within the scope of a given
	 * expression.]
	 */
	TypeDefinition getInitialContextType();
	
	 /** 
	 * [Definition: Function signatures. This component defines the set of
	 * functions that are available to be called from within an expression.
	 * Each function is uniquely identified by its expanded QName and its
	 * arity (number of parameters).] In addition to the name and arity, each
	 * function signature specifies the static types of the function
	 * parameters and result. The function signatures include the signatures
	 * of constructor functions, which are discussed in 3.10.4 Constructor
	 * Functions.
	 */
	public Map<String, FunctionLibrary> getFunctionLibraries();

	/** 
	 * [Definition: Statically known collations. This is an
	 * implementation-defined set of (URI, collation) pairs. It defines the
	 * names of the collations that are available for use in processing
	 * expressions.]
	 */
	public CollationProvider getCollationProvider();
	
	/**
	 * [Definition: Base URI. This is an absolute URI, used when necessary in
	 * the resolution of relative URIs (for example, by the fn:resolve-uri
	 * function.)] The URI value is whitespace normalized according to the
	 * rules for the xs:anyURI type in [XML Schema].
	 */
	public URI getBaseUri();
	
	/**
	 * [Definition: Statically known documents. This is a mapping from strings
	 * onto types. The string represents the absolute URI of a resource that
	 * is potentially available using the fn:doc function. The type is the
	 * static type of a call to fn:doc with the given URI as its literal
	 * argument. ] If the argument to fn:doc is a string literal that is not
	 * present in statically known documents, then the static type of fn:doc
	 * is document-node()?. Note: The purpose of the statically known
	 * documents is to provide static type information, not to determine which
	 * documents are available. A URI need not be found in the statically
	 * known documents to be accessed using fn:doc.
	 */
	public ItemType getDocumentType(URI documentUri);

	/**
	 * [Definition: Statically known namespaces. This is a set of (prefix,
	 * URI) pairs that define all the namespaces that are known during static
	 * processing of a given expression.] The URI value is whitespace
	 * normalized according to the rules for the xs:anyURI type in [XML
	 * Schema]. Note the difference between in-scope namespaces, which is a
	 * dynamic property of an element node, and statically known namespaces,
	 * which is a static property of an expression.
	 * @return The statically known namespace context 
	 */
	public NamespaceContext getNamespaceContext();

	/**
	 *  [Definition: Default element/type namespace. This is a namespace URI or
	 * "none". The namespace URI, if present, is used for any unprefixed QName
	 * appearing in a position where an element or type name is expected.] The
	 * URI value is whitespace normalized according to the rules for the
	 * xs:anyURI type in [XML Schema].
	 * 
	 * @return
	 */
	public String getDefaultNamespace();
	
	/** 
	 * Definition: Default function namespace. This is a namespace URI or
	 * "none". The namespace URI, if present, is used for any unprefixed QName
	 * appearing in a position where a function name is expected.] The URI
	 * value is whitespace normalized according to the rules for the xs:anyURI
	 * type in [XML Schema].
	 * 
	 * @return The default function namespace
	 */
	public String getDefaultFunctionNamespace();

	/**
	 * [Definition: In-scope schema definitions. This is a generic term for
	 * all the element declarations, attribute declarations, and schema type
	 * definitions that are in scope during processing of an expression.] 
     *
  	 * @return A type model which covers the 
	 */
	public TypeModel getTypeModel();

	/**
	 * is the function declared/available in the source context?
	 * 
	 * @param name
	 *            is the qname name
	 * @param arity
	 *            integer of qname
	 * @return boolean
	 */
	// function signatures
	public Function resolveFunction(QName name, int arity);

	/**
	 * [Definition: Statically known collections. This is a mapping from
	 * strings onto types. The string represents the absolute URI of a
	 * resource that is potentially available using the fn:collection
	 * function. The type is the type of the sequence of nodes that would
	 * result from calling the fn:collection function with this URI as its
	 * argument.] If the argument to fn:collection is a string literal that is
	 * not present in statically known collections, then the static type of
	 * fn:collection is node()*. Note: The purpose of the statically known
	 * collections is to provide static type information, not to determine
	 * which collections are available. A URI need not be found in the
	 * statically known collections to be accessed using fn:collection.
	 */
	public TypeDefinition getCollectionType(String collectionName);

	/**
	 * [Definition: Statically known default collection type. This is the type
	 * of the sequence of nodes that would result from calling the
	 * fn:collection function with no arguments.] Unless initialized to some
	 * other value by an implementation, the value of statically known default
	 * collection type is node()*.
	 */
	public TypeDefinition getDefaultCollectionType();
}
