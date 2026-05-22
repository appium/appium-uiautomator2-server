(:*******************************************************:)
(: Test: K-SeqExprCast-1226                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Casting from xs:boolean to xs:double is allowed and should always succeed. :)
(:*******************************************************:)
xs:boolean("true") cast as xs:double
                    ne
                  xs:double("3.3e3")