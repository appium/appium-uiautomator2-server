(:*******************************************************:)
(: Test: K-AdjTimeToTimezoneFunc-6                       :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: Passing a too large xs:dayTimeDuration as timezone to adjust-time-to-timezone(). :)
(:*******************************************************:)
adjust-time-to-timezone(xs:time("08:02:00"),
							xs:dayTimeDuration("PT14H1M"))