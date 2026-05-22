(:*******************************************************:)
(: Test: K-FilterExpr-40                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: Multiple xs:untypedAtomic values is an invalid predicate. :)
(:*******************************************************:)
(1, 2, 3)[(xs:untypedAtomic("content"), xs:untypedAtomic("content"))]