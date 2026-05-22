(:*******************************************************:)
(: Test: K-SeqExprCast-74                                :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A test whose essence is: `xs:anyURI("example.com/") cast as xs:anyURI eq xs:anyURI('example.com/')`. :)
(:*******************************************************:)
xs:anyURI("example.com/") cast as xs:anyURI eq 
				xs:anyURI('example.com/')