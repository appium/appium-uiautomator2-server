(:*******************************************************:)
(: Test: K-LogicExpr-12                                  :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Non-empty xs:untypedAtomics in the left branch of an or-expression has an EBV value of true. :)
(:*******************************************************:)
xs:untypedAtomic("a string") or 0