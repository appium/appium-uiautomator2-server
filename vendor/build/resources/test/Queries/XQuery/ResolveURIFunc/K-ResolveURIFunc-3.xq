(:*******************************************************:)
(: Test: K-ResolveURIFunc-3                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `resolve-uri("relative/uri.ext", "http://www.example.com/") eq xs:anyURI("http://www.example.com/relative/uri.ext")`. :)
(:*******************************************************:)
resolve-uri("relative/uri.ext", "http://www.example.com/") eq
			xs:anyURI("http://www.example.com/relative/uri.ext")