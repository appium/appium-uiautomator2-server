(:*******************************************************:)
(: Test: K-SeqSubsequenceFunc-31                         :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `count(subsequence((1, 2, 2, current-time()), 2, 2)) eq 2`. :)
(:*******************************************************:)
count(subsequence((1, 2, 2, current-time()), 2, 2)) eq 2