(:*******************************************************:)
(: Test: K-SeqExprCast-1416                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Casting from xs:anyURI to xs:anyURI is allowed and should always succeed. :)
(:*******************************************************:)
xs:anyURI("http://www.example.com/an/arbitrary/URI.ext") cast as xs:anyURI
                    eq
                  xs:anyURI("http://www.example.com/an/arbitrary/URI.ext")