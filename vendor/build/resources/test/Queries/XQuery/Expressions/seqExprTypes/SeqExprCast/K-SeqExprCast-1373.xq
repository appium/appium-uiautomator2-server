(:*******************************************************:)
(: Test: K-SeqExprCast-1373                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: An empty string is a valid lexical representation of xs:anyURI. :)
(:*******************************************************:)
xs:anyURI("")
            eq
            xs:anyURI("")
          