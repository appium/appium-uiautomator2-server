(:*******************************************************:)
(: Test: K-SeqExprCast-142                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: An empty string is a valid lexical representation for xs:base64Binary, and means 'no data. :)
(:*******************************************************:)
xs:string(xs:base64Binary("")) eq ""