/*******************************************************************************
 * Copyright (c) 2010, 2011 Mukul Gandhi, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Mukul Gandhi - initial API and implementation
 *     Mukul Gandhi - bug 318313 - improvements to computation of typed values of nodes, when validated by XML Schema 
 *                                 primitive types.
 *     Mukul Gandhi - bug 323900 - improving computing the typed value of element and attribute nodes, where the schema
 *                                 type of nodes are simple, with varieties 'list' and 'union'.
 *     Mukul Gandhi - bug 341862 - improvements to computation of typed value of xs:boolean nodes.                                                                  
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.types;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.apache.xerces.xs.XSConstants;
import org.eclipse.wst.xml.xpath2.processor.PsychoPathTypeHelper;

/**
 * A factory class implementation, to construct PsychoPath schema type representation corresponding 
 * to XML Schema types.
 */
public class SchemaTypeValueFactory {

	public static AnyType newSchemaTypeValue(short typeDef, String strValue) {
		
		if (typeDef == XSConstants.ANYURI_DT) {
			return new XSAnyURI(strValue);
		}
		
		if (typeDef == XSConstants.BOOLEAN_DT) {
			String newStrValue = ("1".equals(strValue) || "true".equals(strValue)) ? "true" : "false";
			return new XSBoolean(Boolean.valueOf(newStrValue).booleanValue());
		}
		
		if (typeDef == XSConstants.DATE_DT) {       
			return XSDate.parse_date(strValue);
		}
		
		if (typeDef == XSConstants.DATETIME_DT) {
			return XSDateTime.parseDateTime(strValue);
		}
		
		// decimal and it's subtypes		
		if (typeDef == XSConstants.DECIMAL_DT) {      
			return new XSDecimal(new BigDecimal(strValue));
		}
		
		if (typeDef == XSConstants.INTEGER_DT) {      
			return new XSInteger(new BigInteger(strValue));
		}
		
		if (typeDef == XSConstants.LONG_DT) {     
			return new XSLong(new BigInteger(strValue));
		}
		
		if (typeDef == XSConstants.INT_DT) {      
			return new XSInt(new BigInteger(strValue));
		}
		
		if (typeDef == XSConstants.SHORT_DT) {      
			return new XSShort(new BigInteger(strValue));
		}
		
		if (typeDef == XSConstants.BYTE_DT) {      
			return new XSByte(new BigInteger(strValue));
		}
		
		if (typeDef == XSConstants.NONNEGATIVEINTEGER_DT) {      
			return new XSNonNegativeInteger(new BigInteger(strValue));
		}
		
		if (typeDef == XSConstants.POSITIVEINTEGER_DT) {      
			return new XSPositiveInteger(new BigInteger(strValue));
		}
		
		if (typeDef == XSConstants.UNSIGNEDLONG_DT) {      
			return new XSUnsignedLong(new BigInteger(strValue));
		}
		
		if (typeDef == XSConstants.UNSIGNEDINT_DT) {      
			return new XSUnsignedInt(new BigInteger(strValue));
		}
		
		if (typeDef == XSConstants.UNSIGNEDSHORT_DT) {      
			return new XSUnsignedShort(new BigInteger(strValue));
		}
		
		if (typeDef == XSConstants.UNSIGNEDBYTE_DT) {      
			return new XSUnsignedByte(new BigInteger(strValue));
		}
		
		if (typeDef == XSConstants.NONPOSITIVEINTEGER_DT) {      
			return new XSNonPositiveInteger(new BigInteger(strValue));
		}
		
		if (typeDef == XSConstants.NEGATIVEINTEGER_DT) {      
			return new XSNegativeInteger(new BigInteger(strValue));
		}
		// end of, decimal types
		
		if (typeDef == XSConstants.DOUBLE_DT) {       
			return new XSDouble(Double.parseDouble(strValue));
		}
		
		// duration and it's subtypes
		if (typeDef == XSConstants.DURATION_DT) {       
			return XSDuration.parseDTDuration(strValue);
		}
		
		if (typeDef == PsychoPathTypeHelper.DAYTIMEDURATION_DT) {       
			return XSDayTimeDuration.parseDTDuration(strValue);
		}
		
		if (typeDef == PsychoPathTypeHelper.YEARMONTHDURATION_DT) {       
			return XSYearMonthDuration.parseYMDuration(strValue);
		}
		// end of, duration types
		
		if (typeDef == XSConstants.FLOAT_DT) {        
			return new XSFloat(Float.parseFloat(strValue));
		}
		
		if (typeDef == XSConstants.GDAY_DT) {        
			return XSGDay.parse_gDay(strValue);
		}
		
		if (typeDef == XSConstants.GMONTH_DT) {        
			return XSGMonth.parse_gMonth(strValue);
		}
		
		if (typeDef == XSConstants.GMONTHDAY_DT) {        
			return XSGMonthDay.parse_gMonthDay(strValue);
		}
		
		if (typeDef == XSConstants.GYEAR_DT) {        
			return XSGYear.parse_gYear(strValue);
		}
		
		if (typeDef == XSConstants.GYEARMONTH_DT) {        
			return XSGYearMonth.parse_gYearMonth(strValue);
		}
		
		if (typeDef == XSConstants.NOTATION_DT) {
			return new XSString(strValue);
		}
		
		if (typeDef == XSConstants.QNAME_DT) {
			return QName.parse_QName(strValue);
		}
		
		// string and it's subtypes
		if (typeDef == XSConstants.STRING_DT) {
			return new XSString(strValue);   
		}
		
		if (typeDef == XSConstants.NORMALIZEDSTRING_DT) {
			return new XSNormalizedString(strValue);   
		}
		
		if (typeDef == XSConstants.TOKEN_DT) {
			return new XSToken(strValue);   
		}
		
		if (typeDef == XSConstants.NAME_DT) {
			return new XSName(strValue);   
		}
		
		if (typeDef == XSConstants.NCNAME_DT) {
			return new XSNCName(strValue);   
		}
		
		if (typeDef == XSConstants.ENTITY_DT) {
			return new XSEntity(strValue);   
		}
		
		if (typeDef == XSConstants.ID_DT) {
			return new XSID(strValue);   
		}
		
		if (typeDef == XSConstants.IDREF_DT) {
			return new XSIDREF(strValue);   
		}
		
		if (typeDef == XSConstants.NMTOKEN_DT) {
			return new XSNMTOKEN(strValue);   
		}		
		// end of, string types
		
		if (typeDef == XSConstants.TIME_DT) {
			return XSTime.parse_time(strValue);
		}  
		
	    // create a XSString value, as fallback option 
		return new XSString(strValue);
		
	} // newSchemaTypeValue
	
}