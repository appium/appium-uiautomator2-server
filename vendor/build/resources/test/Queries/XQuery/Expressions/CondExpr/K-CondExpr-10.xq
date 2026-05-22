(:*******************************************************:)
(: Test: K-CondExpr-10                                   :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: An if-test applied on fn:count().            :)
(:*******************************************************:)
if(count((1, 2, 3, current-time(), 4))) then true() else 4