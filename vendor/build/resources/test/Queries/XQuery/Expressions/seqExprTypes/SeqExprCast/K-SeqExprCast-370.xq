(:*******************************************************:)
(: Test: K-SeqExprCast-370                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Ensure when casting xs:time to xs:string that milli seconds have no trailing zeros. :)
(:*******************************************************:)
xs:string(xs:time("21:01:23.100")) eq "21:01:23.1"