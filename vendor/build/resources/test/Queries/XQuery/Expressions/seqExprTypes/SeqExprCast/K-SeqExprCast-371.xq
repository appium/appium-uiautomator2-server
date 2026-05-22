(:*******************************************************:)
(: Test: K-SeqExprCast-371                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Ensure when casting xs:time to xs:string that milli seconds are properly padded with zeros. :)
(:*******************************************************:)
xs:string(xs:time("21:01:23.001")) eq "21:01:23.001"