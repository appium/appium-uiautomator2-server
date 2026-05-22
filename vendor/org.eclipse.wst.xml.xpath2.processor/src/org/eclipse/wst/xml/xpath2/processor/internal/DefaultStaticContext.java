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
 *     David Carver (STAR) - bug 277792 - add built in types to static context. 
 *     Jesper Steen Moller - bug 297707 - Missing the empty-sequence() type
 *     Mukul Gandhi        - bug 325262 - providing ability to store an XPath2 sequence
 *                                        into an user-defined variable.
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

import org.eclipse.wst.xml.xpath2.api.typesystem.TypeDefinition;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeModel;
import org.eclipse.wst.xml.xpath2.processor.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.StaticContext;
import org.eclipse.wst.xml.xpath2.processor.function.FnFunctionLibrary;
import org.eclipse.wst.xml.xpath2.processor.function.XSCtrLibrary;
import org.eclipse.wst.xml.xpath2.processor.internal.function.ConstructorFL;
import org.eclipse.wst.xml.xpath2.processor.internal.function.Function;
import org.eclipse.wst.xml.xpath2.processor.internal.function.FunctionLibrary;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyAtomicType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.NodeType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSAnyURI;
import org.w3c.dom.Node;

/**
 * Default implementation of a static context as described by the XPath 2.0
 * specification.
 */
public class DefaultStaticContext implements StaticContext {

	private boolean _xpath1_compatible;
	private String _default_namespace;
	private String _default_function_namespace;
	private TypeModel _model;
	private XSCtrLibrary builtinTypes;

	// key: String prefix, contents: String namespace
	private Map _namespaces;

	private String _cntxt_item_type;
	private Map _functions;

	// XXX collations

	private XSAnyURI _base_uri;
	private Map _documents;
	private Map _collections;
	
	public String get_cntxt_item_type() {
		return _cntxt_item_type;
	}

	public void set_cntxt_item_type(String cntxtItemType) {
		_cntxt_item_type = cntxtItemType;
	}

	public Map get_collections() {
		return _collections;
	}

	public void set_collections(Map collections) {
		_collections = collections;
	}

	public String get_default_collection_type() {
		return _default_collection_type;
	}

	public void set_default_collection_type(String defaultCollectionType) {
		_default_collection_type = defaultCollectionType;
	}

	private String _default_collection_type;

	// Variables are held like this:
	// A stack of maps of variables....
	// or in more human terms:
	// a stack of scopes each containing a symbol table
	// XXX vars contain AnyType... should they be ResultSequence ?
	private Stack _scopes;

	/**
	 * Constructor.
	 * 
	 * @param schema
	 *            Schema information from document. May be null.
	 */
	public DefaultStaticContext(TypeModel model) {
		_xpath1_compatible = false;

		_default_namespace = null;
		_default_function_namespace = FnFunctionLibrary.XPATH_FUNCTIONS_NS;
		_model = model;
		builtinTypes = new XSCtrLibrary();

		_functions = new HashMap(20); // allow null keys: null namespace
		_namespaces = new HashMap(20); // ditto

		_cntxt_item_type = null;

		_scopes = new Stack();
		new_scope();

		if (_model != null)
			init_schema(model);

		_base_uri = new XSAnyURI();

		// XXX wildcard prefix
		add_namespace("*", "*");

	}

	/**
	 * Constructor for schema-less documents.
	 * 
	 */
	public DefaultStaticContext() {
		this(null);
	}

	private void init_schema(TypeModel schema) {
		_model = schema;
	}

	/**
	 * return the base URI
	 * 
	 * @return XSAnyURI
	 */
	public XSAnyURI base_uri() {
		return _base_uri;
	}

	/**
	 * is it xpath1 compatible?
	 * 
	 * @return boolean
	 */
	public boolean xpath1_compatible() {
		return _xpath1_compatible;
	}

	/**
	 * adds namespace
	 * 
	 * @param prefix
	 *            namespace prefix
	 * @param namespace
	 *            namespace URI
	 * 
	 */
	public void add_namespace(String prefix, String namespace) {
		// XXX are these reserved ?
		// refer to formal semantics section 2.5.1
		if ((prefix != null) && (prefix.equals("fs") || prefix.equals("op") || prefix.equals("dm")))
			return;
		 if (prefix == null) {
		   _default_namespace = namespace; 
		   _namespaces.put("", namespace);
		 } else {
		   _namespaces.put(prefix, namespace);
		 }
	}

	/**
	 * Retrieves the default namespace, when one is not allocated
	 * 
	 * @return string
	 */
	public String default_namespace() {
		return _default_namespace;
	}

	/**
	 * Retrieves the defaul function namespace
	 * 
	 * @return string
	 */
	public String default_function_namespace() {
		return _default_function_namespace;
	}

	/**
	 * Adds a function to the library.
	 * 
	 * @param fl
	 *            Function library to add.
	 */
	public void add_function_library(FunctionLibrary fl) {
		fl.set_static_context(this);
		_functions.put(fl.namespace(), fl);
	}

