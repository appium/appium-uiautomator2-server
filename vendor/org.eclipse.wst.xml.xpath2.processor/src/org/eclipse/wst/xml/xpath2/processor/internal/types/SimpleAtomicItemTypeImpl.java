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

import org.eclipse.wst.xml.xpath2.api.AtomicItemType;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeDefinition;

public class SimpleAtomicItemTypeImpl implements AtomicItemType {

	private final short occurrence;
	private final TypeDefinition typeDefinition;

	public SimpleAtomicItemTypeImpl(TypeDefinition typeDefinition) {
		super();
		this.typeDefinition = typeDefinition;
		this.occurrence = OCCURRENCE_ONE;
	}

	public SimpleAtomicItemTypeImpl(TypeDefinition typeDefinition, short occurrence) {
		super();
		this.typeDefinition = typeDefinition;
		this.occurrence = occurrence;
	}

	public short getOccurrence() {
		return this.occurrence;
	}

	public TypeDefinition getTypeDefinition() {
		return typeDefinition;
	}

}
