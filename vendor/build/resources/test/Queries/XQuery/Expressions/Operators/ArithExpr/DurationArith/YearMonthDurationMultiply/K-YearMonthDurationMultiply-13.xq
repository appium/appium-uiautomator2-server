(:*******************************************************:)
(: Test: K-YearMonthDurationMultiply-13                  :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: The multiplication operator is not available between xs:dayTimeDuration and xs:dayTimeDuration. :)
(:*******************************************************:)
xs:dayTimeDuration("P3D") *
						       xs:dayTimeDuration("P3D")