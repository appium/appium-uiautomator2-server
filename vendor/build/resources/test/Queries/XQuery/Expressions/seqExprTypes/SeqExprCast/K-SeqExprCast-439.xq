(:*******************************************************:)
(: Test: K-SeqExprCast-439                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: 'castable as' involving xs:string as source type and xs:string as target type should always evaluate to true. :)
(:*******************************************************:)
xs:string("an arbitrary string") castable as xs:string