(:*******************************************************:)
(: Test: K-SeqExprCast-1377                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: 'castable as' involving xs:anyURI as source type and xs:untypedAtomic as target type should always evaluate to true. :)
(:*******************************************************:)
xs:anyURI("http://www.example.com/an/arbitrary/URI.ext") castable as xs:untypedAtomic