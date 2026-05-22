(:*******************************************************:)
(: Test: K-ForExprWithout-24                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A test whose essence is: `(for $fn:name in (1, 1) return $fn:name) instance of xs:integer+`. :)
(:*******************************************************:)
(for $fn:name in (1, 1) return $fn:name) instance of xs:integer+