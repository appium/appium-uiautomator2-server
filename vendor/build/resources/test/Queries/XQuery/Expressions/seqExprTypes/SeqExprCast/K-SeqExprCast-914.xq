(:*******************************************************:)
(: Test: K-SeqExprCast-914                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:date to xs:double isn't allowed. :)
(:*******************************************************:)
xs:date("2004-10-13") cast as xs:double