	/**
	 * Check for existance of function.
	 * 
	 * @param name
	 *            function name.
	 * @param arity
	 *            arity of function.
	 * @return true if function exists. False otherwise.
	 */
	public boolean function_exists(QName name, int arity) {
		String ns = name.namespace();
		if (!_functions.containsKey(ns))
			return false;

		FunctionLibrary fl = (FunctionLibrary) _functions.get(ns);

		return fl.function_exists(name, arity);
	}

	public Function function(QName name, int arity) {
		String ns = name.namespace();
		if (!_functions.containsKey(ns))
			return null;

		FunctionLibrary fl = (FunctionLibrary) _functions.get(ns);

		return fl.function(name, arity);
	}

	/**
	 * 
	 * Creates an atomic from a specific type name initialized with a default
	 * value.
	 * 
	 * @param name
	 *            name of type to create
	 * @return Atomic type of desired type.
	 */
	public AnyAtomicType make_atomic(QName name) {
		String ns = name.namespace();

		if (!_functions.containsKey(ns))
			return null;

		FunctionLibrary fl = (FunctionLibrary) _functions.get(ns);

		if (!(fl instanceof ConstructorFL))
			return null;

		ConstructorFL cfl = (ConstructorFL) fl;

		return cfl.atomic_type(name);
	}

	private boolean expand_qname(QName name, String def) {
		String prefix = name.prefix();

		if (prefix == null) {
			name.set_namespace(def);
			return true;
		}

		if (!prefix_exists(prefix))
			return false;

		name.set_namespace(resolve_prefix(prefix));
		return true;

	}

	/**
	 * Expands the qname's prefix into a namespace.
	 * 
	 * @param name
	 *            qname to expand.
	 * @return true on success.
	 */
	public boolean expand_qname(QName name) {
		return expand_qname(name, null);
	}

	/**
	 * Expands a qname and uses the default function namespace if unprefixed.
	 * 
	 * @param name
	 *            qname to expand.
	 * @return true on success.
	 */
	public boolean expand_function_qname(QName name) {
		return expand_qname(name, default_function_namespace());
	}

	/**
	 * Expands a qname and uses the default type/element namespace if
	 * unprefixed.
	 * 
	 * @param name
	 *            qname to expand.
	 * @return true on success.
	 */
	public boolean expand_elem_type_qname(QName name) {
		return expand_qname(name, default_namespace());
	}

	/**
	 * 
	 * Checks whether the type is defined in the in scope schema definitions.
	 * 
	 * @param qname
	 *            type name.
	 * @return true if type is defined.
	 */
	public boolean type_defined(QName qname) {
		
		if (_model == null) {
			return builtinTypes.atomic_type(qname) != null;
		}

		TypeDefinition td = _model.lookupType(qname.namespace(), qname.local());
		if (td == null)
			return false;

		return true;
	}

	/**
	 * Checks whether the type is defined in the in scope schema definitions.
	 * 
	 * @param ns
	 *            namespace of type.
	 * @param type
	 *            name of type.
	 * @return true if type is defined.
	 * 
	 */
	public boolean type_defined(String ns, String type) {
		return type_defined(new QName(ns, type));
	}

	/**
	 * is element declared?
	 * 
	 * @param elem
	 *            name of element.
	 * @return true if element declared.
	 */
	public boolean element_declared(QName elem) {
		if (_model == null)
			return false;

		TypeDefinition td = _model.lookupElementDeclaration(elem.local(),
				elem.namespace());

		if (td == null)
			return false;

		return true;
	}

	/**
	 * Obtains schema definition of the type of an element.
	 * 
	 * @param elem
	 *            name of element who's type is desired.
	 * @return schema definition of type
	 */
	public TypeDefinition element_type_definition(QName elem) {
		return _model.lookupElementDeclaration(elem.local(),
				elem.namespace());
	}

	/**
	 * Checks if an attribute is in the in-scope schema definitions.
	 * 
	 * @param attr
	 *            name of attribute.
	 * @return true if attribute is declared.
	 */
	public boolean attribute_declared(QName attr) {
		if (_model == null)
			return false;

		TypeDefinition td = _model.lookupAttributeDeclaration(attr
				.local(), attr.namespace());

		if (td == null)
			return false;

		return true;
	}

	/**
	 * Retrieves type definition of the attribute in an element.
	 * 
	 * @param elem
	 *            element name
	 * @return schema definition of the type of the attribute
	 */
	public TypeDefinition attribute_type_definition(QName elem) {
		return _model.lookupAttributeDeclaration(elem
				.local(), elem.namespace());
	}

	/**
	 * does prefix exist?
	 * 
	 * @param pref
	 *            prefix name.
	 * @return true if it does.
	 */
	public boolean prefix_exists(String pref) {
		return _namespaces.containsKey(pref);
	}

