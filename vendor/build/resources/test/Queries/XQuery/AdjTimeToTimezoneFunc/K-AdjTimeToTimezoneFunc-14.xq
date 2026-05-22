(:*******************************************************:)
(: Test: K-AdjTimeToTimezoneFunc-14                      :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: Example from F&O.                            :)
(:*******************************************************:)

			adjust-time-to-timezone(xs:time("10:00:00"),
						())
			eq xs:time("10:00:00")
		