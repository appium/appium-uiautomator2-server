(:*******************************************************:)
(: Test: K-SeqExprCast-860                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:time to xs:float isn't allowed. :)
(:*******************************************************:)
xs:time("03:20:00-05:00") cast as xs:float