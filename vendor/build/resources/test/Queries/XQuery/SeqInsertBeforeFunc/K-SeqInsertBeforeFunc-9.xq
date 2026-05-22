(:*******************************************************:)
(: Test: K-SeqInsertBeforeFunc-9                         :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `count(insert-before((), 1, (1, 2, 3))) eq 3`. :)
(:*******************************************************:)
count(insert-before((), 1, (1, 2, 3))) eq 3