(:*******************************************************:)
(: Test: K-QuantExprWith-14                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Some-quantification carrying invalid type declarations. :)
(:*******************************************************:)
some $a as xs:integer+ in (1, 2), $b as xs:string* in $a satisfies $b