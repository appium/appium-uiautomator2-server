(:*******************************************************:)
(: Test: K-SeqExprCast-205                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Ensure that when casting xs:yearMonthDuration to xs:string, that empty fields are properly serialized. :)
(:*******************************************************:)
xs:string(xs:yearMonthDuration("P0Y0M")) eq "P0M"