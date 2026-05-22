(:*******************************************************:)
(: Test: K-SeqExprCast-800                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: A simple test exercising the whitespace facet for type xs:dateTime. :)
(:*******************************************************:)
xs:dateTime("
	 2002-10-10T12:00:00-05:00
	 ")
        eq
        xs:dateTime("
	 2002-10-10T12:00:00-05:00
	 ")
      