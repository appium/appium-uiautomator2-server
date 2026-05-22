(:*******************************************************:)
(: Test: K-SeqExprCast-852                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: A simple test exercising the whitespace facet for type xs:time. :)
(:*******************************************************:)
xs:time("
	 03:20:00-05:00
	 ")
        eq
        xs:time("
	 03:20:00-05:00
	 ")
      