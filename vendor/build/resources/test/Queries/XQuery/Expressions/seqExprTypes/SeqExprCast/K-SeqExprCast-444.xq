(:*******************************************************:)
(: Test: K-SeqExprCast-444                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: A simple test exercising the whitespace facet for type xs:float. :)
(:*******************************************************:)
xs:float("
	 3.4e5
	 ")
        eq
        xs:float("
	 3.4e5
	 ")
      