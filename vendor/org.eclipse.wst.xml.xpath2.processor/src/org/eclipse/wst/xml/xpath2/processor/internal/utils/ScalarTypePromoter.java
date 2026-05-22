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

import org.eclipse.wst.xml.xpath2.processor.internal.types.XSDayTimeDuration;
import org.eclipse.wst.xml.xpath2.processor.internal.types.XSYearMonthDuration;

public class ScalarTypePromoter extends NumericTypePromoter {

	protected boolean checkCombination(Class newType) {

		Class targetType = getTargetType();
		if (targetType == XSDayTimeDuration.class || targetType == XSYearMonthDuration.class) {
			return targetType == newType;	
		}
		return super.checkCombination(newType);
	}

	protected Class substitute(Class typeToConsider) {
		if (typeToConsider == XSDayTimeDuration.class || typeToConsider == XSYearMonthDuration.class) {
			return typeToConsider;
		}

		return super.substitute(typeToConsider);
	}
	
}
