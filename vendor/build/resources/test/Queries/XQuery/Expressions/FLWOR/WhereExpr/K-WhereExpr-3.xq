(:*******************************************************:)
(: Test: K-WhereExpr-3                                   :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A where clause containing a value which EBV cannot be extracted from. :)
(:*******************************************************:)
count((for $fo in (1, 2, 3) where xs:time("08:08:23Z") return $fo)) eq 3