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
 *     Jesper Moller - bug 275610 - Avoid big time and memory overhead for externals
 *     David Carver  - bug 281186 - implementation of fn:id and fn:idref
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.types;

import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeDefinition;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeModel;
import org.eclipse.wst.xml.xpath2.processor.internal.types.builtin.BuiltinTypeLibrary;
import org.w3c.dom.Comment;

/**
 * A representation of the CommentType datatype
 */
public class CommentType extends NodeType {
	private static final String COMMENT = "comment";
	private Comment _value;

	/**
	 * Initialise according to the supplied parameters
	 * 
	 * @param v
	 *            The comment being represented
	 */
	public CommentType(Comment v, TypeModel tm) {
		super(v, tm);
		_value = v;
	}

	/**
	 * Retrieves the datatype's full pathname
	 * 
	 * @return "comment" which is the datatype's full pathname
	 */
	public String string_type() {
		return COMMENT;
	}

	/**
	 * Retrieves a String representation of the comment being stored
	 * 
	 * @return String representation of the comment being stored
	 */
	public String getStringValue() {
		return _value.getNodeValue();
	}

	/**
	 * Creates a new ResultSequence consisting of the comment stored
	 * 
	 * @return New ResultSequence consisting of the comment stored
	 */
	public ResultSequence typed_value() {
		return new XSString(_value.getData());
	}

	/**
	 * Unsupported method for this node.
	 * 
	 * @return null
	 */
	public QName node_name() {
		return null;
	}

	/**
	 * @since 1.1
	 */
	public boolean isID() {
		return false;
	}

	/**
	 * @since 1.1
	 */
	public boolean isIDREF() {
		return false;
	}
	
	public TypeDefinition getTypeDefinition() {
		return BuiltinTypeLibrary.XS_UNTYPEDATOMIC;
	}
}
