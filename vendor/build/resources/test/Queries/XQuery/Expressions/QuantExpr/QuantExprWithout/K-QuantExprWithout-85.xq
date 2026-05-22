(:*******************************************************:)
(: Test: K-QuantExprWithout-85                           :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Variable which is not in scope.              :)
(:*******************************************************:)
some $a in (1, 2, 3), $b in (1, 2, 3, $b) satisfies $a eq $b