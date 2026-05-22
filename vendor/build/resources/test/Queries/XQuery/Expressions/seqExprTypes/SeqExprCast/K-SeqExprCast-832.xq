(:*******************************************************:)
(: Test: K-SeqExprCast-832                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:dateTime to xs:gMonthDay is allowed and should always succeed. :)
(:*******************************************************:)
xs:dateTime("2002-10-10T12:00:00-05:00") cast as xs:gMonthDay
                    ne
                  xs:gMonthDay("--11-13")