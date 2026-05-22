(:*******************************************************:)
(: Test: K-SeqExprCast-1304                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Casting from xs:base64Binary to xs:gMonth isn't allowed. :)
(:*******************************************************:)
xs:base64Binary("aaaa") cast as xs:gMonth