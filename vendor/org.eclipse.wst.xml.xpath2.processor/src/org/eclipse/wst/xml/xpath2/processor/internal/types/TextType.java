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
 *     Jesper Moller- bug 275610 - Avoid big time and memory overhead for externals
 *     David Carver  - bug 281186 - implementation of fn:id and fn:idref
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.types;

import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeDefinition;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeModel;
import org.eclipse.wst.xml.xpath2.processor.internal.types.builtin.BuiltinTypeLibrary;
import org.w3c.dom.Text;

/**
 * A representation of the TextType datatype
 */
public class TextType extends NodeType {
	private static final String TEXT = "text";
	private Text _value;

	/**
	 * Initialises using the supplied parameters
	 * 
	 * @param v
	 *            The value of the TextType node
	 */
	public TextType(Text v, TypeModel tm) {
		super(v, tm);
		_value = v;
	}

	/**
	 * Retrieves the datatype's name
	 * 
	 * @return "text" which is the datatype's name
	 */
	public String string_type() {
		return TEXT;
	}

	/**
	 * Retrieves a String representation of the actual value stored
	 * 
	 * @return String representation of the actual value stored
	 */
	public String getStringValue() {
		return _value.getNodeValue();
	}

	/**
	 * Creates a new ResultSequence consisting of the Text value stored
	 * 
	 * @return New ResultSequence consisting of the Text value stored
	 */
	public ResultSequence typed_value() {
		return new XSUntypedAtomic(_value.getData());
	}

	/**
	 * Unsupported method for this nodetype.
	 * 
	 * @return null (no user defined name for this node gets defined)
	 */
	public QName node_name() {
		return null;
	}

	/**
	 * Will always return false;
	 * @since 1.1
	 */
	public boolean isID() {

		return false;
	}

	/**
	 * 
	 * @since 1.1
	 */
	public boolean isIDREF() {
		return false;
	}
	
	public TypeDefinition getTypeDefinition() {
		return BuiltinTypeLibrary.XS_UNTYPEDATOMIC;
	}
}
