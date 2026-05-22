/*******************************************************************************
 * Copyright (c) 2011 Jesper Moller, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Jesper Moller - initial API and implementation
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.api.typesystem;

import org.w3c.dom.Node;

/**
 * @since 2.0
 */
public interface TypeModel {
	TypeDefinition getType(Node node);
	TypeDefinition lookupType(String namespace, String typeName);
	TypeDefinition lookupElementDeclaration(String namespace, String elementName);
	TypeDefinition lookupAttributeDeclaration(String namespace,
			String attributeName);
	
	/**
	 * @param at
	 *            the node type
	 * @param et
	 *            is the qname
	 * @return boolean
	 */
//	public boolean derivesFrom(Node at, QName et);

	/**
	 * @param at
	 *            the node type
	 * @param et
	 *            is the XSTypeDefinition of the node
	 * @return boolean
	 */
//	public boolean derivesFrom(Node at, TypeDefinition et);


}
