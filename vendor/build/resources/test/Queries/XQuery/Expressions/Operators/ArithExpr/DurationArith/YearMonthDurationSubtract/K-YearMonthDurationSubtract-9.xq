(:*******************************************************:)
(: Test: K-YearMonthDurationSubtract-9                   :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: The addition operator is not available between xs:duration and xs:dayTimeDuration. :)
(:*******************************************************:)
xs:duration("P3Y3M") +
						       xs:dayTimeDuration("P3D")