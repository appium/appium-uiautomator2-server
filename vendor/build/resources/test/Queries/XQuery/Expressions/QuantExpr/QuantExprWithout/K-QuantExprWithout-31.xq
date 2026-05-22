(:*******************************************************:)
(: Test: K-QuantExprWithout-31                           :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: EBV cannot be extracted fro xs:hexBinary.    :)
(:*******************************************************:)
some $var in (xs:hexBinary("FF"), false(), true()) satisfies $var