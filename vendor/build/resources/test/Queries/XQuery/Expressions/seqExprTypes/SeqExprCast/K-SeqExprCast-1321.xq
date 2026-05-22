(:*******************************************************:)
(: Test: K-SeqExprCast-1321                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: An empty string is a valid lexical representation of xs:hexBinary. :)
(:*******************************************************:)
xs:hexBinary("")
            eq
            xs:hexBinary("")
          