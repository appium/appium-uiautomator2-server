(:*******************************************************:)
(: Test: K-SeqExprCast-448                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:float to xs:untypedAtomic is allowed and should always succeed. :)
(:*******************************************************:)
xs:float("3.4e5") cast as xs:untypedAtomic
                    ne
                  xs:untypedAtomic("an arbitrary string(untypedAtomic source)")