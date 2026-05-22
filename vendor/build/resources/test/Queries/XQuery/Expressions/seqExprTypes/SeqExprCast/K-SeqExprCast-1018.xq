(:*******************************************************:)
(: Test: K-SeqExprCast-1018                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Casting from xs:gYear to xs:double isn't allowed. :)
(:*******************************************************:)
xs:gYear("1999") cast as xs:double