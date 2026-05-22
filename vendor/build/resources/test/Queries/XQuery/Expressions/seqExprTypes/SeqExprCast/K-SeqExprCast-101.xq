(:*******************************************************:)
(: Test: K-SeqExprCast-101                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A test whose essence is: `count(xs:hexBinary(xs:hexBinary("03"))) eq 1`. :)
(:*******************************************************:)
count(xs:hexBinary(xs:hexBinary("03"))) eq 1