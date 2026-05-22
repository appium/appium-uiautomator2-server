(:*******************************************************:)
(: Test: K-SeqExprCast-204                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Ensure that a negative xs:yearMonthDuration is properly serialized when cast to xs:string. :)
(:*******************************************************:)
xs:string(xs:yearMonthDuration("-P0010Y0010M")) eq "-P10Y10M"