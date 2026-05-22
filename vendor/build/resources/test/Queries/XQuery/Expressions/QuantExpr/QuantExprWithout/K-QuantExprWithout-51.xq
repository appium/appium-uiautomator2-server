(:*******************************************************:)
(: Test: K-QuantExprWithout-51                           :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A test whose essence is: `every $aaa in (3, 3, 3), $bbb in (3, 3, 3) satisfies $aaa + $bbb eq 6`. :)
(:*******************************************************:)
every $aaa in (3, 3, 3), $bbb in (3, 3, 3) satisfies $aaa + $bbb eq 6