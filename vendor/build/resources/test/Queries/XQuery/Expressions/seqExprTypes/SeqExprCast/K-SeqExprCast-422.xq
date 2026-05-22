(:*******************************************************:)
(: Test: K-SeqExprCast-422                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:untypedAtomic to xs:untypedAtomic is allowed and should always succeed. :)
(:*******************************************************:)
xs:untypedAtomic("an arbitrary string(untypedAtomic source)") cast as xs:untypedAtomic
                    eq
                  xs:untypedAtomic("an arbitrary string(untypedAtomic source)")