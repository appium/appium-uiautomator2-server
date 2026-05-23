/*******************************************************************************
 * Copyright (c) 2009, 2017 Standards for Technology in Automotive Retail and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     David Carver (STAR) - initial API and implementation based on W3C XPath 2.0
 *                           Test Suite.
 *******************************************************************************/
package org.eclipse.wst.xml.xpath2.processor.test;

import org.eclipse.wst.xml.xpath2.processor.testsuite.numeric.AllW3CNumericTests;
import org.eclipse.wst.xml.xpath2.processor.test.AllW3CCoreTests;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AllW3CXPath20Tests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for W3C XPath 2.0 test suite.");
		//$JUnit-BEGIN$
		suite.addTest(AllW3CNumericTests.suite());
		suite.addTest(AllW3CCoreTests.suite());
		//$JUnit-END$
		return suite;
	}

}
