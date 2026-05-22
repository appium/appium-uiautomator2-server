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
 *     Mukul Gandhi - bug 277608 - implementation of xs:short data type
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

public class XSShort extends XSInt {
	
	private static final String XS_SHORT = "xs:short";

	/**
	 * Initializes a representation of 0
	 */
	public XSShort() {
	  this(BigInteger.valueOf(0));
	}
	
	/**
	 * Initializes a representation of the supplied short value
	 * 
	 * @param x
	 *            Short to be stored
	 */
	public XSShort(BigInteger x) {
		super(x);
	}
	
	/**
	 * Retrieves the datatype's full pathname
	 * 
	 * @return "xs:short" which is the datatype's full pathname
	 */
	public String string_type() {
		return XS_SHORT;
	}
	
	/**
	 * Retrieves the datatype's name
	 * 
	 * @return "short" which is the datatype's name
	 */
	public String type_name() {
		return "short";
	}
	
	/**
	 * Creates a new ResultSequence consisting of the extractable 'short' in the
	 * supplied ResultSequence
	 * 
	 * @param arg
	 *            The ResultSequence from which the short is to be extracted
	 * @return New ResultSequence consisting of the 'short' supplied
	 * @throws DynamicError
	 */
	public ResultSequence constructor(ResultSequence arg) throws DynamicError {
		if (arg.empty())
			return ResultBuffer.EMPTY;

		// the function conversion rules apply here too. Get the argument
		// and convert it's string value to a short.
		Item aat = arg.first();

		try {
			BigInteger bigInt = new BigInteger(aat.getStringValue());
			
			// doing the range checking
			BigInteger min = BigInteger.valueOf(-32768L);
			BigInteger max = BigInteger.valueOf(32767L);			

			if (bigInt.compareTo(min) < 0 || bigInt.compareTo(max) > 0) {
			   // invalid input
			   DynamicError.throw_type_error();	
			}
			
			return new XSShort(bigInt);
		} catch (NumberFormatException e) {
			throw DynamicError.cant_cast(null);
		}

	}

	public TypeDefinition getTypeDefinition() {
		return BuiltinTypeLibrary.XS_SHORT;
	}

	public Short getNativeValue() {
		return getValue().shortValue();
	}

}
