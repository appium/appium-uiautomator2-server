(:*******************************************************:)
(: Test: K-DurationEQ-20                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Simple test of 'eq' for xs:dayTimeDuration with xs:dayTimeDuration on the left hand. Both values are zero. :)
(:*******************************************************:)
xs:dayTimeDuration("PT0S")
	   		      eq
			      xs:yearMonthDuration("P0M")