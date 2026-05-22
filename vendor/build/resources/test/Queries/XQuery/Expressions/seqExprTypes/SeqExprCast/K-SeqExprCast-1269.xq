(:*******************************************************:)
(: Test: K-SeqExprCast-1269                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: An empty string is a valid lexical representation of xs:base64Binary. :)
(:*******************************************************:)
xs:base64Binary("")
            eq
            xs:base64Binary("")
          