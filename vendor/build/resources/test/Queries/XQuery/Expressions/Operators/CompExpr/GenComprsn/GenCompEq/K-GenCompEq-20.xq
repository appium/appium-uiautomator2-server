(:*******************************************************:)
(: Test: K-GenCompEq-20                                  :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: An expression involving the '=' operator that trigger certain optimization paths in some implementations. :)
(:*******************************************************:)
count(remove(remove((current-time(), 1), 1), 1)) = 0