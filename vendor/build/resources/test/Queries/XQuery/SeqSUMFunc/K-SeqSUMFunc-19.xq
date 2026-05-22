(:*******************************************************:)
(: Test: K-SeqSUMFunc-19                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:41+02:00                       :)
(: Purpose: A test whose essence is: `sum((1, 2, xs:untypedAtomic("3"))) eq 6`. :)
(:*******************************************************:)
sum((1, 2, xs:untypedAtomic("3"))) eq 6