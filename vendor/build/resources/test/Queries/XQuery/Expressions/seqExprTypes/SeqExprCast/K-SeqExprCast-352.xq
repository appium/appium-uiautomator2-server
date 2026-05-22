(:*******************************************************:)
(: Test: K-SeqExprCast-352                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: The canonical form of an xs:dateTime containing 24:00:00 is the following day at 00:00:00. :)
(:*******************************************************:)
string(xs:dateTime("2004-03-31T24:00:00")) eq "2004-04-01T00:00:00"