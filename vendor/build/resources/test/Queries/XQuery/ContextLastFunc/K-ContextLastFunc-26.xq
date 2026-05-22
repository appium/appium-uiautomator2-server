(:*******************************************************:)
(: Test: K-ContextLastFunc-26                            :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:41+02:00                       :)
(: Purpose: fn:last() inside a predicate.                :)
(:*******************************************************:)
(1, 2, 3, 4, current-time(), 4, 5, 6)[last()] treat as xs:integer eq 6