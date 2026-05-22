(:*******************************************************:)
(: Test: K-SeqExprCast-1092                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Casting from xs:gMonthDay to xs:gMonthDay is allowed and should always succeed. :)
(:*******************************************************:)
xs:gMonthDay("--11-13") cast as xs:gMonthDay
                    eq
                  xs:gMonthDay("--11-13")