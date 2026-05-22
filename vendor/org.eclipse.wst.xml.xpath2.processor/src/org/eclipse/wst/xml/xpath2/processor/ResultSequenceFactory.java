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

package org.eclipse.wst.xml.xpath2.processor;

import org.eclipse.wst.xml.xpath2.processor.internal.DefaultRSFactory;
import org.eclipse.wst.xml.xpath2.processor.internal.types.*;

/**
 * Result sequence factory
 */
public abstract class ResultSequenceFactory {
	private static final ResultSequenceFactory _factory = new DefaultRSFactory();

	protected abstract ResultSequence fact_create_new();

	protected abstract void fact_release(ResultSequence rs);

	protected ResultSequence fact_create_new(AnyType item) {
		ResultSequence rs = fact_create_new();
		rs.add(item);
		return rs;
	}

	protected void fact_print_debug() {
	}

	/**
	 * @return the creation of a new result sequence
	 */
	public static ResultSequence create_new() {
		return _factory.fact_create_new();
	}

	/**
	 * @param item
	 *            is an item of any type.
	 * @return factory creating new item
	 */
	public static ResultSequence create_new(AnyType item) {
		return _factory.fact_create_new(item);
	}

	/**
	 * @param rs
	 *            is the result sequence factory release rs
	 */
	public static void release(ResultSequence rs) {
		_factory.fact_release(rs);
	}

	/**
	 * factory debug
	 */
	public static void print_debug() {
		_factory.fact_print_debug();
	}
}
