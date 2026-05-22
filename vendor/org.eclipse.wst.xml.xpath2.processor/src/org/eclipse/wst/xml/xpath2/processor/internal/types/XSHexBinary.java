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
 *     Mukul Gandhi - bug 281054 - initial API and implementation
 *     David Carver (STAR) - bug 228223 - fixed casting issue.  Needed to encode the value. 
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
 * A representation of the xs:hexBinary datatype
 */
public class XSHexBinary extends CtrType implements CmpEq {

	private static final String XS_HEX_BINARY = "xs:hexBinary";
	private String _value;

	/**
	 * Initialises using the supplied String
	 * 
	 * @param x
	 *            The String to initialise to
	 */
	public XSHexBinary(String x) {
		_value = x;
	}

	/**
	 * Initialises to null
	 */
	public XSHexBinary() {
		this(null);
	}

	/**
	 * Retrieves the datatype's full pathname
	 * 
	 * @return "xs:hexBinary" which is the datatype's full pathname
	 */
	public String string_type() {
		return XS_HEX_BINARY;
	}

	/**
	 * Retrieves the datatype's name
	 * 
	 * @return "hexBinary" which is the datatype's name
	 */
	public String type_name() {
		return "hexBinary";
	}

	/**
	 * Retrieves a String representation of the base64Binary stored. This method is
	 * functionally identical to value()
	 * 
	 * @return The hexBinary stored
	 */
	public String getStringValue() {
		return _value.toUpperCase();
	}

	/**
	 * Retrieves a String representation of the hexBinary stored. This method is
	 * functionally identical to string_value()
	 * 
	 * @return The hexBinary stored
	 */
	public String value() {
		return _value;
	}

	/**
	 * Creates a new ResultSequence consisting of the hexBinary value
	 * 
	 * @param arg
	 *            The ResultSequence from which to construct hexBinary value 
	 * @return New ResultSequence representing hexBinary value 
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

		String str_value = aat.getStringValue();
		
		if (!(aat instanceof XSHexBinary ||
				  aat instanceof XSString ||
				  aat instanceof XSUntypedAtomic ||
				  aat instanceof XSBase64Binary)) {
				throw DynamicError.cant_cast(null);
			}
		
		if (aat instanceof XSUntypedAtomic || aat instanceof XSString) {
			String[] nonHexValues = null;
			try {
				nonHexValues = str_value.split("[0-9a-fA-F]");
			} catch (Exception ex) {
				throw DynamicError.throw_type_error();
			}
			
			String[] binValues = null;
			try {
				binValues = str_value.split("[0-1]");
			} catch (Exception ex) {
				throw DynamicError.throw_type_error();
			}
			
			if (nonHexValues.length > 0 || binValues.length == 0) {
				throw DynamicError.invalidForCastConstructor();
			}			
		}

		
		byte[] decodedValue = null;
		
		if (aat instanceof XSBase64Binary) {
			decodedValue = Base64.decode(str_value);
			decodedValue = HexBin.encode(decodedValue).getBytes();
		} else {
			decodedValue = str_value.getBytes();
 		}
		
		if (decodedValue != null) {
		  return new XSHexBinary(new String(decodedValue));
		}
		else {
		  // invalid hexBinary string
		  throw DynamicError.throw_type_error();	
		}
	}

	/**
	 * Equality comparison between this and the supplied representation which
	 * must be of type hexBinary
	 * 
	 * @param arg
	 *            The representation to compare with
	 * @return True if the two representation are same. False otherwise.
	 *         
	 * @throws DynamicError
	 */
	public boolean eq(AnyType arg, DynamicContext dynamicContext) throws DynamicError {
      String valToCompare = arg.getStringValue();
      
      byte[] value1 = HexBin.decode(_value);
      byte[] value2 = HexBin.decode(valToCompare);
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
		return BuiltinTypeLibrary.XS_HEXBINARY;
	}
	
	public Object getNativeValue() {
		return HexBin.decode(_value);
	}
}
