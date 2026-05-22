(:*******************************************************:)
(: Test: K-AdjDateToTimezoneFunc-13                      :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: Example from F&O.                            :)
(:*******************************************************:)

			adjust-date-to-timezone(xs:date("2002-03-07"), ())
			eq xs:date("2002-03-07")
		