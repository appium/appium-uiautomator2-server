(:*******************************************************:)
(: Test: K-SeqIndexOfFunc-25                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `count(index-of((1, 2, 3, 2, 1), 4)) eq 0`. :)
(:*******************************************************:)
count(index-of((1, 2, 3, 2, 1), 4)) eq 0