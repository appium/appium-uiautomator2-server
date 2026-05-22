(:*******************************************************:)
(: Test: K-SeqMINFunc-15                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:41+02:00                       :)
(: Purpose: A test whose essence is: `min((3, xs:untypedAtomic("1"), xs:float(2))) instance of xs:double`. :)
(:*******************************************************:)
min((3, xs:untypedAtomic("1"), xs:float(2))) instance of xs:double