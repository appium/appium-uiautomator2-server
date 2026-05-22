(:*******************************************************:)
(: Test: K-SeqExprCast-960                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:gYearMonth to xs:untypedAtomic is allowed and should always succeed. :)
(:*******************************************************:)
xs:gYearMonth("1999-11") cast as xs:untypedAtomic
                    ne
                  xs:untypedAtomic("an arbitrary string(untypedAtomic source)")