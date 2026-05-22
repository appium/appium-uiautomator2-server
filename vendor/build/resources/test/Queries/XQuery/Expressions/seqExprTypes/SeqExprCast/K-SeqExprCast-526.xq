(:*******************************************************:)
(: Test: K-SeqExprCast-526                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:double to xs:boolean is allowed and should always succeed. :)
(:*******************************************************:)
xs:double("3.3e3") cast as xs:boolean
                    eq
                  xs:boolean("true")