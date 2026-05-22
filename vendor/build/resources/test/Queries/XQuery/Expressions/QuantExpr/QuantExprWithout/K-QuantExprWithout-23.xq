(:*******************************************************:)
(: Test: K-QuantExprWithout-23                           :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A test whose essence is: `some $var in (true(), true(), true()) satisfies $var`. :)
(:*******************************************************:)
some $var in (true(), true(), true()) satisfies $var