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
 *     Mukul Gandhi - bug 280798 - PsychoPath support for JDK 1.4
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.utils;

import org.eclipse.wst.xml.xpath2.processor.internal.types.XSAnyURI;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSDate;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSDateTime;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSString;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSTime;

public class ComparableTypePromoter extends ScalarTypePromoter {

	protected boolean checkCombination(Class newType) {

		Class targetType = getTargetType();
		if (newType == XSString.class || newType == XSTime.class || targetType == XSString.class || targetType == XSTime.class) {
			return targetType == newType;	
		}
		if (newType == XSDate.class && targetType == XSDateTime.class) return true; // leave alone
		if (newType == XSDateTime.class && targetType != XSDateTime.class) {
			if (targetType == XSDate.class) {
				setTargetType(XSDateTime.class);
			} else return false;
		}

		return super.checkCombination(newType);
	}

	protected Class substitute(Class typeToConsider) {
		if (typeToConsider == XSAnyURI.class || typeToConsider == XSString.class) {
			return XSString.class;
		}
		if (typeToConsider == XSDateTime.class || typeToConsider == XSDate.class || typeToConsider == XSTime.class) {
			return typeToConsider;
		}

		return super.substitute(typeToConsider);
	}
	
}
