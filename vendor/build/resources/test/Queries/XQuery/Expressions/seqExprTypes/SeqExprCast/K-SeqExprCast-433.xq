(:*******************************************************:)
(: Test: K-SeqExprCast-433                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: An empty string is a valid lexical representation of xs:string. :)
(:*******************************************************:)
xs:string("")
            eq
            xs:string("")
          