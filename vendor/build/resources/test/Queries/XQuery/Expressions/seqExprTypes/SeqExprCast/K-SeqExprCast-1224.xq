(:*******************************************************:)
(: Test: K-SeqExprCast-1224                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Casting from xs:boolean to xs:float is allowed and should always succeed. :)
(:*******************************************************:)
xs:boolean("true") cast as xs:float
                    ne
                  xs:float("3.4e5")