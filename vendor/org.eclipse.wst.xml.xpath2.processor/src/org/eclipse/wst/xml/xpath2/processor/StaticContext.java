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
 *     David Carver (STAR) - bug 262765 - add ability to set the base uri 
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor;

import java.util.Map;

import org.eclipse.wst.xml.xpath2.api.typesystem.TypeDefinition;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeModel;
import org.eclipse.wst.xml.xpath2.processor.internal.function.FunctionLibrary;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyAtomicType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.NodeType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSAnyURI;
import org.w3c.dom.Node;

/**
 * interface to static context
 * 
 * @deprecated See <tt>org.eclipse.wst.xml.xpath2.api.StaticContext</tt> instead
 */
public interface StaticContext {

	/**
	 * is it xpath 1.0 compatible.
	 * 
	 * @return boolean
	 */
	public boolean xpath1_compatible();

	/**
	 * namespaces does the prefix exist
	 * 
	 * @param prefix
	 *            is the prefix
	 * @return boolean
	 */
	public boolean prefix_exists(String prefix);

	/**
	 * @param prefix
	 *            is the prefix
	 * @return string
	 */
	public String resolve_prefix(String prefix);

	/**
	 * the default namespace
	 * 
	 * @return string
	 */
	public String default_namespace();

	/**
	 * the default function namespace
	 * 
	 * @return string
	 */
	public String default_function_namespace();

	// in scope schema definitions
	/**
	 * @param attr
	 *            is the qname variable
	 * @return attributes's type definition
	 * @since 2.0
	 */
	public TypeDefinition attribute_type_definition(QName attr);

	/**
	 * @param elem
	 *            is the elem of the qname
	 * @return element's type definition
	 */
	public TypeDefinition element_type_definition(QName elem);

	/**
	 * is the attribute declared?
	 * 
	 * @param attr
	 *            is the attribute of the qname
	 * @return boolean
	 */
	public boolean attribute_declared(QName attr);

	/**
	 * is the element declared?
	 * 
	 * @param elem
	 *            is the elem of the qname
	 * @return boolean
	 */
	public boolean element_declared(QName elem);

	// in scope variables

	// context item type

	/**
	 * is the element declared?
	 * 
	 * @param name
	 *            is the qname name
	 * @param arity
	 *            integer of qname
	 * @return boolean
	 */
	// function signatures
	public boolean function_exists(QName name, int arity);

	// collations

	/**
	 * base uri
	 * 
	 * @return uri
	 */
	// base uri
	public XSAnyURI base_uri();

	// statically known documents

	// collections

	// other stuff
	/**
	 * new scope
	 */
	public void new_scope();

	/**
	 * destroy scope
	 */
	public void destroy_scope();

	/**
	 * add variable
	 * 
	 * @param name
	 *            is the qname
	 */
	public void add_variable(QName name);

	/**
	 * delete the variable
	 * 
	 * @param name
	 *            is the qname
	 * @return boolean if deleted variable
	 */
	public boolean del_variable(QName name);

	/**
	 * @param name
	 *            is the qname
	 * @return boolean if variable exists
	 */
	public boolean variable_exists(QName name); // in current scope only

	/**
	 * @param var
	 *            is the variable of qname
	 */
	public boolean variable_in_scope(QName var);

	/**
	 * @param name
	 *            is qname
	 * @return boolean
	 */
	public boolean type_defined(QName name);

	/**
	 * @param at
	 *            the node type
	 * @param et
	 *            is the qname
	 * @return boolean
	 */
	public boolean derives_from(NodeType at, QName et);

	/**
	 * @param at
	 *            the node type
	 * @param et
	 *            is the XSTypeDefinition of the node
	 * @return boolean
	 * @since 2.0
	 */
	public boolean derives_from(NodeType at, TypeDefinition et);

	/**
	 * add namespace
	 * 
	 * @param prefix
	 *            the prefix of the namespace
	 * @param ns
	 *            is the XSTypeDefinition of the node
	 */
	public void add_namespace(String prefix, String ns);

	/**
	 * expand function
	 * 
	 * @param name
	 *            is the qname
	 * @return boolean if function can be expanded
	 */
	public boolean expand_function_qname(QName name);

	/**
	 * expand element type qname
	 * 
	 * @param name
	 *            is the qname
	 * @return boolean if function can be expanded
	 */
	public boolean expand_elem_type_qname(QName name);

	/**
	 * expand qname
	 * 
	 * @param name
	 *            is the qname
	 * @return boolean if function can be expanded
	 */
	public boolean expand_qname(QName name);

	/**
	 * add function to library
	 * 
	 * @param fl
	 *            is the function library
	 */
	public void add_function_library(FunctionLibrary fl);

	/**
	 * @param name
	 *            is the qname
	 * @return any atomic type
	 */
	public AnyAtomicType make_atomic(QName name);
	
	/**
	 * Sets the base uri for the context.
	 * @param baseuri
	 * @since 1.1
	 */
	public void set_base_uri(String baseuri);
	
	/**
	 * @since 1.1
	 * 
	 * Gets the collections map, which maps a String into a List of Document, in
	 * Java5 it would be <code>Map<String, List<Document>></code>
	 * 
	 */
	public Map get_collections();
	
	/**
	 * @since 1.1
	 * 
	 * Sets the collections map, which maps a String into a List of Document, in
	 * Java5 it would be <code>Map<String, List<Document>></code>
	 */
	public void set_collections(Map collections);

	/**
	 * Gets the type provider in use for the specified DOM node.
	 * 
	 * @since 2.0
	 */
	public TypeModel getTypeModel(Node element);
	
}
