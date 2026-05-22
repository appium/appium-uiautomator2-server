(:*******************************************************:)
(: Test: K-SeqRemoveFunc-17                              :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: Combine fn:remove() with operator 'eq'.      :)
(:*******************************************************:)
remove((4, xs:untypedAtomic("4")), 1) eq 4