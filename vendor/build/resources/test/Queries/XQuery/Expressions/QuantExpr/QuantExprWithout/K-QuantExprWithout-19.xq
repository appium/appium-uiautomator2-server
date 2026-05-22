(:*******************************************************:)
(: Test: K-QuantExprWithout-19                           :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A test whose essence is: `every $var in (true(), true(), true()) satisfies $var`. :)
(:*******************************************************:)
every $var in (true(), true(), true()) satisfies $var