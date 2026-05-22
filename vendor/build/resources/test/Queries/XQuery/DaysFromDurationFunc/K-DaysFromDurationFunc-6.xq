(:*******************************************************:)
(: Test: K-DaysFromDurationFunc-6                        :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: Simple test invoking days-from-duration() on a negative duration. :)
(:*******************************************************:)
days-from-duration(xs:dayTimeDuration("-P45678DT8H2M1.03S"))
			eq -45678