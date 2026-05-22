(:*******************************************************:)
(: Test: K-SeqExprCast-1068                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:gMonthDay to xs:float isn't allowed. :)
(:*******************************************************:)
xs:gMonthDay("--11-13") cast as xs:float