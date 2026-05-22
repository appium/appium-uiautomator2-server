(:*******************************************************:)
(: Test: K-SeqExprCast-1370                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: The xs:anyURI constructor function must be passed exactly one argument, not two. :)
(:*******************************************************:)
xs:anyURI(
      "http://www.example.com/an/arbitrary/URI.ext"
    ,
                                                     
      "http://www.example.com/an/arbitrary/URI.ext"
    )