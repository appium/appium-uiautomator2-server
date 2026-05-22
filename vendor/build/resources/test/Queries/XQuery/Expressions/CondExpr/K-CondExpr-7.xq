(:*******************************************************:)
(: Test: K-CondExpr-7                                    :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: An if-test which EBV cannot be extracted from. :)
(:*******************************************************:)
(if(current-time()) then 1 else 0) eq 1