(:*******************************************************:)
(: Test: K-SeqExprCast-963                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: 'castable as' involving xs:gYearMonth as source type and xs:string as target type should always evaluate to true. :)
(:*******************************************************:)
xs:gYearMonth("1999-11") castable as xs:string