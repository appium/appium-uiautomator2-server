(:*******************************************************:)
(: Test: K-DurationEQ-15                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Simple test of 'eq' for xs:dayTimeDuration with xs:duration on the left hand. Both values are zero. :)
(:*******************************************************:)
xs:duration("PT0S") eq
		   xs:dayTimeDuration("PT0S")