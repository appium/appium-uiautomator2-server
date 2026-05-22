(:*******************************************************:)
(: Test: K-SeqExprCast-1142                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Casting from xs:gDay to xs:gYear isn't allowed. :)
(:*******************************************************:)
xs:gDay("---03") cast as xs:gYear