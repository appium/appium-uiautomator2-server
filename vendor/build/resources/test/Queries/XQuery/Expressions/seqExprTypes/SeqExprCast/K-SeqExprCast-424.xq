(:*******************************************************:)
(: Test: K-SeqExprCast-424                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:untypedAtomic to xs:string is allowed and should always succeed. :)
(:*******************************************************:)
xs:untypedAtomic("an arbitrary string(untypedAtomic source)") cast as xs:string
                    ne
                  xs:string("an arbitrary string")