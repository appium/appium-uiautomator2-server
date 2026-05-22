(:*******************************************************:)
(: Test: K-SeqExprCast-1070                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:gMonthDay to xs:double isn't allowed. :)
(:*******************************************************:)
xs:gMonthDay("--11-13") cast as xs:double