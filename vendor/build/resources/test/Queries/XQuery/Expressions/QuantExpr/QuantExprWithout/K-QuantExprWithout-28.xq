(:*******************************************************:)
(: Test: K-QuantExprWithout-28                           :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: EBV cannot be extracted fro xs:hexBinary.    :)
(:*******************************************************:)
every $var in (xs:hexBinary("FF"), true(), true()) satisfies $var