(:*******************************************************:)
(: Test: K-SeqSUMFunc-13                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:41+02:00                       :)
(: Purpose: A test whose essence is: `string(sum((xs:double("NaN"), 1, 2, 3))) eq "NaN"`. :)
(:*******************************************************:)
string(sum((xs:double("NaN"), 1, 2, 3))) eq "NaN"