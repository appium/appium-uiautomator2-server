(:*******************************************************:)
(: Test: K-SeqAVGFunc-41                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:41+02:00                       :)
(: Purpose: A test whose essence is: `string(avg((xs:float('INF'), xs:float('-INF')))) eq "NaN"`. :)
(:*******************************************************:)
string(avg((xs:float('INF'), xs:float('-INF')))) eq "NaN"