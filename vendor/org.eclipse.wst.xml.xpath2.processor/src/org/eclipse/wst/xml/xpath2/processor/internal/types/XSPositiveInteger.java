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
 *     Mukul Gandhi - bug 277632 - Initial API and implementation, of xs:positiveInteger
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

public class XSPositiveInteger extends XSNonNegativeInteger {
	
	private static final String XS_POSITIVE_INTEGER = "xs:positiveInteger";

	/**
	 * Initializes a representation of 1
	 */
	public XSPositiveInteger() {
	  this(BigInteger.valueOf(1));
	}
	
	/**
	 * Initializes a representation of the supplied positiveInteger value
	 * 
	 * @param x
	 *            positiveInteger to be stored
	 */
	public XSPositiveInteger(BigInteger x) {
		super(x);
	}
	
	/**
	 * Retrieves the datatype's full pathname
	 * 
	 * @return "xs:positiveInteger" which is the datatype's full pathname
	 */
	public String string_type() {
		return XS_POSITIVE_INTEGER;
	}
	
	/**
	 * Retrieves the datatype's name
	 * 
	 * @return "positiveInteger" which is the datatype's name
	 */
	public String type_name() {
		return "positiveInteger";
	}
	
	/**
	 * Creates a new ResultSequence consisting of the extractable positiveInteger
	 * in the supplied ResultSequence
	 * 
	 * @param arg
	 *            The ResultSequence from which the positiveInteger is to be extracted
	 * @return New ResultSequence consisting of the 'positiveInteger' supplied
	 * @throws DynamicError
	 */
	public ResultSequence constructor(ResultSequence arg) throws DynamicError {
		if (arg.empty())
			return ResultBuffer.EMPTY;

		// the function conversion rules apply here too. Get the argument
		// and convert it's string value to a positiveInteger.
		Item aat = arg.first();

		try {
			BigInteger bigInt = new BigInteger(aat.getStringValue());
			
			// doing the range checking
			// min value is 1
			// max value is INF
			BigInteger min = BigInteger.valueOf(1);

			if (bigInt.compareTo(min) < 0) {
			   // invalid input
			   throw DynamicError.cant_cast(null);	
			}
			
			return new XSPositiveInteger(bigInt);
		} catch (NumberFormatException e) {
			throw DynamicError.cant_cast(null);
		}

	}

	public TypeDefinition getTypeDefinition() {
		return BuiltinTypeLibrary.XS_POSITIVEINTEGER;
	}

}
