(:*******************************************************:)
(: Test: K-SeqExprCast-904                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: A simple test exercising the whitespace facet for type xs:date. :)
(:*******************************************************:)
xs:date("
	 2004-10-13
	 ")
        eq
        xs:date("
	 2004-10-13
	 ")
      