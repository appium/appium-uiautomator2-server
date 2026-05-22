(:*******************************************************:)
(: Test: K-SeqExprTreat-11                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Implementations using the static typing feature, may raise XPTY0004 because one of the operands to operator 'eq' has cardinality 'one-or-more'. :)
(:*******************************************************:)
("asda" treat as xs:string +) eq "asda"