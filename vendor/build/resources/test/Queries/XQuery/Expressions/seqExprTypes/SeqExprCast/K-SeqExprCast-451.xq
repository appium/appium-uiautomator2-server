(:*******************************************************:)
(: Test: K-SeqExprCast-451                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: 'castable as' involving xs:float as source type and xs:string as target type should always evaluate to true. :)
(:*******************************************************:)
xs:float("3.4e5") castable as xs:string