	/**
	 * Resolves a prefix into a namespace URI.
	 * 
	 * @param pref
	 *            prefix name
	 * @return uri prefix is resolved to or null.
	 */
	public String resolve_prefix(String pref) {
		return (String) _namespaces.get(pref);
	}

	/**
	 * Checks if an XML node derives from a specified type.
	 * 
	 * @param at
	 *            node actual type
	 * @param et
	 *            name of expected type
	 * @return true if a derivation exists
	 */
	// XXX fix this
	public boolean derives_from(NodeType at, QName et) {
		
		TypeDefinition td = _model.getType(at.node_value());

		short method = TypeDefinition.DERIVATION_EXTENSION | TypeDefinition.DERIVATION_RESTRICTION;

		// XXX
		if (!et.expanded()) {
			String pre = et.prefix();

			if (pre != null) {
				if (prefix_exists(pre)) {
					et.set_namespace(resolve_prefix(pre));
				} else
					assert false;
			} else
				et.set_namespace(default_namespace());
		}

		return td != null && td.derivedFrom(et.namespace(), et.local(), method);
	}

	/**
	 * Checks if an XML node derives from a specified type definition.
	 * 
	 * @param at
	 *            node actual type.
	 * @param et
	 *            type definition of expected type.
	 * @return true if a derivation exists.
	 */
	public boolean derives_from(NodeType at, TypeDefinition et) {
		TypeDefinition td = _model.getType(at.node_value());
		short method = 0;
		return td.derivedFromType(et, method);
	}

	/**
	 * Creates a new scope level.
	 */
	// variable stuff
	public void new_scope() {
		Map vars = new HashMap();

		_scopes.push(vars);
	}

	/**
	 * Destroys a scope.
	 */
	public void destroy_scope() {
		_scopes.pop();
	}

	private Map current_scope() {
		return (Map) _scopes.peek();
	}

	/**
	 * does variable exist in current scope ?
	 * 
	 * @param var
	 *            variable name.
	 * @return true if it does.
	 */
	public boolean variable_exists(QName var) {
		Map scope = current_scope();

		return scope.containsKey(var);
	}

	/**
	 * checks to see if variable is in scope
	 * 
	 * @param var
	 *            variable name.
	 * @return true if variable is in current or above scope.
	 */
	public boolean variable_in_scope(QName var) {
		// order doesn't matter..
		for (Iterator i = _scopes.iterator(); i.hasNext();) {
			Map scope = (Map) i.next();

			if (scope.containsKey(var))
				return true;
		}
		return false;
	}

	/**
	 * Adds a variable to current scope.
	 * 
	 * used for static checking.... i.e. presence of variables
	 * 
	 * @param var
	 *            variable name to add.
	 */
	public void add_variable(QName var) {
		set_variable(var, (AnyType) null);
	}

	// overwrites, or creates
	protected void set_variable(QName var, AnyType val) {
		Map scope = current_scope();

		scope.put(var, val);
	}
	
	/*
	 * Set a XPath2 sequence into a variable.
	 */
	protected void set_variable(QName var, ResultSequence val) {
		Map scope = current_scope();

		scope.put(var, val);
	}
	

	/**
	 * Deletes a variable from current scope.
	 * 
	 * @param var
	 *            variable name to delete.
	 * @return false if variable doesn't exist.
	 */
	public boolean del_variable(QName var) {
		if (!variable_exists(var))
			return false;

		Map scope = current_scope();
		if (scope.remove(var) == null)
			return false;
		return true;

	}

	// return null if "not found"
	protected Object get_var(QName var) {
		// go through the stack in reverse order... reverse iterators
		// would be nice here...

		int pos = _scopes.size();
		while (--pos >= 0) {
			Map scope = (Map) _scopes.get(pos);

			// gotcha
			if (scope.containsKey(var)) {
				return scope.get(var);
			}
		}

		return null;
	}

	/**
	 * Debug function which will print current variable scopes and info.
	 */
	// debug functions
	public void debug_print_vars() {
		int level = 0;

		for (Iterator i = _scopes.iterator(); i.hasNext();) {
			Map scope = (Map) i.next();

			System.out.println("Scope level " + level);
//			scope.entrySet().iterator();
			for (Iterator j = scope.entrySet().iterator(); j.hasNext();) {
				QName varname = (QName) j.next();

				AnyType val = (AnyType) scope.get(varname);

				String string_val = "null";

				if (val != null)
					string_val = val.getStringValue();

				System.out.println("Varname: " + varname.string()
						+ " expanded=" + varname.expanded() + " Value: "
						+ string_val);

			}

			level++;
		}
	}

	/**
	 * Set the Base URI for the static context.
	 */
	public void set_base_uri(String baseuri) {
		_base_uri = new XSAnyURI(baseuri);
	}

	public void set_documents(Map _documents) {
		this._documents = _documents;
	}

	public Map get_documents() {
		return _documents;
	}

	public TypeModel getTypeModel(Node node) {
		return _model;
	}

	public Map get_function_libraries() {
		return _functions;
	}
}
