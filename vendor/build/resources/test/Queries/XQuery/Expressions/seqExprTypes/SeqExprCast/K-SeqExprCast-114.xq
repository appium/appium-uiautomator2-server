(:*******************************************************:)
(: Test: K-SeqExprCast-114                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: An empty string is a valid lexical representation for xs:hexBinary, and means 'no data. :)
(:*******************************************************:)
xs:string(xs:hexBinary("")) eq ""