(:*******************************************************:)
(: Test: K-SeqExprCast-1228                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Casting from xs:boolean to xs:decimal is allowed and should always succeed. :)
(:*******************************************************:)
xs:boolean("true") cast as xs:decimal
                    ne
                  xs:decimal("10.01")