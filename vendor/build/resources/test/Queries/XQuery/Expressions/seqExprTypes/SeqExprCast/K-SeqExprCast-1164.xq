(:*******************************************************:)
(: Test: K-SeqExprCast-1164                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A simple test exercising the whitespace facet for type xs:gMonth. :)
(:*******************************************************:)
xs:gMonth("
	 --11
	 ")
        eq
        xs:gMonth("
	 --11
	 ")
      