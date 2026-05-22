(:*******************************************************:)
(: Test: K-DurationEQ-25                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Simple test of 'eq' for xs:yearMonthDuration with xs:dayTimeDuration on the left hand. :)
(:*******************************************************:)
xs:yearMonthDuration("P0M") eq
		   xs:dayTimeDuration("PT0S")