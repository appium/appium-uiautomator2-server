(:*******************************************************:)
(: Test: K-ForExprPositionalVar-8                        :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A positional variable causing a type error.  :)
(:*******************************************************:)
for $i at $p in (1, 2, 3) return $p + "1"