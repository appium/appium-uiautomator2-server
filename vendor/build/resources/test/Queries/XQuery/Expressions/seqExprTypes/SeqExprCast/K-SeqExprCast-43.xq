(:*******************************************************:)
(: Test: K-SeqExprCast-43                                :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A test whose essence is: `(xs:QName("local-name") cast as xs:string) eq "local-name"`. :)
(:*******************************************************:)
(xs:QName("local-name") cast as xs:string) eq "local-name"