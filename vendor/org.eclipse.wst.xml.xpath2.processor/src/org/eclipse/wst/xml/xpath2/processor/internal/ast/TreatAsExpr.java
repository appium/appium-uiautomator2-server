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
 * Support for Treat operation. This does not change the value of the operand,
 * rather it ensues the operand has a correct type at evaluation time.
 */
public class TreatAsExpr extends BinExpr {
	/**
	 * Constructor for TreatAsExpr.
	 * 
	 * @param l
	 *            xpath expression/variable.
	 * @param r
	 *            SequenceType to treat as.
	 */
	public TreatAsExpr(Expr l, SequenceType r) {
		super(l, r);
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
