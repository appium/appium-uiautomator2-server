(:*******************************************************:)
(: Test: K-AdjDateTimeToTimezoneFunc-9                   :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: Passing a xs:dayTimeDuration as timezone to adjust-dateTime-to-timezone() which isn't an integral number of minutes. :)
(:*******************************************************:)
adjust-dateTime-to-timezone(xs:dateTime("2001-02-03T08:02:00"),
			   xs:dayTimeDuration("PT14H0M0.001S"))
		