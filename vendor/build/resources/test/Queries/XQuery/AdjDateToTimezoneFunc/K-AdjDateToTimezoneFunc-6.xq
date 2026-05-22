(:*******************************************************:)
(: Test: K-AdjDateToTimezoneFunc-6                       :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: Passing a too large xs:dayTimeDuration as timezone to adjust-date-to-timezone(). :)
(:*******************************************************:)
adjust-date-to-timezone(xs:date("2001-02-03"),
							xs:dayTimeDuration("PT14H1M"))