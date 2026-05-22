(:*******************************************************:)
(: Test: K-SeqExprCast-1374                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Cast a simple xs:untypedAtomic value to "http://www.example.com/an/arbitrary/URI.ext" . :)
(:*******************************************************:)
xs:anyURI(xs:untypedAtomic(
      "http://www.example.com/an/arbitrary/URI.ext"
    )) eq xs:anyURI("http://www.example.com/an/arbitrary/URI.ext")