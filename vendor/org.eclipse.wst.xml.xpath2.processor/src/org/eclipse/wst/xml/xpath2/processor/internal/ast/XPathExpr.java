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

/**
 * Path expression walks tries to walk the path specified in its argument
 */
public class XPathExpr extends Expr {
	private int _slashes;
	private StepExpr _expr;

	// single linked list
	private XPathExpr _next;

	/**
	 * @param slashes
	 *            is copied to _slashes
	 * @param expr
	 *            is copied to _expr _next is made null as a result.
	 */
	public XPathExpr(int slashes, StepExpr expr) {
		_slashes = slashes;
		_expr = expr;
		_next = null;
	}

	/**
	 * Support for Visitor interface.
	 * 
	 * @return Result of Visitor operation.
	 */
	public Object accept(XPathVisitor v) {
		return v.visit(this);
	}

	/**
	 * Add to tail of path
	 */
	// XXX: keep ref to last
	public void add_tail(int slashes, StepExpr expr) {
		XPathExpr last = this;
		XPathExpr next = _next;

		while (next != null) {
			last = next;
			next = last.next();
		}

		XPathExpr item = new XPathExpr(slashes, expr);
		last.set_next(item);

	}

	/**
	 * @param count
	 *            is copied to _slashes
	 */
	public void set_slashes(int count) {
		_slashes = count;
	}

	/**
	 * @return XPath expression _next
	 */
	public XPathExpr next() {
		return _next;
	}

	/**
	 * an XPath expression, n is copied to _next
	 */
	public void set_next(XPathExpr n) {
		_next = n;
	}

	/**
	 * @return Step expression _expr
	 */
	public StepExpr expr() {
		return _expr;
	}

	/**
	 * @return int _slashes
	 */
	public int slashes() {
		return _slashes;
	}

}
