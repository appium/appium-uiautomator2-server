(:*******************************************************:)
(: Test: K-YearMonthDurationMultiply-11                  :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: The multiplication operator is not available between xs:yearMonthDuration and xs:dayTimeDuration. :)
(:*******************************************************:)
xs:yearMonthDuration("P3Y3M") *
						       xs:dayTimeDuration("P3D")