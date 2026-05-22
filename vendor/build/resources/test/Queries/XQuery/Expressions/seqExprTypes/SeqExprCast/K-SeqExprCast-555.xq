(:*******************************************************:)
(: Test: K-SeqExprCast-555                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: 'castable as' involving xs:decimal as source type and xs:integer as target type should always evaluate to true. :)
(:*******************************************************:)
xs:decimal("10.01") castable as xs:integer