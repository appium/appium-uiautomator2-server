(:*******************************************************:)
(: Test: K-SeqSUMFunc-21                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:41+02:00                       :)
(: Purpose: A test whose essence is: `sum((xs:float(1), 2, xs:untypedAtomic("3"))) instance of xs:double`. :)
(:*******************************************************:)
sum((xs:float(1), 2, xs:untypedAtomic("3"))) instance of xs:double