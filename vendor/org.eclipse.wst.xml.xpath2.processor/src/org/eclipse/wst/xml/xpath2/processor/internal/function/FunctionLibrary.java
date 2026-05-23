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
 *     Mukul Gandhi - bug 274471 - improvements to fn:string function (support for arity 0)
 *     Jesper Steen Moeller - bug 285145 - implement full arity checking
 *     Jesper Steen Moller  - bug 340933 - Migrate to new XPath2 API
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.util.Hashtable;
import java.util.Map;

import org.eclipse.wst.xml.xpath2.processor.DynamicContext;
import org.eclipse.wst.xml.xpath2.processor.StaticContext;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;

/**
 * Class for Function Library support.
 */
public class FunctionLibrary implements org.eclipse.wst.xml.xpath2.api.FunctionLibrary {
	private String _namespace;
	private Map _functions;
	private StaticContext _sc;
	private DynamicContext _dc;

	/**
	 * Constructor for FunctionLibrary.
	 * 
	 * @param ns
	 *            namespace.
	 */
	public FunctionLibrary(String ns) {
		_namespace = ns;
		_functions = new Hashtable();
		_sc = null;
		_dc = null;
	}

	/**
	 * Add a function.
	 * 
	 * @param x
	 *            function to add.
	 */
	public void add_function(Function x) {
		x.name().set_namespace(_namespace);
		_functions.put(x.signature(), x);
	}

	/**
	 * Checks whether the function exists or not.
	 * 
	 * @param name
	 *            QName of function.
	 * @param arity
	 *            arity of the function.
	 * @return Result of the test.
	 */
	public boolean function_exists(QName name, int arity) {
		return function(name, arity) != null;
	}

	/**
	 * Function support.
	 * 
	 * @param name
	 *            QName.
	 * @param arity
	 *            arity of the function.
	 * @return The new function.
	 */
	public Function function(QName name, int arity) {
		Function f = (Function) _functions.get(Function.signature(name, arity));

		if (f != null || arity == -1)
			return f;

		// see if we got a varg one
		f = function(name, -1);

		// nope
		if (f == null)
			return null;
		
		if (f.matches_arity(arity))
			return f;

		return null;
	}

	/**
	 * Support for namespace.
	 * 
	 * @return Namespace.
	 */
	public String namespace() {
		return _namespace;
	}

	/**
	 * Set static context on function.
	 */
	public void set_static_context(StaticContext sc) {
		_sc = sc;
	}

	/**
	 * Set dynamic context on function.
	 */
	public void set_dynamic_context(DynamicContext dc) {
		_dc = dc;
	}

	/**
	 * Support for Static context.
	 * 
	 * @return Result of static context.
	 */
	public StaticContext static_context() {
		return _sc;
	}

	/**
	 * Support for Dynamic context.
	 * 
	 * @return Result of dynamic context.
	 */
	public DynamicContext dynamic_context() {
		return _dc;
	}

	public boolean functionExists(String name, int arity) {
		return function_exists(new QName(null,name, namespace()), arity);
	}

	public org.eclipse.wst.xml.xpath2.api.Function resolveFunction(
			String localName, int arity) {

		return function(new QName("f", localName, namespace()), arity);
	}

	public String getNamespace() {
		return namespace();
	}
}
