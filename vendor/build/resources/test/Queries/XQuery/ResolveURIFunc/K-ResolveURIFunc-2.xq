(:*******************************************************:)
(: Test: K-ResolveURIFunc-2                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `resolve-uri("http://www.example.com/", "relative/uri.ext", "wrong param")`. :)
(:*******************************************************:)
resolve-uri("http://www.example.com/", 
				"relative/uri.ext", "wrong param")