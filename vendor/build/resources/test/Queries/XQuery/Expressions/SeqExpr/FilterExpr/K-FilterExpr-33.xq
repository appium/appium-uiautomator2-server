(:*******************************************************:)
(: Test: K-FilterExpr-33                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: The context item is used as the predicate, leading to a truth predicate. :)
(:*******************************************************:)
deep-equal((true(), true(), true()),
(false(), true(), true(), false(), true(), false())[.])