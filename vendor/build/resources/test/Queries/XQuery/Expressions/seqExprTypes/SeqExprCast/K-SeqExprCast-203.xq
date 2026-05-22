(:*******************************************************:)
(: Test: K-SeqExprCast-203                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Ensure that when casting xs:yearMonthDuration to xs:string, that preceding zeros are handled properly. :)
(:*******************************************************:)
xs:string(xs:yearMonthDuration("P0010Y0010M")) eq "P10Y10M"