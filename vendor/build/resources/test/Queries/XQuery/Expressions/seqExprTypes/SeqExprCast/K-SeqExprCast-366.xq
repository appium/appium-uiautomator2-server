(:*******************************************************:)
(: Test: K-SeqExprCast-366                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Simple xs:time involving time with no milli seconds. :)
(:*******************************************************:)
string(xs:time("12:44:05")) eq "12:44:05"