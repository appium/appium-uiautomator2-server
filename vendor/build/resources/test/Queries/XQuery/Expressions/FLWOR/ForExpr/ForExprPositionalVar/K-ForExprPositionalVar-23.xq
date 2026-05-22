(:*******************************************************:)
(: Test: K-ForExprPositionalVar-23                       :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Verify that the position is properly computed for fn:subsequence(). :)
(:*******************************************************:)
1 eq 
	    (for $i at $p
	    in subsequence((1, 2, 3, current-time()), 1, 1)
	    return $p)