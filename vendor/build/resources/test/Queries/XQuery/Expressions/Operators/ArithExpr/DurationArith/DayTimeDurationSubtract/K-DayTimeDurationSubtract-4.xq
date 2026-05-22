(:*******************************************************:)
(: Test: K-DayTimeDurationSubtract-4                     :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: The substraction operator is not available between xs:dayTimeDuration and xs:yearMonthDuration. :)
(:*******************************************************:)
xs:dayTimeDuration("P3D") -
						       xs:yearMonthDuration("P3Y3M")