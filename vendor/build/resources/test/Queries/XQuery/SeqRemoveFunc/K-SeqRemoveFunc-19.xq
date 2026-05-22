(:*******************************************************:)
(: Test: K-SeqRemoveFunc-19                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: An expression involving the eq operator that trigger certain optimization paths in some implementations. :)
(:*******************************************************:)
count(remove(current-time(), 1)) eq 0