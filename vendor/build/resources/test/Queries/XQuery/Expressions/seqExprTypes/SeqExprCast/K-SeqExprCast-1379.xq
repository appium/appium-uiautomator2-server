(:*******************************************************:)
(: Test: K-SeqExprCast-1379                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: 'castable as' involving xs:anyURI as source type and xs:string as target type should always evaluate to true. :)
(:*******************************************************:)
xs:anyURI("http://www.example.com/an/arbitrary/URI.ext") castable as xs:string