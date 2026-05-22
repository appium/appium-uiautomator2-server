(:*******************************************************:)
(: Test: K-SeqExprCast-544                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:decimal to xs:untypedAtomic is allowed and should always succeed. :)
(:*******************************************************:)
xs:decimal("10.01") cast as xs:untypedAtomic
                    ne
                  xs:untypedAtomic("an arbitrary string(untypedAtomic source)")