(:*******************************************************:)
(: Test: K-FilterExpr-90                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: Nested predicate with multiple calls to fn:last(). :)
(:*******************************************************:)
(1, 2, 3)[(last(), last())[2]]