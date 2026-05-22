(:*******************************************************:)
(: Test: K-ForExprWithout-25                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A test whose essence is: `(for $xs:name in (1, 1) return $xs:name) instance of xs:integer+`. :)
(:*******************************************************:)
(for $xs:name in (1, 1) return $xs:name) instance of xs:integer+