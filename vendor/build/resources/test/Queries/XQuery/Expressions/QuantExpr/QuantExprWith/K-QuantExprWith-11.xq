(:*******************************************************:)
(: Test: K-QuantExprWith-11                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Every-quantification carrying invalid type declarations. :)
(:*******************************************************:)
every $a as empty-sequence() in (1, 2), $b as xs:integer in $a satisfies $b