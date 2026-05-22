(:*******************************************************:)
(: Test: K-SeqExprCast-1268                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A simple test exercising the whitespace facet for type xs:base64Binary. :)
(:*******************************************************:)
xs:base64Binary("
	 aaaa
	 ")
        eq
        xs:base64Binary("
	 aaaa
	 ")
      