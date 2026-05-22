(:*******************************************************:)
(: Test: K-SeqExprCast-1308                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Casting from xs:base64Binary to xs:base64Binary is allowed and should always succeed. :)
(:*******************************************************:)
xs:base64Binary("aaaa") cast as xs:base64Binary
                    eq
                  xs:base64Binary("aaaa")