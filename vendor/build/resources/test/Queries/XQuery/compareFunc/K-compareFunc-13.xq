(:*******************************************************:)
(: Test: K-compareFunc-13                                :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A test whose essence is: `empty(compare("a string", (), "http://www.w3.org/2005/xpath-functions/collation/codepoint"))`. :)
(:*******************************************************:)
empty(compare("a string", (),
			"http://www.w3.org/2005/xpath-functions/collation/codepoint"))