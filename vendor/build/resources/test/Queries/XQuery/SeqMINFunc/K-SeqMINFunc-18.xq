(:*******************************************************:)
(: Test: K-SeqMINFunc-18                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:41+02:00                       :)
(: Purpose: A test whose essence is: `min((xs:float("NaN"), xs:untypedAtomic("3"), xs:double(2))) instance of xs:double`. :)
(:*******************************************************:)
min((xs:float("NaN"), xs:untypedAtomic("3"), xs:double(2)))
			instance of xs:double