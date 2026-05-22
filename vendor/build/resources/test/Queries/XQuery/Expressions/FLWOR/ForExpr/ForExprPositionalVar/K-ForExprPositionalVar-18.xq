(:*******************************************************:)
(: Test: K-ForExprPositionalVar-18                       :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Verify that the position is properly computed for fn:remove(). :)
(:*******************************************************:)
deep-equal((1, 2, 3),
	    for $i at $p
	    in remove((1, 2, 3, current-time()), 4)
	    return $p)