(:*******************************************************:)
(: Test: K-SeqExprCast-72                                :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A test whose essence is: `"example.com/" cast as xs:anyURI eq xs:anyURI('example.com/')`. :)
(:*******************************************************:)
"example.com/" cast as xs:anyURI eq 
				xs:anyURI('example.com/')