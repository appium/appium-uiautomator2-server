(:*******************************************************:)
(: Test: K-SeqExprCast-547                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: 'castable as' involving xs:decimal as source type and xs:string as target type should always evaluate to true. :)
(:*******************************************************:)
xs:decimal("10.01") castable as xs:string