(:*******************************************************:)
(: Test: K-SeqExprCast-663                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: 'castable as' involving xs:duration as source type and xs:yearMonthDuration as target type should always evaluate to true. :)
(:*******************************************************:)
xs:duration("P1Y2M3DT10H30M") castable as xs:yearMonthDuration