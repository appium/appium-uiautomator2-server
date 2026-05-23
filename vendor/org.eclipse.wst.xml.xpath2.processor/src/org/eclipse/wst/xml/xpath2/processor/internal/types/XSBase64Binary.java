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
 *     Mukul Gandhi - bug 281046 - initial API and implementation 
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.types;

import org.apache.xerces.impl.dv.util.Base64;
import org.apache.xerces.impl.dv.util.HexBin;
import org.eclipse.wst.xml.xpath2.api.DynamicContext;
import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.api.ResultSequence;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeDefinition;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.function.CmpEq;
import org.eclipse.wst.xml.xpath2.processor.internal.types.builtin.BuiltinTypeLibrary;

/**
 * A representation of the base64Binary datatype
 */
public class XSBase64Binary extends CtrType implements CmpEq {

	private static final String XS_BASE64_BINARY = "xs:base64Binary";
	private String _value;

	/**
	 * Initialises using the supplied String
	 * 
	 * @param x
	 *            The String to initialise to
	 */
	public XSBase64Binary(String x) {
		_value = x;
	}

	/**
	 * Initialises to null
	 */
	public XSBase64Binary() {
		this(null);
	}

	/**
	 * Retrieves the datatype's full pathname
	 * 
	 * @return "xs:base64Binary" which is the datatype's full pathname
	 */
	public String string_type() {
		return XS_BASE64_BINARY;
	}

	/**
	 * Retrieves the datatype's name
	 * 
	 * @return "base64Binary" which is the datatype's name
	 */
	public String type_name() {
		return "base64Binary";
	}

	/**
	 * Retrieves a String representation of the base64Binary stored. This method is
	 * functionally identical to value()
	 * 
	 * @return The base64Binary stored
	 */
	public String getStringValue() {
		return _value;
	}

	/**
	 * Retrieves a String representation of the base64Binary stored. This method is
	 * functionally identical to string_value()
	 * 
	 * @return The base64Binary stored
	 */
	public String value() {
		return _value;
	}

	/**
	 * Creates a new ResultSequence consisting of the base64Binary value
	 * 
	 * @param arg
	 *            The ResultSequence from which to construct base64Binary value 
	 * @return New ResultSequence representing base64Binary value 
	 * @throws DynamicError
	 */
	public ResultSequence constructor(ResultSequence arg) throws DynamicError {

		if (arg.empty())
			return ResultBuffer.EMPTY;

		AnyAtomicType aat = (AnyAtomicType) arg.first();
		if (aat instanceof NumericType || aat instanceof XSDuration ||
			aat instanceof CalendarType || aat instanceof XSBoolean ||
			aat instanceof XSAnyURI) {
			throw DynamicError.invalidType();
		}

		if (!isCastable(aat)) {
			throw DynamicError.cant_cast(null);
		}
		
		String str_value = aat.getStringValue();
		
		byte[] decodedValue = Base64.decode(str_value);
		
		if (aat instanceof XSHexBinary) {
			decodedValue = HexBin.decode(str_value);
			decodedValue = Base64.encode(decodedValue).getBytes();
		} else {
			decodedValue = str_value.getBytes();
		}
		if (decodedValue != null) {
		  return new XSBase64Binary(new String(decodedValue));
		}
		else {
		  // invalid base64 string
		  throw DynamicError.throw_type_error();	
		}
	}

	private boolean isCastable(AnyAtomicType aat) {
		if (aat instanceof XSString || aat instanceof XSUntypedAtomic) {
			return true;
		}
		
		if (aat instanceof XSBase64Binary || aat instanceof XSHexBinary) {
			return true;
		}
		
		return false;
	}

	/**
	 * Equality comparison between this and the supplied representation which
	 * must be of type base64Binary
	 * 
	 * @param arg
	 *            The representation to compare with
	 * @return True if the two representation are same. False otherwise.
	 *         
	 * @throws DynamicError
	 */
	public boolean eq(AnyType arg, DynamicContext dynamicContext) throws DynamicError {
      String valToCompare = arg.getStringValue();
      
      byte[] value1 = Base64.decode(_value);
      byte[] value2 = Base64.decode(valToCompare);
      if (value2 == null) {
    	return false;  
      }
      
      int len = value1.length;
      if (len != value2.length) {
        return false;
      }
      
      for (int i = 0; i < len; i++) {
        if (value1[i] != value2[i]) {
          return false;
        }
      }
      
      return true;
	}

	public TypeDefinition getTypeDefinition() {
		return BuiltinTypeLibrary.XS_BASE64BINARY;
	}

	public Object getNativeValue() {
		return Base64.decode(_value);
	}
}
