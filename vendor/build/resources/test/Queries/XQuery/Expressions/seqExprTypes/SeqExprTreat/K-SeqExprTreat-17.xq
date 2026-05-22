(:*******************************************************:)
(: Test: K-SeqExprTreat-17                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:39+02:00                       :)
(: Purpose: A test whose essence is: `(remove((5, 1e0), 2) treat as xs:integer) eq 5`. :)
(:*******************************************************:)
(remove((5, 1e0), 2) treat as xs:integer) eq 5