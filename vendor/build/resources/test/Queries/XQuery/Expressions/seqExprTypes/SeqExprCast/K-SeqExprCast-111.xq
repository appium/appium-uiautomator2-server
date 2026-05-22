(:*******************************************************:)
(: Test: K-SeqExprCast-111                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A test whose essence is: `xs:string(xs:hexBinary("0c")) eq "0C"`. :)
(:*******************************************************:)
xs:string(xs:hexBinary("0c")) eq "0C"