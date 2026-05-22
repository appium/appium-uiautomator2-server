(:*******************************************************:)
(: Test: K-compareFunc-15                                :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A test whose essence is: `empty(compare("a string", "a string", "http://www.example.com/COLLATION/NOT/SUPPORTED"))`. :)
(:*******************************************************:)
empty(compare("a string", "a string",
			"http://www.example.com/COLLATION/NOT/SUPPORTED"))