/*******************************************************************************
 * Copyright (c) 2011, 2018 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xml.xpath2.processor.internal.types.builtin;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.xml.datatype.Duration;

import org.eclipse.wst.xml.xpath2.api.typesystem.TypeDefinition;
import org.eclipse.wst.xml.xpath2.api.typesystem.TypeModel;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyAtomicType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSAnyURI;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSBase64Binary;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSBoolean;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSByte;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSDate;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSDateTime;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSDayTimeDuration;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSDecimal;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSDouble;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSDuration;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSEntity;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSFloat;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSGDay;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSGMonth;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSGMonthDay;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSGYear;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSGYearMonth;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSHexBinary;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSID;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSIDREF;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSInt;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSInteger;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSLong;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSNCName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSNMTOKEN;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSNegativeInteger;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSNonNegativeInteger;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSNonPositiveInteger;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSNotation;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSPositiveInteger;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSShort;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSString;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSTime;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSToken;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSUnsignedByte;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSUnsignedInt;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSUnsignedLong;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSUnsignedShort;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSYearMonthDuration;
import org.w3c.dom.Node;


/**
 * This type captures all of the built-in XPath 2.0 types, as defined in F&O specification.
 * http://www.w3.org/TR/xquery-operators/#datatypes
 */
public class BuiltinTypeLibrary {
	// This type captures all of the built-in XPath 2.0 types, as defined in F&O specification.
	// http://www.w3.org/TR/xquery-operators/#datatypes
	
	public final static BuiltinTypeDefinition XS_ANYTYPE = new BuiltinTypeDefinition("anyType", null);
		public final static BuiltinTypeDefinition XS_UNTYPED = new BuiltinTypeDefinition("untyped", XS_ANYTYPE);
		public final static BuiltinTypeDefinition XS_ANYSIMPLETYPE = new BuiltinTypeDefinition("anySimpleType", XS_ANYTYPE);
			//	Subtypes of user-defined list and union types
			//	xs:IDREFS
			//	xs:NMTOKENS
			//	xs:ENTITIES
			//	xs:anyAtomicType
			public final static BuiltinAtomicTypeDefinition XS_ANYATOMICTYPE = new BuiltinAtomicTypeDefinition("anyAtomicType", AnyAtomicType.class, Object.class, XS_ANYSIMPLETYPE);
			
	public final static BuiltinAtomicTypeDefinition XS_UNTYPEDATOMIC = new BuiltinAtomicTypeDefinition("untypedAtomic", null, String.class, XS_ANYATOMICTYPE);

