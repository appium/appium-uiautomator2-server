(:*******************************************************:)
(: Test: K-SeqMAXFunc-16                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:41+02:00                       :)
(: Purpose: A test whose essence is: `string(max((1, xs:untypedAtomic("NaN"), xs:float(2)))) eq "NaN"`. :)
(:*******************************************************:)
string(max((1, xs:untypedAtomic("NaN"), xs:float(2)))) eq "NaN"