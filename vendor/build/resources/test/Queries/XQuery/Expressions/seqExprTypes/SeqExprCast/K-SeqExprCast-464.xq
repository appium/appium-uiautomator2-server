(:*******************************************************:)
(: Test: K-SeqExprCast-464                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:float to xs:time isn't allowed. :)
(:*******************************************************:)
xs:float("3.4e5") cast as xs:time