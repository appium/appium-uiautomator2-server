(:*******************************************************:)
(: Test: K-SeqSUMFunc-14                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:41+02:00                       :)
(: Purpose: A test whose essence is: `string(sum((xs:float("NaN"), 1, 2, 3))) eq "NaN"`. :)
(:*******************************************************:)
string(sum((xs:float("NaN"), 1, 2, 3))) eq "NaN"