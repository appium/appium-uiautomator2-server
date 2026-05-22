(:*******************************************************:)
(: Test: K-SeqExprCast-548                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:decimal to xs:float is allowed and should always succeed. :)
(:*******************************************************:)
xs:decimal("10.01") cast as xs:float
                    ne
                  xs:float("3.4e5")