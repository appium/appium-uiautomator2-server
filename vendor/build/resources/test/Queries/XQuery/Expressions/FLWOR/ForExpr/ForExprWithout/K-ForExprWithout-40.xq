(:*******************************************************:)
(: Test: K-ForExprWithout-40                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Variable which is not in scope.              :)
(:*******************************************************:)
for $foo in 1 return 
						$bar + (for $bar in 2 return $bar)