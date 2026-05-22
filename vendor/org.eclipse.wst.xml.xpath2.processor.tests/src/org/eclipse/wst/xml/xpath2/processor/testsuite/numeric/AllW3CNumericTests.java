/*******************************************************************************
 * Copyright (c) 2009, 2018 IBM Corporation and others.
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
package org.eclipse.wst.xml.xpath2.processor.testsuite.numeric;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllW3CNumericTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for org.eclipse.wst.xml.xpath2.processor.testsuite.numeric");
		//$JUnit-BEGIN$
		suite.addTestSuite(NumericUnaryPlusTest.class);
		suite.addTestSuite(NumericAddTest.class);
		suite.addTestSuite(NumericUnaryMinusTest.class);
		suite.addTestSuite(NumericMultiplyTest.class);
		suite.addTestSuite(NumericModTest.class);
		suite.addTestSuite(NumericGTTest.class);
		suite.addTestSuite(NumericIntegerDivideTest.class);
		suite.addTestSuite(NumericEqualTest.class);
		suite.addTestSuite(NumericLTTest.class);
		suite.addTestSuite(NumericSubtractTest.class);
		suite.addTestSuite(NumericDivideTest.class);
		//$JUnit-END$
		return suite;
	}

}
