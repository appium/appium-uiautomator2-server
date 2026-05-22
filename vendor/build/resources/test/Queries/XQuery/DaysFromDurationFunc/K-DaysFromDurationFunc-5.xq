(:*******************************************************:)
(: Test: K-DaysFromDurationFunc-5                        :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `days-from-duration(xs:dayTimeDuration("P45678DT8H2M1.03S")) eq 45678`. :)
(:*******************************************************:)
days-from-duration(xs:dayTimeDuration("P45678DT8H2M1.03S"))
			eq 45678