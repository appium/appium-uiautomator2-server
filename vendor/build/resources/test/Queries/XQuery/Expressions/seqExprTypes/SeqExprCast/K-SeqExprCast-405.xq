(:*******************************************************:)
(: Test: K-SeqExprCast-405                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:38+02:00                       :)
(: Purpose: Ensure when casting xs:date to xs:string that an empty milli seconds part is not serialized. :)
(:*******************************************************:)
xs:string(xs:date("2002-02-15")) eq "2002-02-15"