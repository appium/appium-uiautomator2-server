(:*******************************************************:)
(: Test: K-SeqExprCast-703                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: 'castable as' involving xs:yearMonthDuration as source type and xs:string as target type should always evaluate to true. :)
(:*******************************************************:)
xs:yearMonthDuration("P1Y12M") castable as xs:string