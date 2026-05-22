(:*******************************************************:)
(: Test: K-SeqExprCast-550                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:decimal to xs:double is allowed and should always succeed. :)
(:*******************************************************:)
xs:decimal("10.01") cast as xs:double
                    ne
                  xs:double("3.3e3")