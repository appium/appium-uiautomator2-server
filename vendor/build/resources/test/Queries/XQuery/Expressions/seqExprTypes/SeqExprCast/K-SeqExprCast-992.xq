(:*******************************************************:)
(: Test: K-SeqExprCast-992                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:gYearMonth to xs:gMonth isn't allowed. :)
(:*******************************************************:)
xs:gYearMonth("1999-11") cast as xs:gMonth