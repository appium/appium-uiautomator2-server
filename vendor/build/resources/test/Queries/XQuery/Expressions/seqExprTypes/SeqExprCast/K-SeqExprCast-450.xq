(:*******************************************************:)
(: Test: K-SeqExprCast-450                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:float to xs:string is allowed and should always succeed. :)
(:*******************************************************:)
xs:float("3.4e5") cast as xs:string
                    ne
                  xs:string("an arbitrary string")