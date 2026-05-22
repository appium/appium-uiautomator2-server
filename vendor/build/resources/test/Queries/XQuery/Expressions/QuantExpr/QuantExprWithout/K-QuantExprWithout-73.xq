(:*******************************************************:)
(: Test: K-QuantExprWithout-73                           :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Variable which is not in scope.              :)
(:*******************************************************:)
every $a in (1, 2), $b in (1, 2), $c in (1, 2) satisfies 1, $c