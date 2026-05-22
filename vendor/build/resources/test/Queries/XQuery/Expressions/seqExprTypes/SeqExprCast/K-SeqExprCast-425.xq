(:*******************************************************:)
(: Test: K-SeqExprCast-425                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: 'castable as' involving xs:untypedAtomic as source type and xs:string as target type should always evaluate to true. :)
(:*******************************************************:)
xs:untypedAtomic("an arbitrary string(untypedAtomic source)") castable as xs:string