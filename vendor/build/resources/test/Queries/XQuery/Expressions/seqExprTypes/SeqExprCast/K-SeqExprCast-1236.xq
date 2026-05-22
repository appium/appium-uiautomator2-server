(:*******************************************************:)
(: Test: K-SeqExprCast-1236                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Casting from xs:boolean to xs:dayTimeDuration isn't allowed. :)
(:*******************************************************:)
xs:boolean("true") cast as xs:dayTimeDuration