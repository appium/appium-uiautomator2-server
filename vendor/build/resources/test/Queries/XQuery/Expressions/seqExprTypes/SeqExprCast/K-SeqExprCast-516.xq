(:*******************************************************:)
(: Test: K-SeqExprCast-516                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:double to xs:gYearMonth isn't allowed. :)
(:*******************************************************:)
xs:double("3.3e3") cast as xs:gYearMonth