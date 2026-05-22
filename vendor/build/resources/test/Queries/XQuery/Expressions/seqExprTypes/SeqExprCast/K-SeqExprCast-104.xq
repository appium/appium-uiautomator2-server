(:*******************************************************:)
(: Test: K-SeqExprCast-104                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A test whose essence is: `xs:string(xs:hexBinary("a0")) eq "A0"`. :)
(:*******************************************************:)
xs:string(xs:hexBinary("a0")) eq "A0"