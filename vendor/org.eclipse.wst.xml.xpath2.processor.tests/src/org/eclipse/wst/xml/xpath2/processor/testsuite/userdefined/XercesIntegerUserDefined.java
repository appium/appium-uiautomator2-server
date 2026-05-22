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

import java.math.BigInteger;

import org.apache.xerces.xs.XSObject;
import org.apache.xerces.xs.XSSimpleTypeDefinition;
import org.eclipse.wst.xml.xpath2.api.Item;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.ResultSequenceFactory;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSInteger;

public class XercesIntegerUserDefined extends XSInteger {

	private XSObject typeInfo;
	
	public XercesIntegerUserDefined(BigInteger x) {
		super(x);
	}
	
	public XercesIntegerUserDefined(XSObject typeInfo) {
		this.typeInfo = typeInfo;
	}
	
	public ResultSequence constructor(ResultSequence arg) throws DynamicError {
			ResultSequence rs = ResultSequenceFactory.create_new();

			if (arg.empty())
				return rs;


			//AnyAtomicType aat = (AnyAtomicType) arg.first();
			Item aat = arg.first();
			
			XSSimpleTypeDefinition simpletype = (XSSimpleTypeDefinition) typeInfo;
			if (simpletype != null) {
				if (simpletype.isDefinedFacet(XSSimpleTypeDefinition.FACET_MININCLUSIVE)) {
					String minValue = simpletype.getLexicalFacetValue(XSSimpleTypeDefinition.FACET_MININCLUSIVE);
					int iminValue = Integer.parseInt(minValue);
					int actualValue = Integer.parseInt(aat.getStringValue());
	
					if (actualValue < iminValue) {
						throw DynamicError.invalidForCastConstructor();
					}
				}
	
				if (simpletype.isDefinedFacet(XSSimpleTypeDefinition.FACET_MAXINCLUSIVE)) {
					String maxValue = simpletype.getLexicalFacetValue(XSSimpleTypeDefinition.FACET_MAXINCLUSIVE);
					int imaxValue = Integer.parseInt(maxValue);
					int actualValue = Integer.parseInt(aat.getStringValue());
					if (actualValue > imaxValue) {
						throw DynamicError.invalidForCastConstructor();
					}
				}
			}
			
			return new XercesIntegerUserDefined(new BigInteger(aat.getStringValue()));
	}	

	public String type_name() {
		return typeInfo.getName();
	}
	
}
