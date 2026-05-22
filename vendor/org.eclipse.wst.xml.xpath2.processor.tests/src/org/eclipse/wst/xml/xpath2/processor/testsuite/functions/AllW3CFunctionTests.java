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
 *     Standards for Technology in Automotive Retail - initial API and implementation
 *******************************************************************************/
package org.eclipse.wst.xml.xpath2.processor.testsuite.functions;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllW3CFunctionTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(
				"Test for org.eclipse.wst.xml.xpath2.processor.testsuite.functions");
		//$JUnit-BEGIN$
		suite.addTestSuite(ContextLastFuncTest.class);
		suite.addTestSuite(SeqExactlyOneFuncTest.class);
		suite.addTestSuite(StringJoinFuncTest.class);
		suite.addTestSuite(LowerCaseFuncTest.class);
		suite.addTestSuite(TokenizeFuncTest.class);
		suite.addTestSuite(CodepointToStringFuncTest.class);
		suite.addTestSuite(ResolveURIFuncTest.class);
		suite.addTestSuite(RoundEvenFuncTest.class);
		suite.addTestSuite(MinutesFromDurationFuncTest.class);
		suite.addTestSuite(YearFromDateTimeFuncTest.class);
		suite.addTestSuite(EndsWithFuncTest.class);
		suite.addTestSuite(ErrorFuncTest.class);
		suite.addTestSuite(SeqSubsequenceFuncTest.class);
		suite.addTestSuite(DayFromDateFuncTest.class);
		suite.addTestSuite(EncodeURIfuncTest.class);
		suite.addTestSuite(FloorFuncTest.class);
		suite.addTestSuite(TimezoneFromDateFuncTest.class);
		suite.addTestSuite(SeqSUMFuncTest.class);
		suite.addTestSuite(DateTimeFuncTest.class);
		suite.addTestSuite(RoundFuncTest.class);
		suite.addTestSuite(SubstringFuncTest.class);
		suite.addTestSuite(StaticBaseURIFuncTest.class);
		suite.addTestSuite(StartsWithFuncTest.class);
		suite.addTestSuite(AdjDateToTimezoneFuncTest.class);
		suite.addTestSuite(ContextCurrentDateFuncTest.class);
		suite.addTestSuite(StringLengthFuncTest.class);
		suite.addTestSuite(CeilingFuncTest.class);
		suite.addTestSuite(TrueFuncTest.class);
		suite.addTestSuite(NodeNumberFuncTest.class);
		suite.addTestSuite(NormalizeUnicodeFuncTest.class);
		suite.addTestSuite(NotFuncTest.class);
		suite.addTestSuite(IRIToURIfuncTest.class);
		suite.addTestSuite(SeqExistsFuncTest.class);
		suite.addTestSuite(InScopePrefixesFuncTest.class);
		suite.addTestSuite(UpperCaseFuncTest.class);
		suite.addTestSuite(HoursFromDurationFuncTest.class);
		suite.addTestSuite(TimezoneFromDateTimeFuncTest.class);
		suite.addTestSuite(MonthFromDateFuncTest.class);
		suite.addTestSuite(BaseURIFuncTest.class);
		suite.addTestSuite(SeqDeepEqualFuncTest.class);
		suite.addTestSuite(ConcatFuncTest.class);
		suite.addTestSuite(NodeRootFuncTest.class);
		suite.addTestSuite(SeqReverseFuncTest.class);
		suite.addTestSuite(ContextDefaultCollationFuncTest.class);
		suite.addTestSuite(SeqRemoveFuncTest.class);
		suite.addTestSuite(ContextCurrentDatetimeFuncTest.class);
		suite.addTestSuite(SeqIndexOfFuncTest.class);
		suite.addTestSuite(HoursFromDateTimeFuncTest.class);
		suite.addTestSuite(SeqInsertBeforeFuncTest.class);
		suite.addTestSuite(SeqUnorderedFuncTest.class);
		suite.addTestSuite(SubstringBeforeFuncTest.class);
		suite.addTestSuite(SurrogatesTest.class);
		suite.addTestSuite(NameFuncTest.class);
		suite.addTestSuite(SecondsFromTimeFuncTest.class);
		suite.addTestSuite(SeqCountFuncTest.class);
		suite.addTestSuite(SeqBooleanFuncTest.class);
		suite.addTestSuite(ReplaceFuncTest.class);
		suite.addTestSuite(NodeLocalNameFuncTest.class);
		suite.addTestSuite(StringFuncTest.class);
		suite.addTestSuite(ContainsFuncTest.class);
		suite.addTestSuite(ContextImplicitTimezoneFuncTest.class);
		suite.addTestSuite(EscapeHTMLURIFuncTest.class);
		suite.addTestSuite(SecondsFromDateTimeFuncTest.class);
		suite.addTestSuite(SeqMINFuncTest.class);
		suite.addTestSuite(TimezoneFromTimeFuncTest.class);
		suite.addTestSuite(ContextPositionFuncTest.class);
		suite.addTestSuite(SeqCollectionFuncTest.class);
		suite.addTestSuite(LocalNameFromQNameFuncTest.class);
		suite.addTestSuite(ContextCurrentTimeFuncTest.class);
		suite.addTestSuite(TraceFuncTest.class);
		suite.addTestSuite(MonthFromDateTimeFuncTest.class);
		suite.addTestSuite(TranslateFuncTest.class);
		suite.addTestSuite(SeqAVGFuncTest.class);
		suite.addTestSuite(NodeNamespaceURIFuncTest.class);
		suite.addTestSuite(ABSFuncTest.class);
		suite.addTestSuite(SeqDocFuncTest.class);
		suite.addTestSuite(AdjDateTimeToTimezoneFuncTest.class);
		suite.addTestSuite(FalseFuncTest.class);
		suite.addTestSuite(SeqOneOrMoreFuncTest.class);
		suite.addTestSuite(MonthsFromDurationFuncTest.class);
		suite.addTestSuite(SeqZeroOrOneFuncTest.class);
		suite.addTestSuite(compareFuncTest.class);
		suite.addTestSuite(NodeLangFuncTest.class);
		suite.addTestSuite(MinutesFromDateTimeFuncTest.class);
		suite.addTestSuite(HoursFromTimeFuncTest.class);
		suite.addTestSuite(SeqIDREFFuncTest.class);
		suite.addTestSuite(NamespaceURIFromQNameFuncTest.class);
		suite.addTestSuite(StringToCodepointFuncTest.class);
		suite.addTestSuite(SeqMAXFuncTest.class);
		suite.addTestSuite(AdjTimeToTimezoneFuncTest.class);
		suite.addTestSuite(MinutesFromTimeFuncTest.class);
		suite.addTestSuite(SubstringAfterFuncTest.class);
		suite.addTestSuite(CodepointEqualTest.class);
		suite.addTestSuite(YearFromDateFuncTest.class);
		suite.addTestSuite(YearsFromDurationFuncTest.class);
		suite.addTestSuite(NilledFuncTest.class);
		suite.addTestSuite(DataFuncTest.class);
		suite.addTestSuite(NormalizeSpaceFuncTest.class);
		suite.addTestSuite(DayFromDateTimeFuncTest.class);
		suite.addTestSuite(MatchesFuncTest.class);
		suite.addTestSuite(SeqDistinctValuesFuncTest.class);
		suite.addTestSuite(SecondsFromDurationFuncTest.class);
		suite.addTestSuite(SeqIDFuncTest.class);
		suite.addTestSuite(DaysFromDurationFuncTest.class);
		suite.addTestSuite(DocumentURIFuncTest.class);
		suite.addTestSuite(ResolveQNameFuncTest.class);
		//$JUnit-END$
		return suite;
	}

}
