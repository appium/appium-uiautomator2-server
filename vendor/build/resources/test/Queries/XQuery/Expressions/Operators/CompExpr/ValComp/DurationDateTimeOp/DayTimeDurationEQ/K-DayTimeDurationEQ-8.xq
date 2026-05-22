(:*******************************************************:)
(: Test: K-DayTimeDurationEQ-8                           :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Test eq operator for xs:dayTimeDuration values with large milli second component. :)
(:*******************************************************:)
xs:dayTimeDuration("P6DT16H34M59.613999S") eq
		   xs:dayTimeDuration("P6DT16H34M59.613999S")