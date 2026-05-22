(:*******************************************************:)
(: Test: K-DayTimeDurationSubtract-7                     :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: The substraction operator is not available between xs:yearMonthDuration and xs:duration. :)
(:*******************************************************:)
xs:yearMonthDuration("P3Y3M") -
						       xs:duration("P3D")