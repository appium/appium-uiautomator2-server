(:*******************************************************:)
(: Test: K-QuantExprWith-12                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Every-quantification carrying invalid type declarations. :)
(:*******************************************************:)
some $a as empty-sequence() in (1, 2), $b as xs:integer in $a satisfies $b