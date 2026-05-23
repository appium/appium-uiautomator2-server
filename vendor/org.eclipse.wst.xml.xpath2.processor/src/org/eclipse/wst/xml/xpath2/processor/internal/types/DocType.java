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
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.types;

import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeDefinition;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeModel;
import org.eclipse.wst.xml.xpath2.processor.internal.types.builtin.BuiltinTypeLibrary;
import org.w3c.dom.Document;

/**
 * A representation of the DocumentType datatype
 */
public class DocType extends NodeType {
	private static final String DOCUMENT = "document";
	private Document _value;
	private String _string_value;

	/**
	 * Initialises according to the supplied parameters
	 * 
	 * @param v
	 *            The document being represented
	 */
	public DocType(Document v, TypeModel tm) {
		super(v, tm);
		_value = v;
		_string_value = null;
	}

	/**
	 * Retrieves the actual document being represented
	 * 
	 * @return Actual document being represented
	 */
	public Document value() {
		return _value;
	}

	/**
	 * Retrieves the datatype's full pathname
	 * 
	 * @return "document" which is the datatype's full pathname
	 */
	public String string_type() {
		return DOCUMENT;
	}

	/**
	 * Retrieves a String representation of the document being stored
	 * 
	 * @return String representation of the document being stored
	 */
	public String getStringValue() {
		// XXX caching
		if (_string_value == null)
			_string_value = ElementType.textnode_strings(_value);

		return _string_value;
	}

	/**
	 * Creates a new ResultSequence consisting of the document being stored
	 * 
	 * @return New ResultSequence consisting of the document being stored
	 */
	public ResultSequence typed_value() {
		// XXX no psvi
		return new XSUntypedAtomic(getStringValue());
	}

	/**
	 * Retrieves the name of the node
	 * 
	 * @return QName representation of the name of the node
	 */
	public QName node_name() {
		return null;
	}

	/**
	 * @since 1.1
	 */
	public boolean isID() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @since 1.1
	 */
	public boolean isIDREF() {
		return false;
	}
	
	public TypeDefinition getTypeDefinition() {
		return BuiltinTypeLibrary.XS_UNTYPED;
	}

}
