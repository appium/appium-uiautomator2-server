(:*******************************************************:)
(: Test: K-SeqExprCast-1427                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Casting from xs:QName to xs:untypedAtomic is allowed and should always succeed. :)
(:*******************************************************:)
xs:QName("ncname") cast as xs:untypedAtomic
                    ne
                  xs:untypedAtomic("an arbitrary string(untypedAtomic source)")