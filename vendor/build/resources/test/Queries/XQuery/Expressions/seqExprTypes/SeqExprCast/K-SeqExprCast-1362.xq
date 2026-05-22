(:*******************************************************:)
(: Test: K-SeqExprCast-1362                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Casting from xs:hexBinary to xs:hexBinary is allowed and should always succeed. :)
(:*******************************************************:)
xs:hexBinary("0FB7") cast as xs:hexBinary
                    eq
                  xs:hexBinary("0FB7")