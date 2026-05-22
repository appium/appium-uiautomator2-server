/*******************************************************************************
 * Copyright (c) 2011 Jesper Moller, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Jesper Moller - initial API and implementation
 *     Jesper Steen Moller  - bug 340933 - Migrate to new XPath2 API
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.api;

import java.util.Iterator;

import org.eclipse.wst.xml.xpath2.api.typesystem.ItemType;

/**
 * Immutable representation of a result
 * 
 * @since 2.0
 */
public interface ResultSequence {
	/**
	 * Return the size of the result set. Only call this if you need it, since it may require that the entire result
	 * is fetched.
	 * 
	 * @return Count of items.
	 */
	int size();
	
	/**
	 * Return the native representation of the item.
	 * 
	 * @param index
	 * @return Native object representing the item.
	 */
	Object value(int index);

	/**
	 * Return the item.
	 * 
	 * @param index
	 * @return Native object representing the item.
	 */
	Item item(int index);

	/**
	 * Return the native representation of the first item.
	 * 
	 * @return Native object representing the first item.
	 */
	Object firstValue();

	/**
	 * XPath2 type definition description of the item at location '0'
	 * 
	 * @param index
	 * @return
	 */
	ItemType itemType(int index);
	
	/**
	 * Is the sequence empty.
	 * 
	 * @return true for empty sequences
	 */
	boolean empty();

	/**
	 * Iterator of Item elements 
	 * 
	 * @return
	 */
	Iterator<Item> iterator();

	/**
	 * 
	 * 
	 * @return
	 */
	Item first();
	
	/**
	 * Describe the whole sequence's type.
	 * 
	 * @return Item type definition.
	 */
	ItemType sequenceType();
}
