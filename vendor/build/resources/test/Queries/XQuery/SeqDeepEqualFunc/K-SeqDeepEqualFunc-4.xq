(:*******************************************************:)
(: Test: K-SeqDeepEqualFunc-4                            :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `deep-equal("a string", "a string", "http://www.example.com/COLLATION/NOT/SUPPORTED")`. :)
(:*******************************************************:)
deep-equal("a string", "a string",
			"http://www.example.com/COLLATION/NOT/SUPPORTED")