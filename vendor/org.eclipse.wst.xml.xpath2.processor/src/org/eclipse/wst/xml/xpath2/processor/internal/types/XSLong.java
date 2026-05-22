/*******************************************************************************
 * Copyright (c) 2009, 2011 Mukul Gandhi, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Mukul Gandhi - bug 274952 - Initial API and implementation, of xs:long data 
 *                                 type.
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.types;

import java.math.BigInteger;

import org.eclipse.wst.xml.xpath2.api.Item;
import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeDefinition;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.types.builtin.BuiltinTypeLibrary;

public class XSLong extends XSInteger {
	
	private static final String XS_LONG = "xs:long";

	/**
	 * Initializes a representation of 0
	 */
	public XSLong() {
	  this(BigInteger.valueOf(0));
	}
	
	/**
	 * Initializes a representation of the supplied long value
	 * 
	 * @param x
	 *            Long to be stored
	 */
	public XSLong(BigInteger x) {
		super(x);
	}
	
	/**
	 * Retrieves the datatype's full pathname
	 * 
	 * @return "xs:long" which is the datatype's full pathname
	 */
	public String string_type() {
		return XS_LONG;
	}
	
	/**
	 * Retrieves the datatype's name
	 * 
	 * @return "long" which is the datatype's name
	 */
	public String type_name() {
		return "long";
	}
	
	/**
	 * Creates a new ResultSequence consisting of the extractable long in the
	 * supplied ResultSequence
	 * 
	 * @param arg
	 *            The ResultSequence from which the long is to be extracted
	 * @return New ResultSequence consisting of the 'long' supplied
	 * @throws DynamicError
	 */
	public ResultSequence constructor(ResultSequence arg) throws DynamicError {

		if (arg.empty())
			return ResultBuffer.EMPTY;

		// the function conversion rules apply here too. Get the argument
		// and convert it's string value to a long.
		Item aat = arg.first();

		try {
			BigInteger bigInt = new BigInteger(aat.getStringValue());
			
			// doing the range checking
			BigInteger min = BigInteger.valueOf(-9223372036854775808L);
			BigInteger max = BigInteger.valueOf(9223372036854775807L);			

			if (bigInt.compareTo(min) < 0 || bigInt.compareTo(max) > 0) {
			   // invalid input
			   DynamicError.throw_type_error();	
			}
			
			return new XSLong(bigInt);
		} catch (NumberFormatException e) {
			throw DynamicError.cant_cast(null);
		}

	}

	public TypeDefinition getTypeDefinition() {
		return BuiltinTypeLibrary.XS_LONG;
	}

}
