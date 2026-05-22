(:*******************************************************:)
(: Test: K-SeqExprCast-1360                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Casting from xs:hexBinary to xs:base64Binary is allowed and should always succeed. :)
(:*******************************************************:)
xs:hexBinary("0FB7") cast as xs:base64Binary
                    ne
                  xs:base64Binary("aaaa")