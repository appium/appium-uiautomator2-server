(:*******************************************************:)
(: Test: K-SeqMINFunc-26                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:41+02:00                       :)
(: Purpose: A test whose essence is: `string(min((xs:float("NaN"), xs:float("NaN")))) eq "NaN"`. :)
(:*******************************************************:)
string(min((xs:float("NaN"), xs:float("NaN")))) eq "NaN"