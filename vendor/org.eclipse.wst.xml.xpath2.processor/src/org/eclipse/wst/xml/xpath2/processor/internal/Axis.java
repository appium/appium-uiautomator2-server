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
 * This is the interface class for an Axis.
 * 
 * An axis defines the "direction of movement" for a step between a context node
 * and another node that is reachable via the axis.
 */
public interface Axis {
	/**
	 * Get elements and attributes.
	 * 
	 * @param node
	 *            is the type of node.
	 * @param copyInto TODO
	 * @param limitNode TODO
	 */
	public void iterate(NodeType node, ResultBuffer copyInto, Node limitNode);

	/**
	 * Get the principle kind of node.
	 * 
	 * @return The principle node kind.
	 */
	public NodeType principal_node_kind();
	
	/**
	 * Returns the title of the sequence as it appears in the XPath source
	 * 
	 * @return Axis title
	 */
	public String name();
}
