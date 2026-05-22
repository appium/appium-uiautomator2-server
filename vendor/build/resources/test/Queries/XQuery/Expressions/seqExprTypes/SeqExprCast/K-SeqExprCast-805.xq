(:*******************************************************:)
(: Test: K-SeqExprCast-805                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: 'castable as' involving xs:dateTime as source type and xs:untypedAtomic as target type should always evaluate to true. :)
(:*******************************************************:)
xs:dateTime("2002-10-10T12:00:00-05:00") castable as xs:untypedAtomic