(:*******************************************************:)
(: Test: K-SeqAVGFunc-29                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `avg((xs:float(1), 2, xs:untypedAtomic("3"))) eq 2`. :)
(:*******************************************************:)
avg((xs:float(1), 2, xs:untypedAtomic("3"))) eq 2