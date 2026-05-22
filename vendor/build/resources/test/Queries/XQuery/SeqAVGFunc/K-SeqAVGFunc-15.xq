(:*******************************************************:)
(: Test: K-SeqAVGFunc-15                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `string(avg((3, xs:double("NaN"), 3))) eq "NaN"`. :)
(:*******************************************************:)
string(avg((3, xs:double("NaN"), 3))) eq "NaN"