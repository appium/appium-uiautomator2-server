(:*******************************************************:)
(: Test: K-SeqExprCast-578                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:decimal to xs:boolean is allowed and should always succeed. :)
(:*******************************************************:)
xs:decimal("10.01") cast as xs:boolean
                    eq
                  xs:boolean("true")