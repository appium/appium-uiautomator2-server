(:*******************************************************:)
(: Test: K-GenCompEq-34                                  :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: General comparison involving remove(), resulting in operands that require conversion to numeric from xs:untypedAtomic. Implementations supporting the static typing feature may raise XPTY0004. :)
(:*******************************************************:)
(remove((xs:untypedAtomic("6"), "a string"), 2)) = 6