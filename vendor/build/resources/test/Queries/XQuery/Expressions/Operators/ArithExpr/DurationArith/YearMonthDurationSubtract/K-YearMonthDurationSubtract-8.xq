(:*******************************************************:)
(: Test: K-YearMonthDurationSubtract-8                   :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: The addition operator is not available between xs:dayTimeDuration and xs:duration. :)
(:*******************************************************:)
xs:dayTimeDuration("P3D") +
						       xs:duration("P3Y3M")