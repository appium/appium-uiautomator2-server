(:*******************************************************:)
(: Test: K-DayTimeDurationDivide-12                      :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: The division operator is not available between xs:yearMonthDuration and xs:dayTimeDuration. :)
(:*******************************************************:)
xs:yearMonthDuration("P3Y3M") div
						       xs:dayTimeDuration("P3D")