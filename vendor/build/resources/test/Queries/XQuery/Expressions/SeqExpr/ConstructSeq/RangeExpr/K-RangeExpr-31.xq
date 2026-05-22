(:*******************************************************:)
(: Test: K-RangeExpr-31                                  :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: A test whose essence is: `(remove((2.e0, 4), 1) treat as xs:integer to 4) eq 4`. :)
(:*******************************************************:)
(remove((2.e0, 4), 1) treat as xs:integer to 4) eq 4