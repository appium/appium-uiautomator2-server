/*******************************************************************************
 * Copyright (c) 2005, 2011 Andrea Bittau, University College London, and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Andrea Bittau - initial API and implementation from the PsychoPath XPath 2.0
 *     David Carver - bug 280547 - fix dates for comparison 
 *     Jesper Steen Moller  - bug 262765 - fix type tests
 *******************************************************************************/

package org.eclipse.wst.xml.xpath2.processor.internal.types;

import java.util.Calendar;
import java.util.GregorianCalendar;

// common base for anything that uses a calendar... basically stuff doing with
// time... hopefully in the future this may be factored out here
/**
 * Common base for all Calendar based classes
 */
public abstract class CalendarType extends CtrType {

	public abstract Calendar calendar();
	
	public Calendar normalizeCalendar(Calendar cal, XSDuration timezone) {
		Calendar adjusted = (Calendar) cal.clone();
		
		if (timezone != null) {
			int hours = timezone.hours();
			int minutes = timezone.minutes();
			if (!timezone.negative()) {
				hours *= -1;
				minutes *= -1;
			}
			adjusted.add(Calendar.HOUR_OF_DAY, hours);
			adjusted.add(Calendar.MINUTE, minutes);
		}
		
		return adjusted;
		
	}

	protected boolean isGDataType(AnyType aat) {
		if (! (aat instanceof AnyAtomicType)) return false;
	
		String type = aat.string_type();
		if (type.equals("xs:gMonthDay") ||
			type.equals("xs:gDay") ||
			type.equals("xs:gMonth") ||
			type.equals("xs:gYear") ||
			type.equals("xs:gYearMonth")) {
			return true;
		}
		return false;
	}
	
	public Object getNativeValue() {
		return _datatypeFactory.newXMLGregorianCalendar((GregorianCalendar)calendar());
	}
}
