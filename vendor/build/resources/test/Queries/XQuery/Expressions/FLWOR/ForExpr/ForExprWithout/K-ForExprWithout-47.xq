(:*******************************************************:)
(: Test: K-ForExprWithout-47                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A variable's for expression causes type error in a value comparison. :)
(:*******************************************************:)
for $foo in ("a string", "another one") return
							1 + subsequence($foo, 1, 1)