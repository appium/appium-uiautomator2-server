(:*******************************************************:)
(: Test: K-SeqExprCast-42                                :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A test whose essence is: `(xs:QName("fn:local-name") cast as xs:string) eq "fn:local-name"`. :)
(:*******************************************************:)
(xs:QName("fn:local-name") cast as xs:string) eq "fn:local-name"