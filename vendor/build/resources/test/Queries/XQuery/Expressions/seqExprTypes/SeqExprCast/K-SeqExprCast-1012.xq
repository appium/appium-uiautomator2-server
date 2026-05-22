(:*******************************************************:)
(: Test: K-SeqExprCast-1012                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:gYear to xs:untypedAtomic is allowed and should always succeed. :)
(:*******************************************************:)
xs:gYear("1999") cast as xs:untypedAtomic
                    ne
                  xs:untypedAtomic("an arbitrary string(untypedAtomic source)")