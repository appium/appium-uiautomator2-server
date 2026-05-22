(:*******************************************************:)
(: Test: K-SeqReverseFunc-18                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: Apply a predicate to the result of fn:reverse(). :)
(:*******************************************************:)
reverse((1, 2, 3))[last() - 1] eq 2