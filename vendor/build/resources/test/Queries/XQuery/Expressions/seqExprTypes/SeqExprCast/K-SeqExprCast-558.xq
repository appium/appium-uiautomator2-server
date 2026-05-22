(:*******************************************************:)
(: Test: K-SeqExprCast-558                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:decimal to xs:yearMonthDuration isn't allowed. :)
(:*******************************************************:)
xs:decimal("10.01") cast as xs:yearMonthDuration