(:*******************************************************:)
(: Test: K-SeqExprCast-73                                :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A test whose essence is: `xs:untypedAtomic("example.com/") cast as xs:anyURI eq xs:anyURI('example.com/')`. :)
(:*******************************************************:)
xs:untypedAtomic("example.com/") cast as xs:anyURI eq 
				xs:anyURI('example.com/')