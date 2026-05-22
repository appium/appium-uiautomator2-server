(:*******************************************************:)
(: Test: K-SeqInsertBeforeFunc-6                         :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `count(insert-before((1, 2, 3), 1, (4, 5, 6))) eq 6`. :)
(:*******************************************************:)
count(insert-before((1, 2, 3), 1, (4, 5, 6))) eq 6