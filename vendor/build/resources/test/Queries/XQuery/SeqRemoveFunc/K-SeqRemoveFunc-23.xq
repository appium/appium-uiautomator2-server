(:*******************************************************:)
(: Test: K-SeqRemoveFunc-23                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: Apply a predicate to the result of fn:remove(). :)
(:*******************************************************:)
remove((1, 2, 3, current-time()), 9)[last() - 1]