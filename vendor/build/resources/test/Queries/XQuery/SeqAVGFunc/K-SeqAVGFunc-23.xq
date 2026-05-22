(:*******************************************************:)
(: Test: K-SeqAVGFunc-23                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `string(avg((xs:float("NaN"), 1, 2, 3))) eq "NaN"`. :)
(:*******************************************************:)
string(avg((xs:float("NaN"), 1, 2, 3))) eq "NaN"