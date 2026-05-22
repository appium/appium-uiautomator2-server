(:*******************************************************:)
(: Test: K-QuantExprWithout-82                           :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Variable which is not in scope.              :)
(:*******************************************************:)
every $foo in ($foo, 2, 3) satisfies 1