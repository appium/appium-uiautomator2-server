(:*******************************************************:)
(: Test: K-SeqExprCast-1372                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A simple test exercising the whitespace facet for type xs:anyURI. :)
(:*******************************************************:)
xs:anyURI("
	 http://www.example.com/an/arbitrary/URI.ext
	 ")
        eq
        xs:anyURI("
	 http://www.example.com/an/arbitrary/URI.ext
	 ")
      