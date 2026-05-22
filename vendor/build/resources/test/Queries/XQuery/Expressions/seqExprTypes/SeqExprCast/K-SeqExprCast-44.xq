(:*******************************************************:)
(: Test: K-SeqExprCast-44                                :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A test whose essence is: `xs:string(xs:anyURI('example.com/')) eq 'example.com/'`. :)
(:*******************************************************:)
xs:string(xs:anyURI('example.com/')) eq 'example.com/'