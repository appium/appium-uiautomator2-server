(:*******************************************************:)
(: Test: K-AdjDateTimeToTimezoneFunc-14                  :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: Example from F&O.                            :)
(:*******************************************************:)

			adjust-dateTime-to-timezone(xs:dateTime("2002-03-07T10:00:00-07:00"),
						    xs:dayTimeDuration("PT10H"))
			eq xs:dateTime("2002-03-08T03:00:00+10:00")
		