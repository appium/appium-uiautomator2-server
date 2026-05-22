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
package org.eclipse.wst.xml.xpath2.processor.internal.types.xerces;

import java.util.LinkedList;
import java.util.List;

import org.apache.xerces.xs.XSObjectList;
import org.apache.xerces.xs.XSSimpleTypeDefinition;
import org.eclipse.wst.xml.xpath2.api.typesystem.SimpleTypeDefinition;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeDefinition;
import org.w3c.dom.Node;

public class SimpleXercesTypeDefinition extends XercesTypeDefinition implements SimpleTypeDefinition {

	private final XSSimpleTypeDefinition simpleTypeDefinition;

	public SimpleXercesTypeDefinition(XSSimpleTypeDefinition ad) {
		super(ad);
		this.simpleTypeDefinition = ad;
	}

	public short getVariety() {
		return simpleTypeDefinition.getVariety();
	}

	public SimpleTypeDefinition getPrimitiveType() {
		return createTypeDefinition(simpleTypeDefinition.getPrimitiveType());
	}

	public short getBuiltInKind() {
		return simpleTypeDefinition.getBuiltInKind();
	}

	public TypeDefinition getItemType() {
		return createTypeDefinition(simpleTypeDefinition.getItemType());
	}

	public List/*<SimpleTypeDefinition>*/ getMemberTypes() {
		XSObjectList xsMemberTypes = simpleTypeDefinition.getMemberTypes();
		List/*<SimpleTypeDefinition>*/ memberTypes = new LinkedList/*<SimpleTypeDefinition>*/();
		for (int i = 0; i < xsMemberTypes.getLength(); i++) {
			memberTypes.add(createTypeDefinition((XSSimpleTypeDefinition) xsMemberTypes.item(i)));
		}
		return memberTypes;
	}

	public short getOrdered() {
		return simpleTypeDefinition.getOrdered();
	}

	public boolean getFinite() {
		return simpleTypeDefinition.getFinite();
	}

	public boolean getBounded() {
		return simpleTypeDefinition.getBounded();
	}

	public boolean getNumeric() {
		return simpleTypeDefinition.getNumeric();
	}

	public Class getNativeType() {
		return Node.class;
	}

}
