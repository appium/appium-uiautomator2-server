(:*******************************************************:)
(: Test: K-CondExpr-12                                   :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: if-then clause combined with fn:boolean().   :)
(:*******************************************************:)
if(boolean((1, 2, 3, current-time())[1] treat as xs:integer)) then true() else 4