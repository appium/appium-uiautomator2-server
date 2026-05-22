(:*******************************************************:)
(: Test: K-NumericSubtract-22                            :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: A test whose essence is: `string(xs:double("NaN") - 3) eq "NaN"`. :)
(:*******************************************************:)
string(xs:double("NaN") - 3) eq "NaN"