	public final static BuiltinAtomicTypeDefinition XS_DATETIME = new BuiltinAtomicTypeDefinition("dateTime", XSDateTime.class, Calendar.class,XS_ANYATOMICTYPE);
	public final static BuiltinAtomicTypeDefinition XS_DATE = new BuiltinAtomicTypeDefinition("date", XSDate.class, Calendar.class, XS_ANYATOMICTYPE);
	public final static BuiltinAtomicTypeDefinition XS_TIME = new BuiltinAtomicTypeDefinition("time", XSTime.class, Calendar.class, XS_ANYATOMICTYPE);
	public final static BuiltinAtomicTypeDefinition XS_DURATION = new BuiltinAtomicTypeDefinition("duration", XSDuration.class, Duration.class, XS_ANYATOMICTYPE);
		public final static BuiltinAtomicTypeDefinition XS_YEARMONTHDURATION = new BuiltinAtomicTypeDefinition("yearMonthDuration", XSYearMonthDuration.class, Duration.class, XS_DURATION);
		public final static BuiltinAtomicTypeDefinition XS_DAYTIMEDURATION = new BuiltinAtomicTypeDefinition("dayTimeDuration", XSDayTimeDuration.class, Duration.class, XS_DURATION);
	public final static BuiltinAtomicTypeDefinition XS_FLOAT = new BuiltinAtomicTypeDefinition("float", XSFloat.class, Float.class, XS_ANYATOMICTYPE);
	public final static BuiltinAtomicTypeDefinition XS_DOUBLE = new BuiltinAtomicTypeDefinition("double", XSDouble.class, Double.class, XS_ANYATOMICTYPE);
	public final static BuiltinAtomicTypeDefinition XS_DECIMAL = new BuiltinAtomicTypeDefinition("decimal", XSDecimal.class, BigDecimal.class, XS_ANYATOMICTYPE);
		public final static BuiltinAtomicTypeDefinition XS_INTEGER = new BuiltinAtomicTypeDefinition("integer", XSInteger.class, BigInteger.class, XS_DECIMAL);
			public final static BuiltinAtomicTypeDefinition XS_NONPOSITIVEINTEGER = new BuiltinAtomicTypeDefinition("nonPositiveInteger",  XSNonPositiveInteger.class, BigInteger.class, XS_INTEGER);
				public final static BuiltinAtomicTypeDefinition XS_NEGATIVEINTEGER = new BuiltinAtomicTypeDefinition("negativeInteger",  XSNegativeInteger.class, BigInteger.class, XS_NONPOSITIVEINTEGER);
			public final static BuiltinAtomicTypeDefinition XS_LONG = new BuiltinAtomicTypeDefinition("long", XSLong.class, Long.class, XS_INTEGER);
				public final static BuiltinAtomicTypeDefinition XS_INT = new BuiltinAtomicTypeDefinition("int", XSInt.class, Integer.class, XS_LONG);
					public final static BuiltinAtomicTypeDefinition XS_SHORT = new BuiltinAtomicTypeDefinition("short", XSShort.class, Short.class, XS_INT);
						public final static BuiltinAtomicTypeDefinition XS_BYTE = new BuiltinAtomicTypeDefinition("byte", XSByte.class, Byte.class, XS_SHORT);
			public final static BuiltinAtomicTypeDefinition XS_NONNEGATIVEINTEGER = new BuiltinAtomicTypeDefinition("nonNegativeInteger", XSNonNegativeInteger.class, BigInteger.class, XS_INTEGER);
				public final static BuiltinAtomicTypeDefinition XS_UNSIGNEDLONG = new BuiltinAtomicTypeDefinition("unsignedLong", XSUnsignedLong.class, BigInteger.class, XS_NONNEGATIVEINTEGER);
					public final static BuiltinAtomicTypeDefinition XS_UNSIGNEDINT = new BuiltinAtomicTypeDefinition("unsignedInt", XSUnsignedInt.class, Long.class, XS_UNSIGNEDLONG);
						public final static BuiltinAtomicTypeDefinition XS_UNSIGNEDSHORT = new BuiltinAtomicTypeDefinition("unsignedShort", XSUnsignedShort.class, Integer.class, XS_UNSIGNEDINT);
							public final static BuiltinAtomicTypeDefinition XS_UNSIGNEDBYTE = new BuiltinAtomicTypeDefinition("unsignedByte", XSUnsignedByte.class, Short.class, XS_UNSIGNEDSHORT);
				public final static BuiltinAtomicTypeDefinition XS_POSITIVEINTEGER = new BuiltinAtomicTypeDefinition("positiveInteger", XSPositiveInteger.class, BigInteger.class, XS_NONNEGATIVEINTEGER);
	public final static BuiltinAtomicTypeDefinition XS_GYEARMONTH = new BuiltinAtomicTypeDefinition("gYearMonth", XSGYearMonth.class, Calendar.class, XS_ANYATOMICTYPE);
	public final static BuiltinAtomicTypeDefinition XS_GYEAR = new BuiltinAtomicTypeDefinition("gYear", XSGYear.class, Calendar.class, XS_ANYATOMICTYPE);
	public final static BuiltinAtomicTypeDefinition XS_GMONTHDAY = new BuiltinAtomicTypeDefinition("gMonthDay", XSGMonthDay.class, Calendar.class, XS_ANYATOMICTYPE);
	public final static BuiltinAtomicTypeDefinition XS_GDAY = new BuiltinAtomicTypeDefinition("gDay", XSGDay.class, Calendar.class, XS_ANYATOMICTYPE);
	public final static BuiltinAtomicTypeDefinition XS_GMONTH = new BuiltinAtomicTypeDefinition("gMonth", XSGMonth.class, Calendar.class, XS_ANYATOMICTYPE);
	public final static BuiltinAtomicTypeDefinition XS_STRING = new BuiltinAtomicTypeDefinition("string", XSString.class, String.class, XS_ANYATOMICTYPE);
		public final static BuiltinAtomicTypeDefinition XS_NORMALIZEDSTRING = new BuiltinAtomicTypeDefinition("normalizedString", XSString.class, String.class, XS_ANYATOMICTYPE);
			public final static BuiltinAtomicTypeDefinition XS_TOKEN = new BuiltinAtomicTypeDefinition("token", XSToken.class, String.class, XS_NORMALIZEDSTRING);
				public final static BuiltinAtomicTypeDefinition XS_LANGUAGE = new BuiltinAtomicTypeDefinition("language", XSToken.class, String.class, XS_TOKEN);
				public final static BuiltinAtomicTypeDefinition XS_NMTOKEN = new BuiltinAtomicTypeDefinition("NMTOKEN", XSNMTOKEN.class, String.class, XS_TOKEN);
				public final static BuiltinAtomicTypeDefinition XS_NAME = new BuiltinAtomicTypeDefinition("Name", XSName.class, String.class, XS_TOKEN);
					public final static BuiltinAtomicTypeDefinition XS_NCNAME = new BuiltinAtomicTypeDefinition("NCName", XSNCName.class, String.class, XS_NAME);
						public final static BuiltinAtomicTypeDefinition XS_ID = new BuiltinAtomicTypeDefinition("ID", XSID.class, String.class, XS_NCNAME);
						public final static BuiltinAtomicTypeDefinition XS_IDREF = new BuiltinAtomicTypeDefinition("IDREF", XSIDREF.class, String.class, XS_NCNAME);
						public final static BuiltinAtomicTypeDefinition XS_ENTITY = new BuiltinAtomicTypeDefinition("ENTITY", XSEntity.class, String.class, XS_NCNAME);
	public final static BuiltinAtomicTypeDefinition XS_BOOLEAN = new BuiltinAtomicTypeDefinition("boolean", XSBoolean.class, Boolean.class, XS_ANYATOMICTYPE);
	public final static BuiltinAtomicTypeDefinition XS_BASE64BINARY = new BuiltinAtomicTypeDefinition("base64Binary", XSBase64Binary.class, byte[].class, XS_ANYATOMICTYPE);
	public final static BuiltinAtomicTypeDefinition XS_HEXBINARY = new BuiltinAtomicTypeDefinition("hexBinary", XSHexBinary.class, byte[].class, XS_ANYATOMICTYPE);
	public final static BuiltinAtomicTypeDefinition XS_ANYURI = new BuiltinAtomicTypeDefinition("anyURI", XSAnyURI.class, String.class, XS_ANYATOMICTYPE);
	public final static BuiltinAtomicTypeDefinition XS_QNAME = new BuiltinAtomicTypeDefinition("QName", QName.class, javax.xml.namespace.QName.class, XS_ANYATOMICTYPE);
	public final static BuiltinAtomicTypeDefinition XS_NOTATION = new BuiltinAtomicTypeDefinition("NOTATION", XSNotation.class, String.class, XS_ANYATOMICTYPE);

