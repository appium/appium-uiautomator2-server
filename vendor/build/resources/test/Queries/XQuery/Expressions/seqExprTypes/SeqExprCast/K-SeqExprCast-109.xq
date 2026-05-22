(:*******************************************************:)
(: Test: K-SeqExprCast-109                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A test whose essence is: `xs:string(xs:hexBinary("031a34123b")) eq "031A34123B"`. :)
(:*******************************************************:)
xs:string(xs:hexBinary("031a34123b")) eq "031A34123B"