(:*******************************************************:)
(: Test: K-SeqExprCast-1038                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:gYear to xs:gYear is allowed and should always succeed. :)
(:*******************************************************:)
xs:gYear("1999") cast as xs:gYear
                    eq
                  xs:gYear("1999")