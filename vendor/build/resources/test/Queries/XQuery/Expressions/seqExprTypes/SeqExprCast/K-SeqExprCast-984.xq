(:*******************************************************:)
(: Test: K-SeqExprCast-984                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:gYearMonth to xs:gYearMonth is allowed and should always succeed. :)
(:*******************************************************:)
xs:gYearMonth("1999-11") cast as xs:gYearMonth
                    eq
                  xs:gYearMonth("1999-11")