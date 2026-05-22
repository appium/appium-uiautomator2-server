(:*******************************************************:)
(: Test: K-SeqExprCast-432                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: A simple test exercising the whitespace facet for type xs:string. :)
(:*******************************************************:)
xs:string("
	 an arbitrary string
	 ")
        eq
        xs:string("
	 an arbitrary string
	 ")
      