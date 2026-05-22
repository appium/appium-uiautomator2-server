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
 * the following axis contains the context node's following siblings, those
 * children of the context node's parent that occur after the context node in
 * document order.
 */
public class FollowingAxis extends ForwardAxis {

	/**
	 * Return the result of FollowingAxis expression
	 * 
	 * @param node
	 *            is the type of node.
	 */
	public void iterate(NodeType node, ResultBuffer result, Node limitNode) {

		// XXX should be root... not parent!!! read the spec.... BUG BUG
		// BUG LAME LAME....

		if (limitNode != null && limitNode.isSameNode(node.node_value())) {
			// no further, we have reached the limit node
			return;
		}
		
		// get the parent
		NodeType parent = null;
		ResultBuffer parentBuffer = new ResultBuffer();
		new ParentAxis().iterate(node, parentBuffer, limitNode);
		if (parentBuffer.size() == 1)
			parent = (NodeType) parentBuffer.item(0);

		// get the following siblings of this node, and add them
		FollowingSiblingAxis fsa = new FollowingSiblingAxis();
		ResultBuffer siblingBuffer = new ResultBuffer();
		fsa.iterate(node, siblingBuffer, limitNode);

		// for each sibling, get all its descendants
		DescendantAxis da = new DescendantAxis();
		for (Iterator i = siblingBuffer.iterator(); i.hasNext();) {
			result.add((NodeType)i);
			da.iterate((NodeType) i.next(), result, null);
		}

		// if we got a parent, we gotta repeat the story for the parent
		// and add the results
		if (parent != null) {
			iterate(parent, result, limitNode);
		}
	}
	
	public String name() {
		return "following";
	}
}
