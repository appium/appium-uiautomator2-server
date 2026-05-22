(:*******************************************************:)
(: Test: K-SeqExprCast-496                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:double to xs:untypedAtomic is allowed and should always succeed. :)
(:*******************************************************:)
xs:double("3.3e3") cast as xs:untypedAtomic
                    ne
                  xs:untypedAtomic("an arbitrary string(untypedAtomic source)")