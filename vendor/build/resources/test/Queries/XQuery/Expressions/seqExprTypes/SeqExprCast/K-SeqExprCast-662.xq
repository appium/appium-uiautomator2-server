(:*******************************************************:)
(: Test: K-SeqExprCast-662                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:duration to xs:yearMonthDuration is allowed and should always succeed. :)
(:*******************************************************:)
xs:duration("P1Y2M3DT10H30M") cast as xs:yearMonthDuration
                    ne
                  xs:yearMonthDuration("P1Y12M")