(:*******************************************************:)
(: Test: K-QuantExprWithout-93                           :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Nested variable bindings can reference each other. :)
(:*******************************************************:)
deep-equal((for $a in 1, $b in $a, $c in $a, $d in $c return ($a, $b, $c, $d)),
(1, 1, 1, 1))