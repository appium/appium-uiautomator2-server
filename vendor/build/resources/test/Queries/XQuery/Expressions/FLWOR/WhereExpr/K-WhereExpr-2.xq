(:*******************************************************:)
(: Test: K-WhereExpr-2                                   :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Get the string value of the return statement of a for expression after being filtered by a where clause. :)
(:*******************************************************:)
string((for $fo in (1, 2, 3) where $fo eq 3 return $fo)) eq "3"