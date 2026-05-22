(:*******************************************************:)
(: Test: K-ContextLastFunc-29                            :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:41+02:00                       :)
(: Purpose: fn:last() inside a predicate combined with a range expression and offset. :)
(:*******************************************************:)
(-20 to -5)[last() - 3] eq -8