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
 *     David Carver - bug 298535 - Attribute instance of improvements 
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.ast;

import org.eclipse.wst.xml.xpath2.api.Item;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeDefinition;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeModel;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.api.StaticContext;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.ElementType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.NodeType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Class for Element testing.
 */
public class ElementTest extends AttrElemTest {
	private boolean _qmark = false;

	private AnyType anyType = null;

	/**
	 * Constructor for ElementTest. This takes in 4 inputs, Name, wildcard
	 * test(true/false), type and question mark test(true/false).
	 * 
	 * @param name
	 *            Name of element to test.
	 * @param wild
	 *            Wildcard test? (true/false).
	 * @param type
	 *            Type of element to test.
	 * @param qmark
	 *            Nilled property (true/false).
	 */
	public ElementTest(QName name, boolean wild, QName type, boolean qmark) {
		super(name, wild, type);
		_qmark = qmark;
	}

	/**
	 * Constructor for ElementTest. This takes in 3 inputs, Name, wildcard
	 * test(true/false)and type.
	 * 
	 * @param name
	 *            Name of element to test.
	 * @param wild
	 *            Wildcard test? (true/false).
	 * @param type
	 *            Type of element to test.
	 */
	public ElementTest(QName name, boolean wild, QName type) {
		super(name, wild, type);
	}

	/**
	 * Constructor for ElementTest. This takes in 2 inputs, Name, wildcard
	 * test(true/false).
	 * 
	 * @param name
	 *            Name of element to test.
	 * @param wild
	 *            Wildcard test? (true/false).
	 */
	public ElementTest(QName name, boolean wild) {
		super(name, wild);
	}

	/**
	 * Default Constructor for ElementTest.
	 */
	public ElementTest() {
		super();
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
	 * Set nilled property.
	 * 
	 * @return Result of operation.
	 */
	public boolean qmark() {
		return _qmark;
	}

	public AnyType createTestType(ResultSequence rs, StaticContext sc) {

		if (name() == null && !wild()) {
			return new ElementType();
		}

		Item at = rs.first();

		if (!(at instanceof NodeType)) {
			return new ElementType();
		}

		return createElementType(at, sc);
	}

	private AnyType createElementType(Item at, StaticContext sc) {
		anyType = new ElementType();
		NodeType nodeType = (NodeType) at;
		Node node = nodeType.node_value();
		Document doc = null;
		if (node.getNodeType() == Node.DOCUMENT_NODE) {
			doc = (Document) node;
		} else {
			doc = nodeType.node_value().getOwnerDocument();
		}
		
		NodeList nodeList = null;		
		if (!wild()) {
			nodeList = doc.getElementsByTagNameNS(name().namespace(),
					name().local());
		} else {
			nodeList = new SingleItemNodeListImpl(node);
		}

		if (nodeList.getLength() > 0) {
			anyType = createElementForXSDType(nodeList, sc);
		}
		return anyType;
	}

	private AnyType createElementForXSDType(NodeList nodeList, StaticContext sc) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element element = (Element) nodeList.item(i);
			
			TypeModel typeModel = sc.getTypeModel();
			TypeDefinition typedef = typeModel.getType(element);
			if (type() == null || typedef == null) {
				anyType = new ElementType(element, typeModel);
				break;
			} else {
				if (typedef.derivedFrom(type().namespace(), type().local(),
						getDerviationTypes())) {
					anyType = new ElementType(element, typeModel);
					break;
				}
			}
		}
		return anyType;
	}

	public boolean isWild() {
		return wild();
	}

	public Class getXDMClassType() {
		return ElementType.class;
	}
	
	private static class SingleItemNodeListImpl implements NodeList {
		private Node node;
		public SingleItemNodeListImpl(Node node) {
			this.node = node;
		}

		public Node item(int index) {
			return node;
		}

		public int getLength() {
			if (node != null) {
				return 1;
			} else {
				return 0;
			}
		}
		
	}

}
