(:*******************************************************:)
(: Test: K-SeqMINFunc-17                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:41+02:00                       :)
(: Purpose: A test whose essence is: `string(min((xs:float("NaN"), xs:untypedAtomic("3"), xs:float(2)))) eq "NaN"`. :)
(:*******************************************************:)
string(min((xs:float("NaN"), xs:untypedAtomic("3"), xs:float(2)))) eq "NaN"