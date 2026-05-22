(:*******************************************************:)
(: Test: K-SeqAVGFunc-24                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `string(avg((1, 2, xs:double("NaN"), 1, 2, 3))) eq "NaN"`. :)
(:*******************************************************:)
string(avg((1, 2, xs:double("NaN"), 1, 2, 3))) eq "NaN"