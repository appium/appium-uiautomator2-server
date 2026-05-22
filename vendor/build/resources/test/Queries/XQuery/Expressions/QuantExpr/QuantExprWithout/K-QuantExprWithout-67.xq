(:*******************************************************:)
(: Test: K-QuantExprWithout-67                           :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Variable which is not in scope.              :)
(:*******************************************************:)
some $foo in 1 satisfies 
						$bar + (every $bar in 2 satisfies $bar)