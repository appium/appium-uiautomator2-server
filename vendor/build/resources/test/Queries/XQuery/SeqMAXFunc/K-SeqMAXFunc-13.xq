(:*******************************************************:)
(: Test: K-SeqMAXFunc-13                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:41+02:00                       :)
(: Purpose: A test whose essence is: `max((1, xs:float(2), xs:untypedAtomic("3"))) instance of xs:double`. :)
(:*******************************************************:)
max((1, xs:float(2), xs:untypedAtomic("3"))) instance of xs:double