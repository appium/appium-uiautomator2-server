(:*******************************************************:)
(: Test: K-SeqExprCast-1230                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Casting from xs:boolean to xs:integer is allowed and should always succeed. :)
(:*******************************************************:)
xs:boolean("true") cast as xs:integer
                    ne
                  xs:integer("6789")