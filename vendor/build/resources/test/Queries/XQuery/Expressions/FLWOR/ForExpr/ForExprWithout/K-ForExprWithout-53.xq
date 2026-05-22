(:*******************************************************:)
(: Test: K-ForExprWithout-53                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Ensure the correct variable is used in an for-expression whose return sequence is only a variable reference. :)
(:*******************************************************:)

		   declare variable $my := 3;

		   (for $i in 1 return $my) eq 3