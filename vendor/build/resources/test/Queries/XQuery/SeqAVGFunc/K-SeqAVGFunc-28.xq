(:*******************************************************:)
(: Test: K-SeqAVGFunc-28                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `avg((1, 2, xs:untypedAtomic("3"))) eq 2`. :)
(:*******************************************************:)
avg((1, 2, xs:untypedAtomic("3"))) eq 2