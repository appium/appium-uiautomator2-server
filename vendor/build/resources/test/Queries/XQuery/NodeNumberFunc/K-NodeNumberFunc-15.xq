(:*******************************************************:)
(: Test: K-NodeNumberFunc-15                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `string(number(xs:gYear("2005"))) eq "NaN"`. :)
(:*******************************************************:)
string(number(xs:gYear("2005"))) eq "NaN"