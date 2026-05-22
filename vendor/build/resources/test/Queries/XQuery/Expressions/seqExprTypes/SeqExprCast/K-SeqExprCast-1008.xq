(:*******************************************************:)
(: Test: K-SeqExprCast-1008                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: A simple test exercising the whitespace facet for type xs:gYear. :)
(:*******************************************************:)
xs:gYear("
	 1999
	 ")
        eq
        xs:gYear("
	 1999
	 ")
      