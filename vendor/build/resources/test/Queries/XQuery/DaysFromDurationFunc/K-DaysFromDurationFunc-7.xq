(:*******************************************************:)
(: Test: K-DaysFromDurationFunc-7                        :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: Simple test invoking days-from-duration() on an negative xs:duration. :)
(:*******************************************************:)
days-from-duration(xs:duration("-P3Y4M8DT1H23M2.34S")) eq -8