(:*******************************************************:)
(: Test: K-NodeNumberFunc-13                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: A test whose essence is: `string(number(xs:anyURI("1"))) eq "NaN"`. :)
(:*******************************************************:)
string(number(xs:anyURI("1"))) eq "NaN"