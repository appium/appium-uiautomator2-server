(:*******************************************************:)
(: Test: K-SeqExprCast-1273                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: 'castable as' involving xs:base64Binary as source type and xs:untypedAtomic as target type should always evaluate to true. :)
(:*******************************************************:)
xs:base64Binary("aaaa") castable as xs:untypedAtomic