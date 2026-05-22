(:*******************************************************:)
(: Test: K-SeqExprCast-1429                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Casting from xs:QName to xs:string is allowed and should always succeed. :)
(:*******************************************************:)
xs:QName("ncname") cast as xs:string
                    ne
                  xs:string("an arbitrary string")