	// These are also subtypes of any types 
	public final static BuiltinListTypeDefinition XS_IDREFS = new BuiltinListTypeDefinition("IDREFS", XS_ANYTYPE, XS_IDREF);
	public final static BuiltinListTypeDefinition XS_NMTOKENS = new BuiltinListTypeDefinition("NMTOKENS", XS_ANYTYPE, XS_NMTOKEN);
	public final static BuiltinListTypeDefinition XS_ENTITIES = new BuiltinListTypeDefinition("ENTITIES", XS_ANYTYPE, XS_ENTITY);
	
	public static final TypeModel BUILTIN_TYPES = new TypeModel() {

		public TypeDefinition lookupType(String namespace, String typeName) {
			if (! BuiltinTypeDefinition.XS_NS.equals(namespace)) return null;
			return (TypeDefinition) builtinTypes.get(typeName);
		}

		public TypeDefinition lookupElementDeclaration(String namespace, String elementName) {
			return null;
		}

		public TypeDefinition lookupAttributeDeclaration(String namespace, String attributeName) {
			return null;
		}
		
		private Map/*<String, BuiltinTypeDefinition>*/ builtinTypes = new HashMap/*<String, BuiltinTypeDefinition>*/();
		{
			addType(XS_ANYTYPE);
			addType(XS_UNTYPED);
			addType(XS_ANYSIMPLETYPE);
			addType(XS_ANYATOMICTYPE);
							
			addType(XS_UNTYPEDATOMIC);
			
			addType(XS_DATETIME);
			addType(XS_DATE);
			addType(XS_TIME);
			addType(XS_DURATION);
			addType(XS_YEARMONTHDURATION);
			addType(XS_DAYTIMEDURATION);
			addType(XS_FLOAT);
			addType(XS_DOUBLE);
			addType(XS_DECIMAL);
			addType(XS_INTEGER);
			addType(XS_NONPOSITIVEINTEGER);
			addType(XS_NEGATIVEINTEGER);
			addType(XS_LONG);
			addType(XS_INT);
			addType(XS_SHORT);
			addType(XS_BYTE);
			addType(XS_NONNEGATIVEINTEGER);
			addType(XS_UNSIGNEDLONG);
			addType(XS_UNSIGNEDINT);
			addType(XS_UNSIGNEDSHORT);
			addType(XS_UNSIGNEDBYTE);
			addType(XS_POSITIVEINTEGER);
			addType(XS_GYEARMONTH);
			addType(XS_GYEAR);
			addType(XS_GMONTHDAY);
			addType(XS_GDAY);
			addType(XS_GMONTH);
			addType(XS_STRING);
			addType(XS_NORMALIZEDSTRING);
			addType(XS_TOKEN);
			addType(XS_LANGUAGE);
			addType(XS_NMTOKEN);
			addType(XS_NAME);
			addType(XS_NCNAME);
			addType(XS_ID);
			addType(XS_IDREF);
			addType(XS_ENTITY);
			addType(XS_BOOLEAN);
			addType(XS_BASE64BINARY);
			addType(XS_HEXBINARY);
			addType(XS_ANYURI);
			addType(XS_QNAME);
			addType(XS_NOTATION);
			addType(XS_IDREFS);
			addType(XS_NMTOKENS);
			addType(XS_ENTITIES);
		}

		private void addType(BuiltinTypeDefinition btd) { builtinTypes.put(btd.getName(), btd); }

		public TypeDefinition getType(Node node) {
			return null;
		}
	};
	
	// Not types under XML Schema namespace
//	item
//	xs:anyAtomicType
//	node
//	attribute
//	user-defined attribute types
//	comment
//	document
//	user-defined document types
//	element
//	user-defined element types
//	processing-instruction
//	text

	
	
}
