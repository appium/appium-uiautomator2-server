(:*******************************************************:)
(: Test: K-SeqExprCast-375                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Ensure when casting xs:time to xs:string that a milli seconds part of '110' is handled properly. :)
(:*******************************************************:)
xs:string(xs:time("21:01:23.110")) eq "21:01:23.11"