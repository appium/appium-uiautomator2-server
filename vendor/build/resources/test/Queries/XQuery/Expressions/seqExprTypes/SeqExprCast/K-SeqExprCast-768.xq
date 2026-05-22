(:*******************************************************:)
(: Test: K-SeqExprCast-768                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:dayTimeDuration to xs:dayTimeDuration is allowed and should always succeed. :)
(:*******************************************************:)
xs:dayTimeDuration("P3DT2H") cast as xs:dayTimeDuration
                    eq
                  xs:dayTimeDuration("P3DT2H")