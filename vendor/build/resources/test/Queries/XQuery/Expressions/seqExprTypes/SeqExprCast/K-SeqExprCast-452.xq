(:*******************************************************:)
(: Test: K-SeqExprCast-452                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:float to xs:float is allowed and should always succeed. :)
(:*******************************************************:)
xs:float("3.4e5") cast as xs:float
                    eq
                  xs:float("3.4e5")