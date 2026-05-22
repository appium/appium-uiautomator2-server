(:*******************************************************:)
(: Test: K-SeqExprCast-714                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:yearMonthDuration to xs:yearMonthDuration is allowed and should always succeed. :)
(:*******************************************************:)
xs:yearMonthDuration("P1Y12M") cast as xs:yearMonthDuration
                    eq
                  xs:yearMonthDuration("P1Y12M")