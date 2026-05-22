(:*******************************************************:)
(: Test: K-AdjDateTimeToTimezoneFunc-8                   :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: Passing a too small xs:dayTimeDuration as timezone to adjust-dateTime-to-timezone(). :)
(:*******************************************************:)
adjust-dateTime-to-timezone(xs:dateTime("2001-02-03T08:02:00"),
							xs:dayTimeDuration("-PT14H1M"))