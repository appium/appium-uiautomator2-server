(:*******************************************************:)
(: Test: K-SeqExprCast-546                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:decimal to xs:string is allowed and should always succeed. :)
(:*******************************************************:)
xs:decimal("10.01") cast as xs:string
                    ne
                  xs:string("an arbitrary string")