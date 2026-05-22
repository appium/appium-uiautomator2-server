(:*******************************************************:)
(: Test: K-SeqMAXFunc-18                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:41+02:00                       :)
(: Purpose: A test whose essence is: `max((xs:float("NaN"), xs:untypedAtomic("3"), xs:float(2))) instance of xs:double`. :)
(:*******************************************************:)
max((xs:float("NaN"), xs:untypedAtomic("3"), xs:float(2)))
			instance of xs:double