/*******************************************************************************
 * Copyright (c) 2009, 2017 Standards for Technology in Automotive Retail and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     David Carver (STAR) - initial API and implementation 
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/
package org.eclipse.wst.xml.xpath2.processor.testsuite.userdefined;

import org.apache.xerces.xs.XSTypeDefinition;
import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeDefinition;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyAtomicType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.CtrType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSString;
import org.eclipse.wst.xml.xpath2.processor.internal.types.xerces.XercesTypeDefinition;

public class XercesUserDefined extends CtrType {

	private XSTypeDefinition typeInfo;
	private String value;
	
	public XercesUserDefined(XSTypeDefinition typeInfo) {
		this.typeInfo = typeInfo;
	}
	
	public org.eclipse.wst.xml.xpath2.api.ResultSequence constructor(org.eclipse.wst.xml.xpath2.api.ResultSequence arg) throws DynamicError {
			if (arg.empty())
				return ResultBuffer.EMPTY;

			AnyAtomicType aat = (AnyAtomicType) arg.first();

			return new XSString(aat.string_value());
	}

	public String string_type() {
		return null;
	}

	public String getStringValue() {
		return value;
	}
	
	public String type_name() {
		return typeInfo.getName();
	}

	public TypeDefinition getTypeDefinition() {
		return XercesTypeDefinition.createTypeDefinition((XSTypeDefinition)typeInfo);
	}
}
