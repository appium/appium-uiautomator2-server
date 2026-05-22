(:*******************************************************:)
(: Test: K-QuantExprWithout-75                           :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: The 'return' keyword is not valid in an 'every' expression, it must be 'satisfies'. :)
(:*******************************************************:)
every $foo in (1, $2) return 1