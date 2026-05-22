(:*******************************************************:)
(: Test: K-SeqMAXFunc-28                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:41+02:00                       :)
(: Purpose: A test whose essence is: `string(max((3, xs:float("NaN")))) eq "NaN"`. :)
(:*******************************************************:)
string(max((3, xs:float("NaN")))) eq "NaN"