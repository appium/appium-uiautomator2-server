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

import org.apache.xerces.xs.ElementPSVI;
import org.apache.xerces.xs.ItemPSVI;
import org.apache.xerces.xs.XSAttributeDeclaration;
import org.apache.xerces.xs.XSElementDeclaration;
import org.apache.xerces.xs.XSModel;
import org.apache.xerces.xs.XSTypeDefinition;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeDefinition;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeModel;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class XercesTypeModel implements TypeModel {
	/**
	 * 
	 */
	private XSModel _schema;

	public XercesTypeModel(Document doc) {
		_schema = ((ElementPSVI) doc.getDocumentElement()).getSchemaInformation();
	}

	public XercesTypeModel(XSModel model) {
		_schema = model;
	}


	public TypeDefinition lookupType(String namespace, String typeName) {
		XSTypeDefinition ad = _schema.getTypeDefinition(typeName, namespace);

		return XercesTypeDefinition.createTypeDefinition(ad);
	}

	public TypeDefinition lookupElementDeclaration(String namespace, String elementName) {
		XSElementDeclaration ad = _schema.getElementDeclaration(elementName, namespace);

		return XercesTypeDefinition.createTypeDefinition(ad.getTypeDefinition());
	}

	public TypeDefinition lookupAttributeDeclaration(String namespace, String attributeName) {
		XSAttributeDeclaration ad = _schema.getAttributeDeclaration(attributeName, namespace);

		return XercesTypeDefinition.createTypeDefinition(ad.getTypeDefinition());
	}

	public TypeDefinition getType(Node node) {
		if (node instanceof ItemPSVI) {
			XSTypeDefinition typeDefinition = ((ItemPSVI)node).getTypeDefinition();
			if (typeDefinition != null)
				return XercesTypeDefinition.createTypeDefinition(typeDefinition);
		}
		return null;
	}
}