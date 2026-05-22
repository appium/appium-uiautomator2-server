(:*******************************************************:)
(: Test: K-QuantExprWithout-74                           :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: The 'return' keyword is not valid in a 'some' expression, it must be 'satisfies'. :)
(:*******************************************************:)
some $foo in (1, $2) return 1