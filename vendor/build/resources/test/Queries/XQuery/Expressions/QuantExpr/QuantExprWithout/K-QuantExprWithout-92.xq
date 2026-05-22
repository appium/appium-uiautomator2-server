(:*******************************************************:)
(: Test: K-QuantExprWithout-92                           :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Nested variable bindings can reference each other. :)
(:*******************************************************:)
some $a in (1, 2), $b in $a satisfies $b