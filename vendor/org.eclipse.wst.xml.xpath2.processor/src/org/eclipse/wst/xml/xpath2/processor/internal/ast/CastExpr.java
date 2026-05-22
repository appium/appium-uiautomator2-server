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
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.ast;

import org.eclipse.wst.xml.xpath2.api.Function;

/**
 * A class that creates a new value of a specific type based on an existing
 * value. A cast expression takes two operands: an input expression and a target
 * type. The type of the input expression is called the input type. The target
 * type must be a named atomic type, represented by a QName, optionally followed
 * by the occurrence indicator ? if an empty sequence is permitted. If the
 * target type has no namespace prefix, it is considered to be in the default
 * element/type namespace.
 */
public class CastExpr extends BinExpr {
	private Function _function;

	/**
	 * Constructor for CastExpr.
	 * 
	 * @param l
	 *            input xpath expression/variable.
	 * @param r
	 *            SingleType to cast l to.
	 */
	public CastExpr(Expr l, SingleType r) {
		super(l, r);
	}

	
	public Function function() {
		return _function;
	}
	
	public void set_function(Function _function) {
		this._function = _function;
	}
	
	/**
	 * Support for Visitor interface.
	 * 
	 * @return Result of Visitor operation.
	 */
	public Object accept(XPathVisitor v) {
		return v.visit(this);
	}
}
