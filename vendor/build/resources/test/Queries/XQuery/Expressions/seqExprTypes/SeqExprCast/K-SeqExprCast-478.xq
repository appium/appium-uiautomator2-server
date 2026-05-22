(:*******************************************************:)
(: Test: K-SeqExprCast-478                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:float to xs:boolean is allowed and should always succeed. :)
(:*******************************************************:)
xs:float("3.4e5") cast as xs:boolean
                    eq
                  xs:boolean("true")