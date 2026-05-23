/*******************************************************************************
 * Copyright (c) 2009, 2011 Standards for Technology in Automotive Retail and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     David Carver (STAR) bug 228223 - initial API and implementation
 *     Mukul Gandhi - bug 334842 - improving support for the data types Name, NCName, ENTITY, 
 *                                 ID, IDREF and NMTOKEN.
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.types;

import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeDefinition;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.types.builtin.BuiltinTypeLibrary;

/**
 * Built in Data Type for xs:Entity
 * 
 * @author dcarver
 * @since 1.1
 */
public class XSEntity extends XSNCName {
	
	private static final String XS_ENTITY = "xs:ENTITY";

	public XSEntity() {
		super();
	}
	
	public XSEntity(String value) {
		super(value);
	}
	
	public String string_type() {
		return XS_ENTITY;
	}
	
	public String type_name() {
		return "ENTITY";
	}

	/**
	 * Creates a new ResultSequence consisting of the ENTITY within
	 * the supplied ResultSequence.  The specification says that this
	 * is relaxed from the XML Schema requirement.  The ENTITY does
	 * not have to be located or expanded during construction and
	 * evaluation for casting.
	 * 
	 * @param arg
	 *            The ResultSequence from which to extract the ENTITY
	 * @return New ResultSequence consisting of the ENTITY supplied
	 * @throws DynamicError
	 */
	public ResultSequence constructor(ResultSequence arg) throws DynamicError {
		if (arg.empty())
			return ResultBuffer.EMPTY;

		AnyAtomicType aat = (AnyAtomicType) arg.first();
		String strValue = aat.getStringValue();
		
		if (!isConstraintSatisfied(strValue)) {
			// invalid input
			DynamicError.throw_type_error();
		}

		return new XSEntity(strValue);
	}
	

	public TypeDefinition getTypeDefinition() {
		return BuiltinTypeLibrary.XS_ENTITY;
	}

}
