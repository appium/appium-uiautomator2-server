(:*******************************************************:)
(: Test: K-DurationEQ-28                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Simple test of 'eq' for xs:dayTimeDuration with xs:duration on the left hand. :)
(:*******************************************************:)
xs:duration("P1Y12M0D")
	   		      eq
			      xs:yearMonthDuration("P1Y12M")