(:*******************************************************:)
(: Test: K-ForExprWithout-37                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Variable which is not in scope.              :)
(:*******************************************************:)
for $foo in (1, $foo, 3) return 1