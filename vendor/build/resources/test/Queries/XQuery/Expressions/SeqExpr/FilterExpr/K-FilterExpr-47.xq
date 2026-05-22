(:*******************************************************:)
(: Test: K-FilterExpr-47                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: An excessive nesting of various predicates.  :)
(:*******************************************************:)
(0, 2, 4, 5)[1][1][1][true()][1][true()][1] eq 0