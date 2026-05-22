(:*******************************************************:)
(: Test: K-ForExprWithout-10                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A test whose essence is: `deep-equal((for $var in (1, 2, 3) return $var), (1, 2, 3))`. :)
(:*******************************************************:)
deep-equal((for $var in (1, 2, 3) return $var),
					  (1, 2, 3))