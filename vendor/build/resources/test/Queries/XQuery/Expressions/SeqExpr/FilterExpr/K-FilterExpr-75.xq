(:*******************************************************:)
(: Test: K-FilterExpr-75                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: fn:last() in predicate, leading to invalid operator mapping. :)
(:*******************************************************:)
2 eq (0, 1, "2")[last()]