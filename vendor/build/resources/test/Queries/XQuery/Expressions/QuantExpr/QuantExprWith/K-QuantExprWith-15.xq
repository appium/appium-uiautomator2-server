(:*******************************************************:)
(: Test: K-QuantExprWith-15                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Some-quantification carrying invalid type declarations. :)
(:*******************************************************:)
some $a as item()* in (1, 2), $b as xs:string in $a satisfies $b