(:*******************************************************:)
(: Test: K-QuantExprWithout-50                           :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A test whose essence is: `some $aaa in (1, 2, 3), $bbb in (3, 2, 1) satisfies $aaa + $bbb eq 4`. :)
(:*******************************************************:)
some $aaa in (1, 2, 3), $bbb in (3, 2, 1) satisfies $aaa + $bbb eq 4