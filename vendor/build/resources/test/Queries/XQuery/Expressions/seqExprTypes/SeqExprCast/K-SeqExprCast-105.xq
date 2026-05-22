(:*******************************************************:)
(: Test: K-SeqExprCast-105                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A test whose essence is: `xs:string(xs:hexBinary("a4")) eq "A4"`. :)
(:*******************************************************:)
xs:string(xs:hexBinary("a4")) eq "A4"