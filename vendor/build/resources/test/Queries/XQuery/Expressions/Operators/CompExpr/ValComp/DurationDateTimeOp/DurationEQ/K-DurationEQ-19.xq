(:*******************************************************:)
(: Test: K-DurationEQ-19                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Simple test of 'eq' for xs:yearMonthDuration with xs:dayTimeDuration on the right hand. Both values are zero. :)
(:*******************************************************:)
xs:yearMonthDuration("P0M") eq
		   xs:dayTimeDuration("PT0S")