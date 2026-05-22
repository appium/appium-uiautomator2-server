(:*******************************************************:)
(: Test: K-ForExprWithout-56                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Apply fn:count() to a for-expression(#2).    :)
(:*******************************************************:)
count(for $i in (1, 2, timezone-from-time(current-time())) return ($i, $i)) eq 6
or
count(for $i in (1, 2, timezone-from-time(current-time())) return ($i, $i)) eq 4
