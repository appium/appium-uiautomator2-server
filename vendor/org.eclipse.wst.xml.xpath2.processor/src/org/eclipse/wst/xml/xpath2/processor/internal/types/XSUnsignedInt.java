/*******************************************************************************
 * Copyright (c) 2009, 2013 Mukul Gandhi, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Mukul Gandhi - bug 277642 - Initial API and implementation, of xs:unsignedInt
 *                                 data type.
 *     David Carver (STAR) - bug 262765 - fixed abs value tests.
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

public class XSUnsignedInt extends XSUnsignedLong {
	
	private static final String XS_UNSIGNED_INT = "xs:unsignedInt";

	/**
	 * Initializes a representation of 0
	 */
	public XSUnsignedInt() {
	  this(BigInteger.valueOf(0));
	}
	
	/**
	 * Initializes a representation of the supplied unsignedInt value
	 * 
	 * @param x
	 *            unsignedInt to be stored
	 */
	public XSUnsignedInt(BigInteger x) {
		super(x);
	}
	
	/**
	 * Retrieves the datatype's full pathname
	 * 
	 * @return "xs:unsignedInt" which is the datatype's full pathname
	 */
	public String string_type() {
		return XS_UNSIGNED_INT;
	}
	
	/**
	 * Retrieves the datatype's name
	 * 
	 * @return "unsignedInt" which is the datatype's name
	 */
	public String type_name() {
		return "unsignedInt";
	}
	
	/**
	 * Creates a new ResultSequence consisting of the extractable unsignedInt
	 * in the supplied ResultSequence
	 * 
	 * @param arg
	 *            The ResultSequence from which the unsignedInt is to be extracted
	 * @return New ResultSequence consisting of the 'unsignedInt' supplied
	 * @throws DynamicError
	 */
	public ResultSequence constructor(ResultSequence arg) throws DynamicError {
		if (arg.empty())
			return ResultBuffer.EMPTY;

		// the function conversion rules apply here too. Get the argument
		// and convert it's string value to a unsignedInt.
		Item aat = arg.first();

		try {
			BigInteger bigInt = new BigInteger(aat.getStringValue());
			
			// doing the range checking
			// min value is 0
			// max value is 4294967295
			BigInteger min = BigInteger.valueOf(0);
			BigInteger max = BigInteger.valueOf(4294967295L);

			if (bigInt.compareTo(min) < 0 || bigInt.compareTo(max) > 0) {
			   // invalid input
			   throw DynamicError.cant_cast(null);	
			}
			
			return new XSUnsignedInt(bigInt);
		} catch (NumberFormatException e) {
			throw DynamicError.cant_cast(null);
		}

	}

	public TypeDefinition getTypeDefinition() {
		return BuiltinTypeLibrary.XS_UNSIGNEDINT;
	}

	public Number getNativeValue() {
		return getValue().intValue();
	}
}
