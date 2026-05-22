(:*******************************************************:)
(: Test: K-SeqMAXFunc-31                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:41+02:00                       :)
(: Purpose: A test whose essence is: `string(max((xs:float(-3), xs:untypedAtomic("3"), xs:double("NaN")))) eq "NaN"`. :)
(:*******************************************************:)
string(max((xs:float(-3), xs:untypedAtomic("3"), xs:double("NaN"))))
			eq "NaN"