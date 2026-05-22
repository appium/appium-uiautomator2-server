(:*******************************************************:)
(: Test: K-FilterExpr-38                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: xs:anyURI values are invalid predicates.     :)
(:*******************************************************:)
(1, 2, 3)[(xs:anyURI("example.com/"), xs:anyURI("example.com/"))]