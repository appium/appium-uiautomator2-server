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
 *     Jesper Steen Moller - bug 312191 - instance of test fails with partial matches
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.ast;

/**
 * Support for Sequence type.
 */
public class SequenceType extends XPathNode {
	/**
	 * Set internal value for EMPTY.
	 */
	public static final int EMPTY = 0;
	/**
	 * Set internal value for NONE.
	 */
	public static final int NONE = 1;
	/**
	 * Set internal value for QUESTION.
	 */
	public static final int QUESTION = 2;
	/**
	 * Set internal value for STAR.
	 */
	public static final int STAR = 3;
	/**
	 * Set internal value for PLUS.
	 */
	public static final int PLUS = 4;

	private int _occ;
	private ItemType _it;

	/**
	 * Constructor for SequenceType.
	 * 
	 * @param occ
	 *            Occurence.
	 * @param it
	 *            Item type.
	 */
	public SequenceType(int occ, ItemType it) {
		_occ = occ;
		_it = it;
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
	 * Get occurence of item.
	 * 
	 * @return Result from Int operation.
	 */
	public int occurrence() {
		return _occ;
	}

	/**
	 * Support for ItemType interface.
	 * 
	 * @return Result of ItemType operation.
	 */
	public ItemType item_type() {
		return _it;
	}
	
	public boolean isLengthValid(int length) {
		switch (occurrence()) {
		case EMPTY: return length == 0;
		case NONE: return length == 1;
		case QUESTION: return length <= 1;
		case STAR: return true;
		case PLUS: return length >= 1;
		default:
			assert false;
			return false;
		}
	}
}
