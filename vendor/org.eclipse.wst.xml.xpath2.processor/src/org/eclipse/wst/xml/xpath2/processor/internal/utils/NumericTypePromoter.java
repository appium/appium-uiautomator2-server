/*******************************************************************************
 * Copyright (c) 2009, 2011 Jesper Steen Moller, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Jesper Steen Moller - initial API and implementation
 *     Jesper Steen Moller - bug 281028 - avg/min/max/sum work
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.utils;

import org.eclipse.wst.xml.xpath2.processor.DynamicError;
import org.eclipse.wst.xml.xpath2.processor.internal.types.AnyAtomicType;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSDecimal;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSDouble;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSFloat;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSInteger;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSUntypedAtomic;

public class NumericTypePromoter extends TypePromoter {

	protected boolean checkCombination(Class newType) {
		// Note: Double or float will override everything
		if (newType == XSDouble.class || getTargetType() == XSDouble.class) {
			setTargetType(XSDouble.class);
		} else if (newType == XSFloat.class || getTargetType() == XSFloat.class) {
			setTargetType(XSFloat.class);
		// If we're still with integers, stick with it
		} else if (newType == XSInteger.class && getTargetType() == XSInteger.class) {
			setTargetType(XSInteger.class);
		} else {
			// Otherwise, switch to decimals
			setTargetType(XSDecimal.class);
		}
		return true;
	}

	public AnyAtomicType doPromote(AnyAtomicType value) throws DynamicError {
		if (getTargetType() == XSFloat.class) {
			return new XSFloat(value.getStringValue());
		} else if (getTargetType() == XSDouble.class) {
			return new XSDouble(value.getStringValue());
		} else if (getTargetType() == XSInteger.class) {
			return new XSInteger(value.getStringValue());
		} else if (getTargetType() == XSDecimal.class) {
			return new XSDecimal(value.getStringValue());
		}
		return null;
	}

	protected Class substitute(Class typeToConsider) {
		if (typeToConsider == XSUntypedAtomic.class) return XSDouble.class;
		if (isDerivedFrom(typeToConsider, XSFloat.class)) return XSFloat.class;
		if (isDerivedFrom(typeToConsider, XSDouble.class)) return XSDouble.class;
		if (isDerivedFrom(typeToConsider, XSInteger.class)) return XSInteger.class;
		if (isDerivedFrom(typeToConsider, XSDecimal.class)) return XSDecimal.class;
		return null;
	}

	private boolean isDerivedFrom(Class typeToConsider, Class superType) {
		return superType.isAssignableFrom(typeToConsider);
	}
	
}
