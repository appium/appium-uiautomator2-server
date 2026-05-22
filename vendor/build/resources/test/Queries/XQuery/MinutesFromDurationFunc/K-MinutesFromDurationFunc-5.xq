(:*******************************************************:)
(: Test: K-MinutesFromDurationFunc-5                     :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `minutes-from-duration(xs:dayTimeDuration("P3DT8H2M1.03S")) eq 2`. :)
(:*******************************************************:)
minutes-from-duration(xs:dayTimeDuration("P3DT8H2M1.03S")) eq 2