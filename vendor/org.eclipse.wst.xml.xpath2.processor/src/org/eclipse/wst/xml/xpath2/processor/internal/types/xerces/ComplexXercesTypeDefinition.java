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

import org.apache.xerces.xs.XSComplexTypeDefinition;
import org.apache.xerces.xs.XSSimpleTypeDefinition;
import org.eclipse.wst.xml.xpath2.api.typesystem.ComplexTypeDefinition;
import org.eclipse.wst.xml.xpath2.api.typesystem.SimpleTypeDefinition;
import org.w3c.dom.NodeList;

public class ComplexXercesTypeDefinition extends XercesTypeDefinition implements ComplexTypeDefinition {

	private final XSComplexTypeDefinition complexTypeDefinition;

	public ComplexXercesTypeDefinition(XSComplexTypeDefinition ad) {
		super(ad);
		this.complexTypeDefinition = ad;
	}

	public SimpleTypeDefinition getSimpleType() {
		XSSimpleTypeDefinition simpleType = complexTypeDefinition.getSimpleType();
		if (simpleType != null) {
			return createTypeDefinition(simpleType);
		} else return null;
	}

	public short getDerivationMethod() {
		// TODO: Map it
		return complexTypeDefinition.getDerivationMethod();
	}

	public boolean getAbstract() {
		return complexTypeDefinition.getAbstract();
	}

	public short getContentType() {
		return complexTypeDefinition.getContentType();
	}

	public boolean isProhibitedSubstitution(short restriction) {
		return complexTypeDefinition.isProhibitedSubstitution(restriction);
	}

	public short getProhibitedSubstitutions() {
		return complexTypeDefinition.getProhibitedSubstitutions();
	}

	public Class getNativeType() {
		return NodeList.class;
	}

}
