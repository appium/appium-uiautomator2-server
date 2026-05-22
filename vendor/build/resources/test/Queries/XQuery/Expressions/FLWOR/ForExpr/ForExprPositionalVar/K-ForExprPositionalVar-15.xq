(:*******************************************************:)
(: Test: K-ForExprPositionalVar-15                       :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Verify that the position is properly computed for the range expression. :)
(:*******************************************************:)
deep-equal((1, 2, 3, 4),
	    for $i at $p
	    in 1 to 4
	    return $p)