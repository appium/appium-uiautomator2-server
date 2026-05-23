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
 *     Mukul Gandhi - bug 274805 - improvements to xs:integer data type 
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *     Jesper Steen Moller  - bug 340933 - Migrate to new XPath2 API
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal;

import java.math.BigInteger;
import java.util.*;

import org.eclipse.wst.xml.xpath2.api.typesystem.ItemType;
import org.eclipse.wst.xml.xpath2.processor.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.ResultSequenceFactory;
import org.eclipse.wst.xml.xpath2.processor.internal.types.*;
import org.eclipse.wst.xml.xpath2.processor.internal.types.builtin.BuiltinTypeLibrary;

/**
 * A range expression can be used to construct a sequence of consecutive
 * integers.
 */
public class RangeResultSequence extends ResultSequence {

	private int _start;
	private int _end;
	private int _size;
	private ResultSequence _tail;

	/**
	 * set the start and end of the range result sequence
	 * 
	 * @param start
	 *            is the integer position of the start of range.
	 * @param end
	 *            is the integer position of the end of range.
	 */
	public RangeResultSequence(int start, int end) {
		_size = (end - start) + 1;

		assert _size >= 0;

		_start = start;
		_end = end;

		_tail = ResultSequenceFactory.create_new();
	}

	/**
	 * item is an integer to add to the range.
	 * 
	 * @param item
	 *            is an integer.
	 */
	public void add(AnyType item) {
		_tail.add(item);
	}

	/**
	 * remove the tail from the range given.
	 * 
	 * @param rs
	 *            is the range
	 */
	public void concat(ResultSequence rs) {
		_tail.concat(rs);
	}

	/**
	 * interate through range.
	 * 
	 * @return tail
	 */
	public ListIterator iterator() {
		// XXX life is getting hard...
		if (_size != 0) {
			ResultSequence newtail = ResultSequenceFactory.create_new();

			for (; _start <= _end; _start++)
				newtail.add(new XSInteger(BigInteger.valueOf(_start)));

			newtail.concat(_tail);
			_tail.release();
			_tail = newtail;

			_size = 0;
			_start = 0;
			_end = 0;

		}

		return _tail.iterator();
	}

	/**
	 * @return item from range
	 */
	public AnyType get(int i) {
		if (i < _size)
			return new XSInteger(BigInteger.valueOf(_start + i));
		else
			return _tail.get(i - _size);
	}

	/**
	 * @return size
	 */
	public int size() {
		return _size + _tail.size();
	}

	/**
	 * clear range
	 */
	public void clear() {
		_size = 0;
		_tail.clear();
	}

	/**
	 * create new result sequence
	 * 
	 * @return null
	 */
	public ResultSequence create_new() {
		assert false;
		return null;
	}

	/**
	 * @return first item in range
	 */
	public AnyType first() {
		return get(0);
	}

	/**
	 * @return first item in range
	 */
	public Object firstValue() {
		return get(0).getNativeValue();
	}

	/**
	 * asks if the range is empty?
	 * 
	 * @return boolean
	 */
	public boolean empty() {
		return size() == 0;
	}

	public ItemType sequenceType() {
		return new SimpleAtomicItemTypeImpl(BuiltinTypeLibrary.XS_INTEGER, ItemType.OCCURRENCE_NONE_OR_MANY);
	}

	/**
	 * release
	 */
	public void release() {
	}
}
