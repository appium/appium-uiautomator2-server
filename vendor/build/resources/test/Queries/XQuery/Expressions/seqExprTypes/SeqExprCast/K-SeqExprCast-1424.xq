(:*******************************************************:)
(: Test: K-SeqExprCast-1424                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A simple test exercising the whitespace facet for type xs:QName. :)
(:*******************************************************:)
xs:QName("
	 ncname
	 ")
        eq
        xs:QName("
	 ncname
	 ")
      