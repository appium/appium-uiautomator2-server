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
 *     Jesper Steen Moller  - bug 340933 - Migrate to new XPath2 API
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.wst.xml.xpath2.api.typesystem.ItemType;
import org.eclipse.wst.xml.xpath2.processor.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.SimpleAtomicItemTypeImpl;
import org.eclipse.wst.xml.xpath2.processor.internal.types.builtin.BuiltinTypeLibrary;

/**
 * Default implementation of a result sequence.
 * @deprecated use ResultBuffer instead
 */
public class DefaultResultSequence extends ResultSequence {

	private List _seq;

	/**
	 * Constructor.
	 * 
	 * an empty array is created
	 */
	public DefaultResultSequence() {
		_seq = new ArrayList();
	}

	/**
	 * @param item
	 *            is added
	 */
	public DefaultResultSequence(AnyType item) {
		this();
		add(item);
	}

	/**
	 * @param item
	 *            is added to array _seq
	 */
	public void add(AnyType item) {
		assert item != null;
		_seq.add(item);
	}

	/**
	 * @param rs
	 *            ResultSequence
	 */
	public void concat(ResultSequence rs) {
		for (Iterator i = rs.iterator(); i.hasNext();)
			_seq.add(i.next());
	}

	/**
	 * @return the next iteration of array _seq
	 */
	public ListIterator iterator() {
		return _seq.listIterator();
	}

	/**
	 * @return integer of the size of array _seq
	 */
	public int size() {
		return _seq.size();
	}

	/**
	 * @param i
	 *            is the position of the array item that is wanted.
	 * @return item i from array _seq
	 */
	public AnyType get(int i) {
		return (AnyType) _seq.get(i);
	}

	/**
	 * @return first item from array _seq
	 */
	public AnyType first() {
		if (_seq.size() == 0)
			return null;

		return get(0);
	}

	/**
	 * @return first item from array _seq
	 */
	public Object firstValue() {
		return get(0).getNativeValue();
	}

	/**
	 * Whether or not array _seq is empty
	 * 
	 * @return a boolean
	 */
	public boolean empty() {
		return _seq.isEmpty();
	}

	public ItemType sequenceType() {
		return new SimpleAtomicItemTypeImpl(BuiltinTypeLibrary.XS_ANYTYPE, ItemType.OCCURRENCE_NONE_OR_MANY);
	}

	/**
	 * Clears the sequence.
	 */
	public void clear() {
		_seq.clear();
	}

	/**
	 * Create a new sequence.
	 * 
	 * @return The new sequence.
	 */
	public ResultSequence create_new() {
		return new DefaultResultSequence();
	}
	
}
