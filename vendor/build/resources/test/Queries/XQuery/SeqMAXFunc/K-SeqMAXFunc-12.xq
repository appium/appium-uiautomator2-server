(:*******************************************************:)
(: Test: K-SeqMAXFunc-12                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:41+02:00                       :)
(: Purpose: A test whose essence is: `max((1, xs:float(2), xs:untypedAtomic("3"))) eq 3`. :)
(:*******************************************************:)
max((1, xs:float(2), xs:untypedAtomic("3"))) eq 3