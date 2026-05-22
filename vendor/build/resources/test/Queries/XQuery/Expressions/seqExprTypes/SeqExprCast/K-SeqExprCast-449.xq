(:*******************************************************:)
(: Test: K-SeqExprCast-449                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: 'castable as' involving xs:float as source type and xs:untypedAtomic as target type should always evaluate to true. :)
(:*******************************************************:)
xs:float("3.4e5") castable as xs:untypedAtomic