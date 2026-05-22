(:*******************************************************:)
(: Test: K-SeqExprCast-1216                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A simple test exercising the whitespace facet for type xs:boolean. :)
(:*******************************************************:)
xs:boolean("
	 true
	 ")
        eq
        xs:boolean("
	 true
	 ")
      