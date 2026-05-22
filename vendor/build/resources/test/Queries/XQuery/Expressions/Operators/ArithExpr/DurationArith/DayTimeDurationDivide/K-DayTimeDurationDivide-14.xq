(:*******************************************************:)
(: Test: K-DayTimeDurationDivide-14                      :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: The division operator is not available between xs:yearMonthDuration and xs:duration. :)
(:*******************************************************:)
xs:yearMonthDuration("P3Y3M") div
						       xs:duration("P3D")