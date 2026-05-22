(:*******************************************************:)
(: Test: K-SeqExprCast-1210                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Casting from xs:gMonth to xs:QName isn't allowed. :)
(:*******************************************************:)
xs:gMonth("--11") cast as xs:QName