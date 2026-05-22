(:*******************************************************:)
(: Test: K-SeqExprCast-598                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:integer to xs:string is allowed and should always succeed. :)
(:*******************************************************:)
xs:integer("6789") cast as xs:string
                    ne
                  xs:string("an arbitrary string")