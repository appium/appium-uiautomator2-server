(:*******************************************************:)
(: Test: K-QuantExprWithout-89                           :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Nested variable bindings can reference each other. :)
(:*******************************************************:)
every $a in (1, 2, 3), $b in ($a, 4) satisfies $b gt 0