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
 *     Lukasz Wycisk - bug 361803 - NodeType:dom_to_xpath and null value
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal;

import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.processor.internal.types.NodeType;
import org.w3c.dom.Node;

/**
 * The following-sibling axis contains the context node's following siblings,
 * those children of the context node's parent that occur after the context node
 * in document order; if the context node is an attribute nodeor namespace node,
 * the following-sibling axis is empty.
 */
public class FollowingSiblingAxis extends ForwardAxis {

	/**
	 * Return the result of FollowingSiblingAxis expression
	 * 
	 * @param node
	 *            is the type of node.
	 */
	public void iterate(NodeType node, ResultBuffer copyInto, Node limitNode) {
		// XXX check for attribute / namespace node... if so return
		// empty sequence

		Node iterNode = node.node_value();
		// get the children of the parent [siblings]
		do {
			iterNode = iterNode.getNextSibling();
			if (iterNode != null) {
				NodeType nodeType = NodeType.dom_to_xpath(iterNode, node.getTypeModel());
				if(nodeType != null) {
					copyInto.add(nodeType);
				}
			}
		} while (iterNode != null);
	}
	
	public String name() {
		return "following-sibling";
	}
}
