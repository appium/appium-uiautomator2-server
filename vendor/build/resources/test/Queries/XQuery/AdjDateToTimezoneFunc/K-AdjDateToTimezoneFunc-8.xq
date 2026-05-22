(:*******************************************************:)
(: Test: K-AdjDateToTimezoneFunc-8                       :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: Passing a xs:dayTimeDuration as timezone to adjust-date-to-timezone() which isn't an integral number of minutes. :)
(:*******************************************************:)
adjust-date-to-timezone(xs:date("2001-02-03"),
							xs:dayTimeDuration("PT14H0M0.001S"))