(:*******************************************************:)
(: Test: K-SeqExprCast-1064                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:gMonthDay to xs:untypedAtomic is allowed and should always succeed. :)
(:*******************************************************:)
xs:gMonthDay("--11-13") cast as xs:untypedAtomic
                    ne
                  xs:untypedAtomic("an arbitrary string(untypedAtomic source)")