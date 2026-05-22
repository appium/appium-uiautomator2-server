(:*******************************************************:)
(: Test: K-SeqExprCast-106                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A test whose essence is: `xs:string(xs:hexBinary("c0")) eq "C0"`. :)
(:*******************************************************:)
xs:string(xs:hexBinary("c0")) eq "C0"