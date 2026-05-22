(:*******************************************************:)
(: Test: K-SeqExprCast-438                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:string to xs:string is allowed and should always succeed. :)
(:*******************************************************:)
xs:string("an arbitrary string") cast as xs:string
                    eq
                  xs:string("an arbitrary string")