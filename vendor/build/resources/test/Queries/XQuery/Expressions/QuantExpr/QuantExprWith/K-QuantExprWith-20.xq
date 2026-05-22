(:*******************************************************:)
(: Test: K-QuantExprWith-20                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: No 'at' declaration is allowed in 'every'-quantification. :)
(:*******************************************************:)
every $a as item() at $p in (1, 2) satisfies $a