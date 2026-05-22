(:*******************************************************:)
(: Test: K-SeqExprCast-128                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A test whose essence is: `xs:string(xs:base64Binary("frfhforlksid7453")) eq "frfhforlksid7453"`. :)
(:*******************************************************:)
xs:string(xs:base64Binary("frfhforlksid7453")) eq "frfhforlksid7453"