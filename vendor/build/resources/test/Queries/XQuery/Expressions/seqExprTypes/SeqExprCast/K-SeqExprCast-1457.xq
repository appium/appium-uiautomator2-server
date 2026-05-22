(:*******************************************************:)
(: Test: K-SeqExprCast-1457                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Casting from xs:QName to xs:gDay isn't allowed. :)
(:*******************************************************:)
xs:QName("ncname") cast as xs:gDay