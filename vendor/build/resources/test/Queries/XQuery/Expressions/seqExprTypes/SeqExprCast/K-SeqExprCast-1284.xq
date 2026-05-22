(:*******************************************************:)
(: Test: K-SeqExprCast-1284                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Casting from xs:base64Binary to xs:duration isn't allowed. :)
(:*******************************************************:)
xs:base64Binary("aaaa") cast as xs:duration