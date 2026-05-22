(:*******************************************************:)
(: Test: K-ContextPositionFunc-7                         :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:41+02:00                       :)
(: Purpose: fn:position() can never return 0('!=').      :)
(:*******************************************************:)
deep-equal(
(1, 2, 3, remove((current-time(), 4), 1))
[position() != 0],
(1, 2, 3, 4))