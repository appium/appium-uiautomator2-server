(:*******************************************************:)
(: Test: K-SeqExprCast-83                                :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A test whose essence is: `xs:string(xs:float("INF")) eq "INF"`. :)
(:*******************************************************:)
xs:string(xs:float("INF")) eq "INF"