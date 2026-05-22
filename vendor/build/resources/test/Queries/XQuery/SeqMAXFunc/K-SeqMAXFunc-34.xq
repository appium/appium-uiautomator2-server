(:*******************************************************:)
(: Test: K-SeqMAXFunc-34                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:41+02:00                       :)
(: Purpose: A test whose essence is: `string(max(xs:double("NaN"))) eq "NaN"`. :)
(:*******************************************************:)
string(max(xs:double("NaN"))) eq "NaN"