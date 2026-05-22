(:*******************************************************:)
(: Test: K-SeqExprCast-527                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: 'castable as' involving xs:double as source type and xs:boolean as target type should always evaluate to true. :)
(:*******************************************************:)
xs:double("3.3e3") castable as xs:boolean