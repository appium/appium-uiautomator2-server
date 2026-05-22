(:*******************************************************:)
(: Test: K-SeqExprCast-56                                :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A test whose essence is: `xs:untypedAtomic(xs:anyURI('example.com/')) eq 'example.com/'`. :)
(:*******************************************************:)
xs:untypedAtomic(xs:anyURI('example.com/')) eq 'example.com/'