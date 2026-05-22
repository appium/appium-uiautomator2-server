(:*******************************************************:)
(: Test: K-SeqExprCast-409                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Ensure when casting xs:date to xs:string that a milli seconds part of '435' is handled properly. :)
(:*******************************************************:)
xs:string(xs:date("2002-02-15")) eq "2002-02-15"