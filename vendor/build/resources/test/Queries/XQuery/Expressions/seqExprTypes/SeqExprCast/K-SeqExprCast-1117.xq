(:*******************************************************:)
(: Test: K-SeqExprCast-1117                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: 'castable as' involving xs:gDay as source type and xs:untypedAtomic as target type should always evaluate to true. :)
(:*******************************************************:)
xs:gDay("---03") castable as xs:untypedAtomic