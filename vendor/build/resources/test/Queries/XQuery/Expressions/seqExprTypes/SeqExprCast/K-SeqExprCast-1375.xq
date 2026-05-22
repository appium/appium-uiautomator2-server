(:*******************************************************:)
(: Test: K-SeqExprCast-1375                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: Applying fn:boolean() to a value of type xs:anyURI that has the lexical value ' "http://www.example.com/an/arbitrary/URI.ext" ' should result in the boolean value true. :)
(:*******************************************************:)

          boolean(xs:anyURI("http://www.example.com/an/arbitrary/URI.ext"))