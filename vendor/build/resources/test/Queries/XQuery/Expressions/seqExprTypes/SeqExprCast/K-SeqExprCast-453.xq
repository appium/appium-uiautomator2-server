(:*******************************************************:)
(: Test: K-SeqExprCast-453                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: 'castable as' involving xs:float as source type and xs:float as target type should always evaluate to true. :)
(:*******************************************************:)
xs:float("3.4e5") castable as xs:float