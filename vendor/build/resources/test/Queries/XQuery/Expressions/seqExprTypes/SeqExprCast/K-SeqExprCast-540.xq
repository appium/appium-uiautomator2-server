(:*******************************************************:)
(: Test: K-SeqExprCast-540                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: A simple test exercising the whitespace facet for type xs:decimal. :)
(:*******************************************************:)
xs:decimal("
	 10.01
	 ")
        eq
        xs:decimal("
	 10.01
	 ")
      