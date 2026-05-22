/*******************************************************************************
 * Copyright (c) 2009, 2018 Standards for Technology in Automotive Retail and others.
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
package org.eclipse.wst.xml.xpath2.processor.test;

import org.eclipse.wst.xml.xpath2.processor.testsuite.dates.*;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllW3CDateTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for org.eclipse.wst.xml.xpath2.processor.testsuite.dates");
		//$JUnit-BEGIN$
		suite.addTestSuite(YearMonthDurationAddDTTest.class);
		suite.addTestSuite(gDayEQTest.class);
		suite.addTestSuite(YearMonthDurationMultiplyTest.class);
		suite.addTestSuite(TimeLTTest.class);
		suite.addTestSuite(DateLTTest.class);
		suite.addTestSuite(TimeAddDTDTest.class);
		suite.addTestSuite(YearMonthDurationLTTest.class);
		suite.addTestSuite(dateTimesSubtractTest.class);
		suite.addTestSuite(YearMonthDurationGTTest.class);
		suite.addTestSuite(DateAddDTDTest.class);
		suite.addTestSuite(gMonthDayEQTest.class);
		suite.addTestSuite(DateTimeLTTest.class);
		suite.addTestSuite(DayTimeDurationSubtractTest.class);
		suite.addTestSuite(DateTimeSubtractYMDTest.class);
		suite.addTestSuite(DateTimeAddDTDTest.class);
		suite.addTestSuite(DateTimeSubtractDTDTest.class);
		suite.addTestSuite(DayTimeDurationAddTest.class);
		suite.addTestSuite(DateAddYMDTest.class);
		suite.addTestSuite(DateTimeGTTest.class);
		suite.addTestSuite(YearMonthDurationSubtractTest.class);
		suite.addTestSuite(YearMonthDurationAddTest.class);
		suite.addTestSuite(DayTimeDurationMultiplyTest.class);
		suite.addTestSuite(gYearMonthEQTest.class);
		suite.addTestSuite(DayTimeDurationLTTest.class);
		suite.addTestSuite(DatesSubtractTest.class);
		suite.addTestSuite(DateSubtractDTDTest.class);
		suite.addTestSuite(gYearEQTest.class);
		suite.addTestSuite(DateSubtractYMDTest.class);
		suite.addTestSuite(DateTimeEQTest.class);
		suite.addTestSuite(DayTimeDurationDivideDTDTest.class);
		suite.addTestSuite(DayTimeDurationDivideTest.class);
		suite.addTestSuite(TimeEQTest.class);
		suite.addTestSuite(TimeSubtractTest.class);
		suite.addTestSuite(gMonthEQTest.class);
		suite.addTestSuite(DateEQTest.class);
		suite.addTestSuite(TimeSubtractDTDTest.class);
		suite.addTestSuite(TimeGTTest.class);
		suite.addTestSuite(DateGTTest.class);
		suite.addTestSuite(YearMonthDurationDivideYMDTest.class);
		suite.addTestSuite(DayTimeDurationGTTest.class);
		suite.addTestSuite(YearMonthDurationDivideTest.class);
		//$JUnit-END$
		return suite;
	}

}
