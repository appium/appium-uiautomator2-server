(:*******************************************************:)
(: Test: K-NodeNumberFunc-12                             :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:40+02:00                       :)
(: Purpose: fn:number() applied to a type which a cast regardless of source value never would succeed for. :)
(:*******************************************************:)
string(number(xs:anyURI("example.com/"))) eq "NaN"