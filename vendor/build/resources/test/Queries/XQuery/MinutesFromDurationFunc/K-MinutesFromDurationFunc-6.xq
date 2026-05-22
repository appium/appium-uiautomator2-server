(:*******************************************************:)
(: Test: K-MinutesFromDurationFunc-6                     :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: Simple test invoking minutes-from-duration() on a negative duration. :)
(:*******************************************************:)
minutes-from-duration(xs:dayTimeDuration("-P3DT8H2M1.03S")) eq -2