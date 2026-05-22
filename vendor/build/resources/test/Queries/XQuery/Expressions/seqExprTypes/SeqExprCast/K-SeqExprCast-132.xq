(:*******************************************************:)
(: Test: K-SeqExprCast-132                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A test whose essence is: `xs:string(xs:base64Binary(xs:hexBinary("12"))) eq "Eg=="`. :)
(:*******************************************************:)
xs:string(xs:base64Binary(xs:hexBinary("12"))) eq "Eg=="