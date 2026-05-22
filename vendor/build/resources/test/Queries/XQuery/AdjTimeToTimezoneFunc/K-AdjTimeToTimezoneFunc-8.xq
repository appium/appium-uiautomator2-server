(:*******************************************************:)
(: Test: K-AdjTimeToTimezoneFunc-8                       :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: Passing a xs:dayTimeDuration as timezone to adjust-time-to-timezone() which isn't an integral number of minutes. :)
(:*******************************************************:)
adjust-time-to-timezone(xs:time("08:02:00"),
							xs:dayTimeDuration("PT14H0M0.001S"))