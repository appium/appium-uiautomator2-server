(:*******************************************************:)
(: Test: K-AdjTimeToTimezoneFunc-9                       :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: Test that the implicit timezone in the dynamic context is used if $timezone is empty; indirectly also tests context stability. :)
(:*******************************************************:)
timezone-from-time(adjust-time-to-timezone(xs:time("00:00:00")))
			 eq
			 implicit-timezone()