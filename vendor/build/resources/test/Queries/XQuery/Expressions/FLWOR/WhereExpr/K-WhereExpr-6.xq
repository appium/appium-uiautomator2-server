(:*******************************************************:)
(: Test: K-WhereExpr-6                                   :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A for expression binding to one single value, combined with a negative where clause. :)
(:*******************************************************:)
(for $i in 1 where true() return $i) eq 1