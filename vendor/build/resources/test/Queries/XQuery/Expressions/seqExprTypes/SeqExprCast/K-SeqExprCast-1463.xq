(:*******************************************************:)
(: Test: K-SeqExprCast-1463                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Casting from xs:QName to xs:base64Binary isn't allowed. :)
(:*******************************************************:)
xs:QName("ncname") cast as xs:base64Binary