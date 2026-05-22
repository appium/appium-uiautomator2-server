(:*******************************************************:)
(: Test: K-SeqExprCast-1274                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Casting from xs:base64Binary to xs:string is allowed and should always succeed. :)
(:*******************************************************:)
xs:base64Binary("aaaa") cast as xs:string
                    ne
                  xs:string("an arbitrary string")