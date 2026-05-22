(:*******************************************************:)
(: Test: K-SeqExprCast-1112                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A simple test exercising the whitespace facet for type xs:gDay. :)
(:*******************************************************:)
xs:gDay("
	 ---03
	 ")
        eq
        xs:gDay("
	 ---03
	 ")
      