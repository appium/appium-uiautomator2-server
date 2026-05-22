(:*******************************************************:)
(: Test: K-SeqExprCast-600                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:integer to xs:float is allowed and should always succeed. :)
(:*******************************************************:)
xs:integer("6789") cast as xs:float
                    ne
                  xs:float("3.4e5")