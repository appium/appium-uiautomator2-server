(:*******************************************************:)
(: Test: K-SeqExprCast-1014                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:gYear to xs:string is allowed and should always succeed. :)
(:*******************************************************:)
xs:gYear("1999") cast as xs:string
                    ne
                  xs:string("an arbitrary string")