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

import java.math.BigDecimal;

import org.eclipse.wst.xml.xpath2.processor.internal.types.*;

/**
 *The value of a numeric literal containing "." but no e or E character is an
 * atomic value of type xs:decimal
 * 
 */
public class DecimalLiteral extends NumericLiteral {
	private XSDecimal _value;

	/**
	 * Constructor for DecimalLiteral
	 * 
	 * @param value
	 *            double value
	 */
	public DecimalLiteral(BigDecimal value) {
		_value = new XSDecimal(value);
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
	 * @return xs:decimal value
	 */
	public XSDecimal value() {
		return _value;
	}
}
