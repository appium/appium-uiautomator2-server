(:*******************************************************:)
(: Test: K-SeqExprCast-606                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:integer to xs:integer is allowed and should always succeed. :)
(:*******************************************************:)
xs:integer("6789") cast as xs:integer
                    eq
                  xs:integer("6789")