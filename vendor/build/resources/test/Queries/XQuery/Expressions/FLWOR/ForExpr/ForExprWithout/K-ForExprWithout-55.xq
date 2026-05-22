(:*******************************************************:)
(: Test: K-ForExprWithout-55                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Apply fn:count() to a for-expression.        :)
(:*******************************************************:)
count(for $i in (1, 2, current-time()) return ($i, $i)) eq 6