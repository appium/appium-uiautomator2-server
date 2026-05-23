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

package org.eclipse.wst.xml.xpath2.processor.internal;

import java.util.Iterator;

import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.processor.internal.types.NodeType;
import org.w3c.dom.Node;

/**
 * the preceding axis contains all nodes that are descendants of the root of the
 * tree in which the context node is found
 */
public class PrecedingAxis extends ReverseAxis {
	// XXX DOCUMENT ORDER.... dunno

	/**
	 * returns preceding nodes of the context node
	 * 
	 * @param node
	 *            is the node type.
	 * @throws dc
	 *             is the Dynamic context.
	 */
	public void iterate(NodeType node, ResultBuffer result, Node limitNode) {

		if (limitNode != null && limitNode.isSameNode(node.node_value())) {
			// no further, we have reached the limit node
			return;
		}

		// get the parent
		NodeType parent = null;
		ResultBuffer parentBuffer = new ResultBuffer();
		new ParentAxis().iterate(node, parentBuffer, limitNode);
		if (parentBuffer.size() == 1) {
			parent = (NodeType) parentBuffer.item(0);
			// if we got a parent, we gotta repeat the story for the parent
			// and add the results
			iterate(parent, result, limitNode);
		}

		// get the following siblings of this node, and add them
		PrecedingSiblingAxis psa = new PrecedingSiblingAxis();
		ResultBuffer siblingBuffer = new ResultBuffer();
		psa.iterate(node, siblingBuffer, limitNode);

		// for each sibling, get all its descendants
		DescendantAxis da = new DescendantAxis();
		for (Iterator i = siblingBuffer.iterator(); i.hasNext();) {
			result.add((NodeType)i);
			da.iterate((NodeType) i.next(), result, null);
		}
	}
	
	public String name() {
		return "preceding";
	}
}
