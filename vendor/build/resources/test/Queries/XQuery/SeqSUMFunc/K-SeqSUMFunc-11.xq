(:*******************************************************:)
(: Test: K-SeqSUMFunc-11                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:41+02:00                       :)
(: Purpose: A test whose essence is: `string(sum((1, 2, 3, xs:float("NaN")))) eq "NaN"`. :)
(:*******************************************************:)
string(sum((1, 2, 3, xs:float("NaN")))) eq "NaN"