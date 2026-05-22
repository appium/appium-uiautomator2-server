(:*******************************************************:)
(: Test: K-SeqExprCast-144                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: A test whose essence is: `(xs:decimal(remove((3e3, 1.1), 1))) eq 1.1`. :)
(:*******************************************************:)
(xs:decimal(remove((3e3, 1.1), 1))) eq 1.1