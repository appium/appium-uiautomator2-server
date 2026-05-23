/*******************************************************************************
 * Copyright (c) 2005, 2009 Andrea Bittau, University College London, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Andrea Bittau - initial API and implementation from the PsychoPath XPath 2.0 
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.ast;

/**
 * Abstract class for a Binary operation.
 */
public abstract class BinExpr extends Expr {
	// XXX: review hierarchy - strictly should be Expr
	// or rename to binop
	private XPathNode _left;
	private XPathNode _right;

	/**
	 * Constructor for BinExpr.
	 * 
	 * @param l
	 *            left xpath node for the operation.
	 * @param r
	 *            right xpath node for the operation.
	 */
	public BinExpr(XPathNode l, XPathNode r) {
		_left = l;
		_right = r;
	}

	/**
	 * Left xpath node.
	 * 
	 * @return Left node.
	 */
	public XPathNode left() {
		return _left;
	}

	/**
	 * Right xpath node.
	 * 
	 * @return Right node.
	 */
	public XPathNode right() {
		return _right;
	}

	/**
	 * Set the left xpath node.
	 * 
	 * @param n
	 *            Left node.
	 */
	public void set_left(XPathNode n) {
		_left = n;
	}

	/**
	 * Set the right xpath node.
	 * 
	 * @param n
	 *            Right node.
	 */
	public void set_right(XPathNode n) {
		_right = n;
	}
}
