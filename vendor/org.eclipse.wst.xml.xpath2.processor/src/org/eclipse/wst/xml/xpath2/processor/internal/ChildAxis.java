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
 *     David Carver (STAR) - bug 262765 - Was not handling xml loaded dynamically in variables. 
 *     Jesper Moller - bug 275610 - Avoid big time and memory overhead for externals
 *     Lukasz Wycisk - bug 361803 - NodeType:dom_to_xpath and null value
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal;

import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.processor.internal.types.DocType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.ElementType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.NodeType;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * The child axis contains the children of the context node.
 */
public class ChildAxis extends ForwardAxis {

	/**
	 * Retrieves the context node's children.
	 * 
	 * @param node
	 *            is the type of node.
	 */
	public void iterate(NodeType node, ResultBuffer copyInto, Node limitNode) {
		addChildren(node, copyInto, false);
	}

	protected void addChildren(NodeType node, ResultBuffer copyInto, boolean recurse) {
		NodeList nl = null;
		
		// only document and element nodes have children
		if (node instanceof DocType) {
			nl = ((DocType) node).value().getChildNodes();
		}
		if (node instanceof ElementType)
			nl = ((ElementType) node).value().getChildNodes();

		// add the children to the result
		if (nl != null) {
			for (int i = 0; i < nl.getLength(); i++) {
				Node dnode = nl.item(i);
				NodeType n = NodeType.dom_to_xpath(dnode, node.getTypeModel());
				
				if(n != null) {
					copyInto.add(n);
					
					if (recurse) addChildren(n, copyInto, recurse);
				}
			}
		}
	}
	
	public String name() {
		return "child";
	}
}
