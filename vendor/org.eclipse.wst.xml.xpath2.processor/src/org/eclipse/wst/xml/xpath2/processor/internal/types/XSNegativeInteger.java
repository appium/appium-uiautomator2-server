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
 *     Mukul Gandhi - bug 277602 - Initial API and implementation, of xs:negativeInteger
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

public class XSNegativeInteger extends XSNonPositiveInteger {
	
	private static final String XS_NEGATIVE_INTEGER = "xs:negativeInteger";

	/**
	 * Initializes a representation of -1
	 */
	public XSNegativeInteger() {
	  this(BigInteger.valueOf(-1));
	}
	
	/**
	 * Initializes a representation of the supplied negativeInteger value
	 * 
	 * @param x
	 *            negativeInteger to be stored
	 */
	public XSNegativeInteger(BigInteger x) {
		super(x);
	}
	
	/**
	 * Retrieves the datatype's full pathname
	 * 
	 * @return "xs:negativeInteger" which is the datatype's full pathname
	 */
	public String string_type() {
		return XS_NEGATIVE_INTEGER;
	}
	
	/**
	 * Retrieves the datatype's name
	 * 
	 * @return "negativeInteger" which is the datatype's name
	 */
	public String type_name() {
		return "negativeInteger";
	}
	
	/**
	 * Creates a new ResultSequence consisting of the extractable negativeInteger
	 * in the supplied ResultSequence
	 * 
	 * @param arg
	 *            The ResultSequence from which the negativeInteger is to be extracted
	 * @return New ResultSequence consisting of the 'negativeInteger' supplied
	 * @throws DynamicError
	 */
	public ResultSequence constructor(ResultSequence arg) throws DynamicError {
		if (arg.empty())
			return ResultBuffer.EMPTY;

		// the function conversion rules apply here too. Get the argument
		// and convert it's string value to a nonPositiveInteger.
		Item aat = arg.first();

		try {
			BigInteger bigInt = new BigInteger(aat.getStringValue());
			
			// doing the range checking
			// min value is, -INF
			// max value is -1
			BigInteger max = BigInteger.valueOf(-1L);			

			if (bigInt.compareTo(max) > 0) {
			   // invalid input
			   throw DynamicError.cant_cast(null);	
			}
			
			return new XSNegativeInteger(bigInt);
		} catch (NumberFormatException e) {
			throw DynamicError.cant_cast(null);
		}

	}

	public TypeDefinition getTypeDefinition() {
		return BuiltinTypeLibrary.XS_NEGATIVEINTEGER;
	}
	
}
