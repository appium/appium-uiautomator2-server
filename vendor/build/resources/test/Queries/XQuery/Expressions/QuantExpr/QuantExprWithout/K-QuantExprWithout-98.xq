(:*******************************************************:)
(: Test: K-QuantExprWithout-98                           :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: No 'at' declaration is allowed in 'some'-quantification. :)
(:*******************************************************:)
some $a at $p in (1, 2) satisfies $a