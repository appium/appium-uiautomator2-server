(:*******************************************************:)
(: Test: K-EndsWithFunc-4                                :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `ends-with("a string", "a string", "http://www.example.com/COLLATION/NOT/SUPPORTED")`. :)
(:*******************************************************:)
ends-with("a string", "a string",
			"http://www.example.com/COLLATION/NOT/SUPPORTED")