(:*******************************************************:)
(: Test: K-SeqSubsequenceFunc-41                         :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: Apply a predicate to the result of fn:subsequence(). :)
(:*******************************************************:)
subsequence((1, 2, 3, current-time(), 5, 6, 9), 7)[last()]