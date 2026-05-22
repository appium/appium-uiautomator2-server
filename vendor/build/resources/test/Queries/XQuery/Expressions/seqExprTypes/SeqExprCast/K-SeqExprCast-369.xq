(:*******************************************************:)
(: Test: K-SeqExprCast-369                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Ensure when casting xs:time to xs:string that an empty milli seconds part is not serialized. :)
(:*******************************************************:)
xs:string(xs:time("21:01:23.000")) eq "21:01:23"