(:*******************************************************:)
(: Test: K-SeqExprCast-554                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:decimal to xs:integer is allowed and should always succeed. :)
(:*******************************************************:)
xs:decimal("10.01") cast as xs:integer
                    ne
                  xs:integer("6789")