(:*******************************************************:)
(: Test: K-MonthsFromDurationFunc-6                      :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: Simple test invoking months-from-duration() on a negative duration. :)
(:*******************************************************:)
months-from-duration(xs:yearMonthDuration("-P0003Y2M")) eq -2