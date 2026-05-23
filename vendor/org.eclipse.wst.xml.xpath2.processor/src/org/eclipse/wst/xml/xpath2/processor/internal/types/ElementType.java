/*******************************************************************************
 * Copyright (c) 2005, 2012 Andrea Bittau, University College London, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Andrea Bittau - initial API and implementation from the PsychoPath XPath 2.0
 *     Mukul Gandhi - bug 276134 - improvements to schema aware primitive type support
 *                                  for attribute/element nodes
 *     Jesper Moller - bug 275610 - Avoid big time and memory overhead for externals
 *     David Carver  - bug 281186 - implementation of fn:id and fn:idref
 *     David Carver (STAR) - bug 289304 - fix schema awarness of types on elements
 *     Jesper Moller - bug 297958 - Fix fn:nilled for elements
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *     Mukul Gandhi - bug 323900 - improving computing the typed value of element &
 *                                 attribute nodes, where the schema type of nodes
 *                                 are simple, with varieties 'list' and 'union'.
 *     Lukasz Wycisk - bug 361659 - ElemntType typed value in case of nil=�true�                              
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.types;

import org.apache.xerces.dom.PSVIElementNSImpl;
import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeDefinition;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeModel;
import org.eclipse.wst.xml.xpath2.processor.internal.types.builtin.BuiltinTypeLibrary;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.TypeInfo;

/**
 * A representation of the ElementType datatype
 */
public class ElementType extends NodeType {
	private static final String ELEMENT = "element";

	private static final String SCHEMA_INSTANCE = "http://www.w3.org/2001/XMLSchema-instance";
	private static final String NIL_ATTRIBUTE = "nil";
	private static final String TRUE_VALUE = "true";

	private Element _value;

	private String _string_value;

	/**
	 * Initialises to a null element
	 */
	public ElementType() {
		this(null, null);
	}

	/**
	 * Initialises according to the supplied parameters
	 * 
	 * @param v
	 *            The element being represented
	 */
	public ElementType(Element v, TypeModel tm) {
		super(v, tm);
		_value = v;

		_string_value = null;
	}

	/**
	 * Retrieves the actual element value being represented
	 * 
	 * @return Actual element value being represented
	 */
	public Element value() {
		return _value;
	}

	/**
	 * Retrieves the datatype's full pathname
	 * 
	 * @return "element" which is the datatype's full pathname
	 */
	public String string_type() {
		return ELEMENT;
	}

	/**
	 * Retrieves a String representation of the element being stored
	 * 
	 * @return String representation of the element being stored
	 */
	public String getStringValue() {
		// XXX can we cache ?
		if (_string_value != null)
			return _string_value;

		_string_value = textnode_strings(_value);

		return _string_value;
	}

	/**
	 * Creates a new ResultSequence consisting of the element stored
	 * 
	 * @return New ResultSequence consisting of the element stored
	 */
	public ResultSequence typed_value() {
		
		TypeDefinition typeDef = getType();

		if (!isNilled(_value)) {
			if (typeDef != null) {
				return getXDMTypedValue(typeDef, typeDef.getSimpleTypes(_value));
			}
			else {
				return new XSUntypedAtomic(getStringValue());
			}
		}
		return ResultBuffer.EMPTY;
	}

	private boolean isNilled(Element _value2) {
		return TRUE_VALUE.equals(_value2.getAttributeNS(SCHEMA_INSTANCE, NIL_ATTRIBUTE));
	}

	// recursively concatenate TextNode strings
	/**
	 * Recursively concatenate TextNode strings
	 * 
	 * @param node
	 *            Node to recurse
	 * @return String representation of the node supplied
	 */
	public static String textnode_strings(Node node) {
		String result = "";

		if (node.getNodeType() == Node.TEXT_NODE) {
			Text tn = (Text) node;
			result += tn.getData();
		}

		NodeList nl = node.getChildNodes();

		StringBuffer buf = new StringBuffer(result);
		// concatenate children
		for (int i = 0; i < nl.getLength(); i++) {
			Node n = nl.item(i);

			buf.append(textnode_strings(n));
		}

		result = buf.toString();
		return result;
	}

	/**
	 * Retrieves the name of the node
	 * 
	 * @return QName representation of the name of the node
	 */
	public QName node_name() {
		QName name = new QName(_value.getPrefix(), _value.getLocalName(), _value.getNamespaceURI());

		return name;
	}

	public ResultSequence nilled() {

		if (_value instanceof PSVIElementNSImpl) {
			PSVIElementNSImpl psviElement = (PSVIElementNSImpl) _value;
			return XSBoolean.valueOf(psviElement.getNil());
		}
		else {
			return XSBoolean.FALSE;
		}
	}

	/**
	 * @since 1.1
	 */
	public boolean isID() {
		return isElementType(SCHEMA_TYPE_ID);
	}

	/**
	 * @since 1.1
	 */
	public boolean isIDREF() {
		return isElementType(SCHEMA_TYPE_IDREF);
	}

	protected boolean isElementType(String typeName) {
		TypeInfo typeInfo = _value.getSchemaTypeInfo();
		return isType(typeInfo, typeName);
	}
	
	public TypeDefinition getTypeDefinition() {
		return BuiltinTypeLibrary.XS_UNTYPED;
	}
}
