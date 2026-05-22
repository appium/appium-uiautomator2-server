(:*******************************************************:)
(: Test: K-SeqExprCast-1465                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Casting from xs:QName to xs:hexBinary isn't allowed. :)
(:*******************************************************:)
xs:QName("ncname") cast as xs:hexBinary