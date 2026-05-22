(:*******************************************************:)
(: Test: K-SeqExprCast-956                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: A simple test exercising the whitespace facet for type xs:gYearMonth. :)
(:*******************************************************:)
xs:gYearMonth("
	 1999-11
	 ")
        eq
        xs:gYearMonth("
	 1999-11
	 ")
      