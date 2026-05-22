/*******************************************************************************
 * Copyright (c) 2011, 2018 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xml.xpath2.processor.internal.types;

import javax.xml.namespace.QName;

import org.eclipse.wst.xml.xpath2.api.typesystem.NodeItemType;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeDefinition;

public class NodeItemTypeImpl implements NodeItemType {

	public NodeItemTypeImpl(short occurrence, short nodeType) {
		this(occurrence, nodeType, null, null, true);
	}

	public NodeItemTypeImpl(short nodeType) {
		this(OCCURRENCE_ONE, nodeType, null, null, true);
	}

	public NodeItemTypeImpl(short occurrence,
			short nodeType,
			TypeDefinition typeDefinition, 
			QName name,
			boolean wildcard) {
		this.occurrence = occurrence;
		this.typeDefinition = typeDefinition;
		this.nodeType = nodeType;
		this.name = name;
		this.wildcard = wildcard;
	}

	private final short occurrence;
	private final TypeDefinition typeDefinition;
	private final short nodeType;
	private final QName name;
	private final boolean wildcard;

	public short getOccurrence() {
		return occurrence;
	}

	public TypeDefinition getTypeDefinition() {
		return typeDefinition;
	}

	public boolean isWildcard() {
		return wildcard;
	}

	public QName getName() {
		return name;
	}

	public short getNodeType() {
		return nodeType;
	}

}
