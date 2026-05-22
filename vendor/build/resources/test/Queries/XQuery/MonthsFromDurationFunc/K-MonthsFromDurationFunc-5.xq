(:*******************************************************:)
(: Test: K-MonthsFromDurationFunc-5                      :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `months-from-duration(xs:yearMonthDuration("P0003Y2M")) eq 2`. :)
(:*******************************************************:)
months-from-duration(xs:yearMonthDuration("P0003Y2M")) eq 2