(:*******************************************************:)
(: Test: K-SeqExprCast-1168                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Casting from xs:gMonth to xs:untypedAtomic is allowed and should always succeed. :)
(:*******************************************************:)
xs:gMonth("--11") cast as xs:untypedAtomic
                    ne
                  xs:untypedAtomic("an arbitrary string(untypedAtomic source)")