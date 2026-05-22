(:*******************************************************:)
(: Test: K-QuantExprWith-19                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: No 'at' declaration is allowed in 'some'-quantification. :)
(:*******************************************************:)
some $a as item() at $p in (1, 2) satisfies $a