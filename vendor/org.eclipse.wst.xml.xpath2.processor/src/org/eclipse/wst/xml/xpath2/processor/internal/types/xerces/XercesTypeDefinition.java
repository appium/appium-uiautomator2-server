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

import org.apache.xerces.dom.PSVIAttrNSImpl;
import org.apache.xerces.dom.PSVIElementNSImpl;
import org.apache.xerces.impl.dv.XSSimpleType;
import org.apache.xerces.xs.ShortList;
import org.apache.xerces.xs.XSComplexTypeDefinition;
import org.apache.xerces.xs.XSConstants;
import org.apache.xerces.xs.XSSimpleTypeDefinition;
import org.apache.xerces.xs.XSTypeDefinition;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeDefinition;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;

public abstract class XercesTypeDefinition implements TypeDefinition {

	private final XSTypeDefinition xsTypeDefinition;
	private XercesTypeDefinition baseType = null;
	
	public XercesTypeDefinition(XSTypeDefinition typeDef) {
		if (typeDef == null) throw new IllegalArgumentException("typeDef");
		xsTypeDefinition = typeDef;
	}

	public String getNamespace() {
		return xsTypeDefinition.getNamespace();
	}

	public String getName() {
		return xsTypeDefinition.getName();
	}

	public boolean isComplexType() {
		return (xsTypeDefinition.getTypeCategory() & XSConstants.PARTICLE) != 0;
	}

	public TypeDefinition getBaseType() {
		// TODO: Cache per-model??
		if (baseType == null && xsTypeDefinition.getBaseType() != null)
			baseType = createTypeDefinition(xsTypeDefinition.getBaseType());
		return baseType;
	}

	public boolean derivedFromType(TypeDefinition ancestorType,
			short derivationMethod) {

		if (ancestorType instanceof XercesTypeDefinition) {
			XercesTypeDefinition xercesType = (XercesTypeDefinition)ancestorType;
			return xsTypeDefinition.derivedFromType(xercesType.xsTypeDefinition, mapFlags(derivationMethod));
		} else {
			return xsTypeDefinition.derivedFrom(ancestorType.getNamespace(), ancestorType.getName(), mapFlags(derivationMethod));
		}
	}

	public boolean derivedFrom(String namespace, String name,
			short derivationMethod) {

		return xsTypeDefinition.derivedFrom(namespace, name, mapFlags(derivationMethod));
	}

	private static short mapFlags(short modelFlags) {
		short xercesFlags = 0;
		if ((modelFlags & TypeDefinition.DERIVATION_NONE) != 0) xercesFlags |= XSConstants.DERIVATION_NONE;
		if ((modelFlags & TypeDefinition.DERIVATION_EXTENSION) != 0) xercesFlags |= XSConstants.DERIVATION_EXTENSION; 
		if ((modelFlags & TypeDefinition.DERIVATION_RESTRICTION) != 0) xercesFlags |= XSConstants.DERIVATION_RESTRICTION;
		if ((modelFlags & TypeDefinition.DERIVATION_SUBSTITUTION) != 0) xercesFlags |= XSConstants.DERIVATION_SUBSTITUTION;
		if ((modelFlags & TypeDefinition.DERIVATION_UNION) != 0) xercesFlags |= XSConstants.DERIVATION_UNION;    
		if ((modelFlags & TypeDefinition.DERIVATION_LIST) != 0) xercesFlags |= XSConstants.DERIVATION_LIST;

		return xercesFlags;
	}
	
	public List/*<Short>*/ getSimpleTypes(Attr attr) {
		PSVIAttrNSImpl psviAttr= (PSVIAttrNSImpl)attr;
		return mapList(psviAttr.getItemValueTypes());
	}

	public List/*<Short>*/ getSimpleTypes(Element element) {
		PSVIElementNSImpl psviElement= (PSVIElementNSImpl)element;
		return mapList(psviElement.getItemValueTypes());
	}

	private List/*<Short>*/ mapList(ShortList valueTypes) {
		if (valueTypes == null) return null;
		List/*<Short>*/ types = new LinkedList/*<Short>*/();
		int limit = valueTypes.getLength();
		for (int i = 0; i < limit; ++i) types.add(Short.valueOf(valueTypes.item(i)));
		return types;
	}
	
	public static XercesTypeDefinition createTypeDefinition(XSTypeDefinition ad) {
		if (ad instanceof XSSimpleType) return new SimpleXercesType((XSSimpleType)ad);
		else if (ad instanceof XSSimpleTypeDefinition) return new SimpleXercesTypeDefinition((XSSimpleTypeDefinition)ad);
		else return new ComplexXercesTypeDefinition((XSComplexTypeDefinition)ad);
	}

	public static SimpleXercesTypeDefinition createTypeDefinition(XSSimpleTypeDefinition ad) {
		if (ad instanceof XSSimpleType) return new SimpleXercesType((XSSimpleType)ad);
		return new SimpleXercesTypeDefinition((XSSimpleTypeDefinition)ad);
	}

	public static ComplexXercesTypeDefinition createTypeDefinition(XSComplexTypeDefinition ad) {
		return new ComplexXercesTypeDefinition((XSComplexTypeDefinition)ad);
	}

}
