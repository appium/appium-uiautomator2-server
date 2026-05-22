(:*******************************************************:)
(: Test: K-QuantExprWithout-48                           :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A test whose essence is: `every $var in (1, 2, 3) satisfies $var eq 1 or $var eq 2 or $var eq 3`. :)
(:*******************************************************:)
every $var in (1, 2, 3) satisfies $var eq 1 or $var eq 2 or $var eq 3