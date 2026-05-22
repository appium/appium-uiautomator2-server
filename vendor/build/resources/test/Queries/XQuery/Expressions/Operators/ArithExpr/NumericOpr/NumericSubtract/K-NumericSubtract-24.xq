(:*******************************************************:)
(: Test: K-NumericSubtract-24                            :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: A test whose essence is: `string(3 - xs:double("NaN")) eq "NaN"`. :)
(:*******************************************************:)
string(3 - xs:double("NaN")) eq "NaN"