(:*******************************************************:)
(: Test: K-SeqExprCast-127                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A test whose essence is: `xs:string(xs:base64Binary("aaa a")) eq "aaaa"`. :)
(:*******************************************************:)
xs:string(xs:base64Binary("aaa a")) eq "aaaa"