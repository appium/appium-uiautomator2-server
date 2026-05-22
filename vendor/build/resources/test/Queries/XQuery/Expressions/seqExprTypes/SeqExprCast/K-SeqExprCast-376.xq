(:*******************************************************:)
(: Test: K-SeqExprCast-376                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: The canonical form of an xs:time containing 24:00:00 is 00:00:00. :)
(:*******************************************************:)
string(xs:time("24:00:00")) eq "00:00:00"