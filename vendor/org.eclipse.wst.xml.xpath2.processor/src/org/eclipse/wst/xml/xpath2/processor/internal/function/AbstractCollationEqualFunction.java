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
 *     David Carver (STAR) - initial API and implementation
 *     David Carver (STAR) - bug 296882 - fixed function that would always return false.
 *******************************************************************************/
package org.eclipse.wst.xml.xpath2.processor.internal.function;

import java.math.BigInteger;
import java.util.Iterator;

import org.eclipse.wst.xml.xpath2.api.DynamicContext;
import org.eclipse.wst.xml.xpath2.api.ResultBuffer;
import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyAtomicType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.NumericType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.QName;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSBoolean;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSDateTime;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSDouble;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSDuration;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSFloat;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSString;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSUntypedAtomic;

public abstract class AbstractCollationEqualFunction extends Function {

	public AbstractCollationEqualFunction(QName name, int arity) {
		super(name, arity);
		// TODO Auto-generated constructor stub
	}

	public AbstractCollationEqualFunction(QName name, int min_arity,
			int max_arity) {
		super(name, min_arity, max_arity);
	}


	protected static boolean hasValue(AnyType itema, AnyType itemb, DynamicContext context, String collationURI) throws DynamicError {
		XSString itemStr = new XSString(itema.getStringValue());
		if (isBoolean(itema, itemb)) {
			XSBoolean boolat = (XSBoolean) itema;
			if (boolat.eq(itemb, context)) {
				return true;
			}
		}

		if (isNumeric(itema, itemb)) {
			NumericType numericat = (NumericType) itema;
			if (numericat.eq(itemb, context)) {
				return true;
			}
		}

		if (isDuration(itema, itemb)) {
			XSDuration durat = (XSDuration) itema;
			if (durat.eq(itemb, context)) {
				return true;
			}
		}

		if (needsStringComparison(itema, itemb)) {
			XSString xstr1 = new XSString(itema.getStringValue());
			if (FnCompare.compare_string(collationURI, xstr1, itemStr,
					context).equals(BigInteger.ZERO)) {
				return true;
			}
		}
		return false;
	}
	
	protected static boolean hasValue(ResultBuffer rs, AnyAtomicType item,
			DynamicContext context, String collationURI) throws DynamicError {
		XSString itemStr = new XSString(item.getStringValue());

		for (Iterator i = rs.iterator(); i.hasNext();) {
			AnyType at = (AnyType) i.next();

			if (!(at instanceof CmpEq))
				continue;

			if (isBoolean(item, at)) {
				XSBoolean boolat = (XSBoolean) at;
				if (boolat.eq(item, context)) {
					return true;
				}
			}

			if (isNumeric(item, at)) {
				NumericType numericat = (NumericType) at;
				if (numericat.eq(item, context)) {
					return true;
				}
			}

			if (isDuration(item, at)) {
				XSDuration durat = (XSDuration) at;
				if (durat.eq(item, context)) {
					return true;
				}
			}

			if (needsStringComparison(item, at)) {
				XSString xstr1 = new XSString(at.getStringValue());
				if (FnCompare.compare_string(collationURI, xstr1, itemStr,
						context).equals(BigInteger.ZERO)) {
					return true;
				}
			}
		}
		return false;
	}

	protected static boolean isDuration(AnyAtomicType item, AnyType at) {
		return at instanceof XSDuration && item instanceof XSDuration;
	}

	protected static boolean isBoolean(AnyAtomicType item, AnyType at) {
		return at instanceof XSBoolean && item instanceof XSBoolean;
	}

	protected static boolean isNumeric(AnyAtomicType item, AnyType at) {
		return at instanceof NumericType && item instanceof NumericType;
	}

	protected static boolean needsStringComparison(AnyAtomicType item,
			AnyType at) {
		AnyType anyItem = (AnyType) item;
		return needsStringComparison(anyItem, at);
	}

	protected static boolean isDuration(AnyType item, AnyType at) {
		return at instanceof XSDuration && item instanceof XSDuration;
	}
	
	protected static boolean isDate(AnyType item, AnyType at) {
		return at instanceof XSDateTime && item instanceof XSDateTime;
	}
	

	protected static boolean isBoolean(AnyType cmptype, AnyType at) {
		return at instanceof XSBoolean && cmptype instanceof XSBoolean;
	}

	protected static boolean isNumeric(AnyType item, AnyType at) {
		return at instanceof NumericType && item instanceof NumericType;
	}

	protected static boolean needsStringComparison(AnyType item, AnyType at) {
		if (item instanceof NumericType) {
			if (at instanceof XSFloat) {
				XSFloat f = (XSFloat) at;
				if (f.nan()) {
					return true;
				}
			}
			
			if (at instanceof XSDouble) {
				XSDouble d = (XSDouble) at;
				if (d.nan()) {
					return true;
				}
			}
		}

		if (at instanceof XSString) {
			return true;
		}
		
		if (at instanceof XSUntypedAtomic) {
			return true;
		}
		return false;
	}

}
