(:*******************************************************:)
(: Test: K-SeqRemoveFunc-11                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `count(remove((1, 2, "three"), 3)) eq 2`. :)
(:*******************************************************:)
count(remove((1, 2, "three"), 3)) eq 2