(:*******************************************************:)
(: Test: K-SeqExprCast-1060                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: A simple test exercising the whitespace facet for type xs:gMonthDay. :)
(:*******************************************************:)
xs:gMonthDay("
	 --11-13
	 ")
        eq
        xs:gMonthDay("
	 --11-13
	 ")
      