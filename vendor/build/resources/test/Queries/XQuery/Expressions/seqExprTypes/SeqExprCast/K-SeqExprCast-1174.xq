(:*******************************************************:)
(: Test: K-SeqExprCast-1174                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Casting from xs:gMonth to xs:double isn't allowed. :)
(:*******************************************************:)
xs:gMonth("--11") cast as xs:double