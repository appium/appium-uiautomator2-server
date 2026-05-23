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
 * The comparison of expression operator takes the value of its left operand and
 * compares (dependant on type) against its right operand, according to the
 * rules of the particular comparison rule
 */
public class CmpExpr extends BinExpr {
	/**
	 * Set internal value for EQUALS operation.
	 */
	public static final int EQUALS = 0;
	/**
	 * Set internal value for NOTEQUALS operation.
	 */
	public static final int NOTEQUALS = 1;
	/**
	 * Set internal value for LESSTHAN operation.
	 */
	public static final int LESSTHAN = 2;
	/**
	 * Set internal value for LESSEQUAL operation.
	 */
	public static final int LESSEQUAL = 3;
	/**
	 * Set internal value for GREATER operation.
	 */
	public static final int GREATER = 4;
	/**
	 * Set internal value for GREATEREQUAL operation.
	 */
	public static final int GREATEREQUAL = 5;
	/**
	 * Set internal value for EQ operation.
	 */
	public static final int EQ = 6;
	/**
	 * Set internal value for NE operation.
	 */
	public static final int NE = 7;
	/**
	 * Set internal value for LT operation.
	 */
	public static final int LT = 8;
	/**
	 * Set internal value for LE operation.
	 */
	public static final int LE = 9;
	/**
	 * Set internal value for GT operation.
	 */
	public static final int GT = 10;
	/**
	 * Set internal value for GE operation.
	 */
	public static final int GE = 11;
	/**
	 * Set internal value for IS operation.
	 */
	public static final int IS = 12;
	/**
	 * Set internal value for LESS_LESS operation.
	 */
	public static final int LESS_LESS = 13;
	/**
	 * Set internal value for GREATER_GREATER operation.
	 */
	public static final int GREATER_GREATER = 14;

	private int _type;

	/**
	 * Constructor for CmpExpr
	 * 
	 * @param l
	 *            input xpath left expression/variable
	 * @param r
	 *            input xpath right expression/variable
	 * @param type
	 *            what comparison to use l against r.
	 */
	public CmpExpr(Expr l, Expr r, int type) {
		super(l, r);

		_type = type;
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
	 * @return comparison type
	 */
	public int type() {
		return _type;
	}
}
