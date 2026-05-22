(:*******************************************************:)
(: Test: K-SeqSubsequenceFunc-34                         :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: Using subsequence inside a predicate.        :)
(:*******************************************************:)
(1)[deep-equal(1, subsequence((1, 2, current-time()), 1, 1))] eq 1