(:*******************************************************:)
(: Test: K-LogicExpr-8                                   :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Non-empty xs:anyURIs in the left branch of an or-expression has an EBV value of true. :)
(:*******************************************************:)
xs:anyURI("example.com/") or 0