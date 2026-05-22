(:*******************************************************:)
(: Test: K-AdjDateTimeToTimezoneFunc-10                  :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: Example from F&O.                            :)
(:*******************************************************:)

			adjust-dateTime-to-timezone(xs:dateTime("2002-03-07T10:00:00"),
						    xs:dayTimeDuration("-PT5H0M"))
			eq xs:dateTime("2002-03-07T10:00:00-05:00")
		