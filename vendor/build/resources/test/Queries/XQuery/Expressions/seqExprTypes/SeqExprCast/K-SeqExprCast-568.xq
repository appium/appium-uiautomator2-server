(:*******************************************************:)
(: Test: K-SeqExprCast-568                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:decimal to xs:gYearMonth isn't allowed. :)
(:*******************************************************:)
xs:decimal("10.01") cast as xs:gYearMonth