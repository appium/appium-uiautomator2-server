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

import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.processor.internal.types.NodeType;
import org.w3c.dom.Node;

/**
 * Create a result sequence that contains the context node
 */
public class SelfAxis extends ForwardAxis {

	/**
	 * create new rs and add the context node to it
	 * 
	 * @param node
	 *            is the node type
	 */
	public void iterate(NodeType node, ResultBuffer copyInto, Node limitNode) {
		copyInto.add(node);
	}
	
	public String name() {
		return "self";
	}

}
