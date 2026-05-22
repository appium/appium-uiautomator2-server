(:*******************************************************:)
(: Test: K-YearsFromDurationFunc-6                       :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: Simple test invoking years-from-duration() on a negative duration. :)
(:*******************************************************:)
years-from-duration(xs:yearMonthDuration("-P0003Y2M")) eq -3