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

package org.eclipse.wst.xml.xpath2.processor.internal;

import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AttrType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.ElementType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.NodeType;
import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * The attribute axis contains the attributes of the context node. The axis will
 * be empty unless the context node is an element.
 */
public class AttributeAxis extends ForwardAxis {

	/**
	 * Retrieves the context node's attributes.
	 * 
	 * @param node
	 *            is the type of node.
	 */
	public void iterate(NodeType node, ResultBuffer copyInto, Node limitNode) {
		// only elements have attributes
		if (!(node instanceof ElementType))
			return;

		// get attributes
		ElementType elem = (ElementType) node;
		NamedNodeMap attrs = elem.value().getAttributes();

		// add attributes
		for (int i = 0; i < attrs.getLength(); i++) {
			Attr attr = (Attr) attrs.item(i);

			copyInto.add(NodeType.dom_to_xpath(attr, node.getTypeModel()));
		}
	}

	/**
	 * Retrieves the node's principle node kind.
	 * 
	 * @return The type of node.
	 */
	public NodeType principal_node_kind() {
		return new AttrType();
	}

	public String name() {
		return "attribute";
	}
}
