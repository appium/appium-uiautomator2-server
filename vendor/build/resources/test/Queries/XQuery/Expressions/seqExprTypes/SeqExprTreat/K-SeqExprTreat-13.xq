(:*******************************************************:)
(: Test: K-SeqExprTreat-13                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Implementations using the static typing feature, may raise XPTY0004 because one of the operands to the multiply-operator has cardinality 'zero-or-more'. :)
(:*******************************************************:)
(3 treat as xs:integer * * 3) eq 9