(:*******************************************************:)
(: Test: K-SeqExprCast-1320                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A simple test exercising the whitespace facet for type xs:hexBinary. :)
(:*******************************************************:)
xs:hexBinary("
	 0FB7
	 ")
        eq
        xs:hexBinary("
	 0FB7
	 ")
      