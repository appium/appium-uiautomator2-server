(:*******************************************************:)
(: Test: K-SeqExprCast-1146                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Casting from xs:gDay to xs:gDay is allowed and should always succeed. :)
(:*******************************************************:)
xs:gDay("---03") cast as xs:gDay
                    eq
                  xs:gDay("---03")