(:*******************************************************:)
(: Test: K-SeqMINFunc-32                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:41+02:00                       :)
(: Purpose: A test whose essence is: `min((xs:float(-3), xs:untypedAtomic("3"), xs:double("NaN"))) instance of xs:double`. :)
(:*******************************************************:)
min((xs:float(-3), xs:untypedAtomic("3"), xs:double("NaN")))
			instance of xs:double