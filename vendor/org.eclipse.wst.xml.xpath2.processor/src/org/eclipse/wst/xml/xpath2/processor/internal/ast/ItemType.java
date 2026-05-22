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

import org.eclipse.wst.xml.xpath2.processor.internal.types.*;

/**
 * Support for Item node type.
 */
public class ItemType extends XPathNode {
	/**
	 * Set internal value for ITEM.
	 */
	public static final int ITEM = 0;
	/**
	 * Set internal value for QNAME.
	 */
	public static final int QNAME = 1;
	/**
	 * Set internal value for KINDTEST.
	 */
	public static final int KINDTEST = 2;
	private int _type;

	private QName _qname;
	private KindTest _ktest;

	// XXX: polymorphism
	/**
	 * Constructor for ItemType.
	 * 
	 * @param type
	 *            Type.
	 * @param value
	 *            Object value.
	 */
	public ItemType(int type, Object value) {
		_qname = null;
		_ktest = null;

		_type = type;

		switch (type) {
		case QNAME:
			_qname = (QName) value;
			break;
		case KINDTEST:
			_ktest = (KindTest) value;
			break;
		}
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
	 * Support for Type interface.
	 * 
	 * @return Result of Type operation.
	 */
	public int type() {
		return _type;
	}

	/**
	 * Support for QName interface.
	 * 
	 * @return Result of QName operation.
	 */
	public QName qname() {
		return _qname;
	}

	/**
	 * Support KindTest interface.
	 * 
	 * @return Result of KindTest operation.
	 */
	public KindTest kind_test() {
		return _ktest;
	}
}
