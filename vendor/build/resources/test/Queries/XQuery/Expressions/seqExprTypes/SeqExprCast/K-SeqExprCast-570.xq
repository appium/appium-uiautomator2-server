(:*******************************************************:)
(: Test: K-SeqExprCast-570                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:decimal to xs:gYear isn't allowed. :)
(:*******************************************************:)
xs:decimal("10.01") cast as xs:gYear