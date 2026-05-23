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
package org.eclipse.wst.xml.xpath2.processor.internal.types.builtin;

import java.util.Collection;
import java.util.List;

import javax.xml.namespace.QName;

import org.eclipse.wst.xml.xpath2.api.typesystem.SimpleTypeDefinition;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeDefinition;

public class BuiltinListTypeDefinition extends BuiltinTypeDefinition implements SimpleTypeDefinition {
	
	private final BuiltinAtomicTypeDefinition itemType;

	public BuiltinListTypeDefinition(QName name, BuiltinTypeDefinition baseType, BuiltinAtomicTypeDefinition itemType) {
		super(name, null, Collection.class, baseType);
		this.itemType = itemType;
	}

	public BuiltinListTypeDefinition(String name, BuiltinTypeDefinition baseType, BuiltinAtomicTypeDefinition itemType) {
		super(name, null, Collection.class, baseType);
		this.itemType = itemType;
	}

	public boolean isAbstract() {
		return false;
	}

	public short getVariety() {
		// TODO Auto-generated method stub
		return 0;
	}

	public SimpleTypeDefinition getPrimitiveType() {
		// TODO Auto-generated method stub
		return null;
	}

	public short getBuiltInKind() {
		// TODO Auto-generated method stub
		return 0;
	}

	public TypeDefinition getItemType() {
		return itemType;
	}

	public List getMemberTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	public short getOrdered() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean getFinite() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean getBounded() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean getNumeric() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
