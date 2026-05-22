(:*******************************************************:)
(: Test: K-SeqExprCast-418                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: A simple test exercising the whitespace facet for type xs:untypedAtomic. :)
(:*******************************************************:)
xs:untypedAtomic("
	 an arbitrary string(untypedAtomic source)
	 ")
        eq
        xs:untypedAtomic("
	 an arbitrary string(untypedAtomic source)
	 ")
      