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
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor;

import java.util.Iterator;
import java.util.ListIterator;

import org.eclipse.wst.xml.xpath2.api.Item;
import org.eclipse.wst.xml.xpath2.api.typesystem.ItemType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.NodeType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;

/**
 * Interface to the methods of range of result sequence
 * @deprecated Use org.eclipse.wst.xml.xpath2.api.ResultSequence instead
 */
public abstract class ResultSequence implements org.eclipse.wst.xml.xpath2.api.ResultSequence {

	/**
	 * add item
	 * 
	 * @param item
	 *            is an item of any type.
	 */
	public abstract void add(AnyType item);

	/**
	 * concatinate from rs
	 * 
	 * @param rs
	 *            is a Result Sequence.
	 */
	public abstract void concat(ResultSequence rs);

	/**
	 * List Iterator.
	 */
	public abstract ListIterator iterator();

	/**
	 * get item in index i
	 * 
	 * @param i
	 *            is the position.
	 */
	public abstract AnyType get(int i);

	/**
	 * get the size
	 * 
	 * @return the size.
	 */
	public abstract int size();

	/**
	 * clear
	 */
	public abstract void clear();

	/**
	 * create a new result sequence
	 * 
	 * @return a new result sequence.
	 */
	public abstract ResultSequence create_new();

	/**
	 * retrieve the first item
	 * 
	 * @return the first item.
	 */
	public AnyType first() {
		return get(0);
	}

	/**
	 * check is the sequence is empty
	 * 
	 * @return boolean.
	 */
	public boolean empty() {
		if (size() == 0)
			return true;
		return false;
	}

	/**
	 * retrieve items in sequence
	 * 
	 * @return result string
	 */
	public String string() {
		String result = "";
		int num = 1;

		StringBuffer buf = new StringBuffer();
		for (Iterator i = iterator(); i.hasNext();) {
			AnyType elem = (AnyType) i.next();

			buf.append(num + ") ");

			buf.append(elem.string_type() + ": ");

			String value = elem.getStringValue();

			if (elem instanceof NodeType) {
				QName tmp = ((NodeType) elem).node_name();

				if (tmp != null)
					value = tmp.expanded_name();
			}
			buf.append(value + "\n");

			num++;
		}
		result = buf.toString();
		if (num == 1)
			result = "Empty results\n";
		return result;
	}

	/**
	 * release the result sequence
	 */
	public void release() {
		ResultSequenceFactory.release(this);
	}
	
	/**
	 * @since 2.0
	 */
	public Item item(int index) {
		return get(index);
	}
	
	/**
	 * @since 2.0
	 */
	public ItemType itemType(int index) {
		return get(index).getItemType();
	}
	
	/**
	 * @since 2.0
	 */
	public Object value(int index) {
		return get(index).getNativeValue();
	}
}
