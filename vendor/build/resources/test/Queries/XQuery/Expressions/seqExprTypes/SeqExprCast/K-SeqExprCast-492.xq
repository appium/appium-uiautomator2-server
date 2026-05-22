(:*******************************************************:)
(: Test: K-SeqExprCast-492                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: A simple test exercising the whitespace facet for type xs:double. :)
(:*******************************************************:)
xs:double("
	 3.3e3
	 ")
        eq
        xs:double("
	 3.3e3
	 ")
      