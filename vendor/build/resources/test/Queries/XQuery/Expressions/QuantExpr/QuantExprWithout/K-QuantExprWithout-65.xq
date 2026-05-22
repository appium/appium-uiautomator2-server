(:*******************************************************:)
(: Test: K-QuantExprWithout-65                           :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Variable which is not in scope.              :)
(:*******************************************************:)
every $foo in 1 satisfies 
						$bar + (some $bar in 2 satisfies $bar)