(:*******************************************************:)
(: Test: K-SeqExprCast-141                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A test whose essence is: `xs:string(xs:hexBinary(xs:base64Binary("aaa a"))) eq "69A69A"`. :)
(:*******************************************************:)
xs:string(xs:hexBinary(xs:base64Binary("aaa a"))) eq "69A69A"