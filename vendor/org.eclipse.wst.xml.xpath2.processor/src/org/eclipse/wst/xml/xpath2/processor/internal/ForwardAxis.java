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

import org.eclipse.wst.xml.xpath2.processor.internal.types.ElementType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.NodeType;

/**
 * An axis that only ever contains the context node or nodes that are after the
 * context node in document order is a forward axis.
 */
public abstract class ForwardAxis implements Axis {

	/**
	 * Return the new Element Type
	 * 
	 * @return The element type.
	 */
	// "default" type is element....
	// remember to override for attribute and namespce axis tho!
	public NodeType principal_node_kind() {
		return new ElementType();
	}
}
