(:*******************************************************:)
(: Test: K-QuantExprWithout-30                           :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: EBV cannot be extracted fro xs:hexBinary.    :)
(:*******************************************************:)
every $var in (true(), true(), xs:hexBinary("FF")) satisfies $var