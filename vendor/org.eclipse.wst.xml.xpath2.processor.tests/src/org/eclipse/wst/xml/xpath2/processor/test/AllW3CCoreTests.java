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
 *     David Carver (STAR) - bug 262765 - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xml.xpath2.processor.test;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.eclipse.wst.xml.xpath2.processor.testsuite.core.*;

public class AllW3CCoreTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for W3C XPath 2.0 test suite.");
		//$JUnit-BEGIN$
		suite.addTestSuite(QNameEQTest.class);
		suite.addTestSuite(SeqUnionTest.class);
		suite.addTestSuite(LogicExprTest.class);
		suite.addTestSuite(ForExprWithoutTest.class);
		suite.addTestSuite(NodeBeforeTest.class);
		suite.addTestSuite(CondExprTest.class);
		suite.addTestSuite(FilterExprTest.class);
		suite.addTestSuite(BooleanEqualTest.class);
		suite.addTestSuite(ParenExprTest.class);
		suite.addTestSuite(LiteralsTest.class);
		suite.addTestSuite(GenCompNETest.class);
		suite.addTestSuite(SeqExceptTest.class);
		suite.addTestSuite(QuantExprWithTest.class);
		suite.addTestSuite(AxesTest.class);
		suite.addTestSuite(Base64BinaryEQTest.class);
		suite.addTestSuite(XQueryCommentTest.class);
		suite.addTestSuite(PrefixFromQNameTest.class);
		suite.addTestSuite(QuantExprTest.class);
		suite.addTestSuite(commaOpTest.class);
		suite.addTestSuite(GenCompGTEQTest.class);
		suite.addTestSuite(ExternalContextExprTest.class);
		suite.addTestSuite(RangeExprTest.class);
		suite.addTestSuite(InternalContextExprTest.class);
		suite.addTestSuite(AbbrAxesTest.class);
		suite.addTestSuite(NodeAfterTest.class);
		suite.addTestSuite(SeqExprInstanceOfTest.class);
		suite.addTestSuite(NodeSameTest.class);
		suite.addTestSuite(BooleanLTTest.class);
		suite.addTestSuite(BooleanGTTest.class);
		suite.addTestSuite(SeqExprCastableTest.class);
		suite.addTestSuite(ReturnExprTest.class);
		suite.addTestSuite(NodeTestTest.class);
		suite.addTestSuite(GenCompLTTest.class);
		suite.addTestSuite(DurationEQTest.class);
		suite.addTestSuite(CombNodeSeqTest.class);
		suite.addTestSuite(SeqIntersectTest.class);
		suite.addTestSuite(NameTestTest.class);
		suite.addTestSuite(NodeNameFuncTest.class);
		suite.addTestSuite(SequenceTypeSyntaxTest.class);
		suite.addTestSuite(GenCompEqTest.class);
		suite.addTestSuite(PredicatesTest.class);
		suite.addTestSuite(SeqExprCastTest.class);
		suite.addTestSuite(GenCompGTTest.class);
		suite.addTestSuite(HexBinaryEQTest.class);
		suite.addTestSuite(UnabbrAxesTest.class);
		suite.addTestSuite(ForExprWithTest.class);
		suite.addTestSuite(GenCompLTEQTest.class);
		//$JUnit-END$
		return suite;
	}

}
