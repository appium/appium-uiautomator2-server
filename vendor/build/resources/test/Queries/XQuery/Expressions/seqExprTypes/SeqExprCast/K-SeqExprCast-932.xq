(:*******************************************************:)
(: Test: K-SeqExprCast-932                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:date to xs:gYearMonth is allowed and should always succeed. :)
(:*******************************************************:)
xs:date("2004-10-13") cast as xs:gYearMonth
                    ne
                  xs:gYearMonth("1999-11")