(:*******************************************************:)
(: Test: K-SeqInsertBeforeFunc-16                        :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `count(insert-before((error(), 1), 1, (1, "two", 3))) > 1`. :)
(:*******************************************************:)
count(insert-before((error(), 1), 1, (1, "two", 3))) > 1