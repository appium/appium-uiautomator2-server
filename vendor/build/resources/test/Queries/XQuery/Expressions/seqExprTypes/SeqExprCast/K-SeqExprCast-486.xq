(:*******************************************************:)
(: Test: K-SeqExprCast-486                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:float to xs:QName isn't allowed. :)
(:*******************************************************:)
xs:float("3.4e5") cast as xs:QName