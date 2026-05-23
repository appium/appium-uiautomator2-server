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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.wst.xml.xpath2.api.Function;
import org.eclipse.wst.xml.xpath2.api.FunctionLibrary;

/**
 * Class for Function Library support.
 */
public class FunctionLibraryImpl implements FunctionLibrary {
	private String _namespace;
	private Map _functions;

	/**
	 * Constructor for FunctionLibrary.
	 * 
	 * @param ns
	 *            namespace.
	 */
	public FunctionLibraryImpl(String ns) {
		_namespace = ns;
		_functions = new HashMap();
	}

	/**
	 * Add a function.
	 * 
	 * @param x
	 *            function to add.
	 */
	public void addFunction(Function x) {
		_functions.put(signature(x), x);
	}

	/**
	 * Obtain the function name and arity from signature.
	 * 
	 * @param f
	 *            current function.
	 * @return Signature.
	 */
	private static String signature(Function f) {
		return signature(f.getName(), f.isVariableArgument() ? -1 : f.getMinArity());
	}

	/**
	 * Apply the name and arity to signature.
	 * 
	 * @param name
	 *            QName.
	 * @param arity
	 *            arity of the function.
	 * @return Signature.
	 */
	private static String signature(String name, int arity) {
		return name + "_" + ((arity < 0) ? "x" : arity);
	}

	public boolean functionExists(String name, int arity) {
		return resolveFunction(name, arity) != null;
	}

	public org.eclipse.wst.xml.xpath2.api.Function resolveFunction(
			String localName, int arity) {

		Function f = (Function) _functions.get(signature(localName, arity));

		if (f != null || arity == -1)
			return f;

		// see if we got a varg one
		f = (Function) _functions.get(signature(localName, -1));

		// nope
		if (f == null)
			return null;
		
		if (f.canMatchArity(arity))
			return f;

		return null;
	}

	public String getNamespace() {
		return _namespace;
	}
}
