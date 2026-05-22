(:*******************************************************:)
(: Test: K-SeqExprCast-133                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A test whose essence is: `xs:string(xs:base64Binary(xs:hexBinary("3B"))) eq "Ow=="`. :)
(:*******************************************************:)
xs:string(xs:base64Binary(xs:hexBinary("3B"))) eq "Ow=="