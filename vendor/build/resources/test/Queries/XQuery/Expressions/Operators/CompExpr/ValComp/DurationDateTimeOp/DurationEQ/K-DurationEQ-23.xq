(:*******************************************************:)
(: Test: K-DurationEQ-23                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Simple test of 'ne' for xs:dayTimeDuration with xs:duration on the left hand. :)
(:*******************************************************:)
xs:duration("P1M1DT2H") ne
		   xs:dayTimeDuration("P1DT2H")