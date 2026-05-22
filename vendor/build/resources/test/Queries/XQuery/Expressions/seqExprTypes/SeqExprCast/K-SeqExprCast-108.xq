(:*******************************************************:)
(: Test: K-SeqExprCast-108                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A test whose essence is: `xs:string(xs:hexBinary("10")) eq "10"`. :)
(:*******************************************************:)
xs:string(xs:hexBinary("10")) eq "10"