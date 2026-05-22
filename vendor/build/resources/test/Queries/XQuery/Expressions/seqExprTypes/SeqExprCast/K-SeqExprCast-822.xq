(:*******************************************************:)
(: Test: K-SeqExprCast-822                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:dateTime to xs:dateTime is allowed and should always succeed. :)
(:*******************************************************:)
xs:dateTime("2002-10-10T12:00:00-05:00") cast as xs:dateTime
                    eq
                  xs:dateTime("2002-10-10T12:00:00-05:00")