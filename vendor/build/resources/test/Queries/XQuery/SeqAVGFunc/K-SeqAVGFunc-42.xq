(:*******************************************************:)
(: Test: K-SeqAVGFunc-42                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:41+02:00                       :)
(: Purpose: A test whose essence is: `string(avg(((3, 4, 5), xs:float('NaN')))) eq "NaN"`. :)
(:*******************************************************:)
string(avg(((3, 4, 5), xs:float('NaN')))) eq "NaN"