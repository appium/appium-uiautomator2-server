(:*******************************************************:)
(: Test: K-SeqExprCast-696                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: A simple test exercising the whitespace facet for type xs:yearMonthDuration. :)
(:*******************************************************:)
xs:yearMonthDuration("
	 P1Y12M
	 ")
        eq
        xs:yearMonthDuration("
	 P1Y12M
	 ")
      