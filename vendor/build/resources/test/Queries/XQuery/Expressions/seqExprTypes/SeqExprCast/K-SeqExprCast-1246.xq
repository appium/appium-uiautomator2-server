(:*******************************************************:)
(: Test: K-SeqExprCast-1246                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Casting from xs:boolean to xs:gYear isn't allowed. :)
(:*******************************************************:)
xs:boolean("true") cast as xs:gYear