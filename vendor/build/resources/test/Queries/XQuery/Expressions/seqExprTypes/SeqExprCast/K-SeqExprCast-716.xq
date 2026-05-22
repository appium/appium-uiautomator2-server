(:*******************************************************:)
(: Test: K-SeqExprCast-716                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:yearMonthDuration to xs:dayTimeDuration is allowed and should always succeed. :)
(:*******************************************************:)
xs:yearMonthDuration("P1Y12M") cast as xs:dayTimeDuration
                    ne
                  xs:dayTimeDuration("P3DT2H")