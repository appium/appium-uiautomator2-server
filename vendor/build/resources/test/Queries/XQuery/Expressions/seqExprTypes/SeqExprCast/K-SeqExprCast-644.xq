(:*******************************************************:)
(: Test: K-SeqExprCast-644                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: A simple test exercising the whitespace facet for type xs:duration. :)
(:*******************************************************:)
xs:duration("
	 P1Y2M3DT10H30M
	 ")
        eq
        xs:duration("
	 P1Y2M3DT10H30M
	 ")
      