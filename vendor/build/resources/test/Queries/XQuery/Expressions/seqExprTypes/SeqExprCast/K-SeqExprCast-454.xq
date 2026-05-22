(:*******************************************************:)
(: Test: K-SeqExprCast-454                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:float to xs:double is allowed and should always succeed. :)
(:*******************************************************:)
xs:float("3.4e5") cast as xs:double
                    ne
                  xs:double("3.3e3")