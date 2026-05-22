(:*******************************************************:)
(: Test: K-QuantExprWithout-33                           :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Since EBV cannot be extracted from xs:hexBinary, FORG0006 is allowed. :)
(:*******************************************************:)
some $var in (true(), true(), xs:hexBinary("FF")) satisfies $var