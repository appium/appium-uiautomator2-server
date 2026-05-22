(:*******************************************************:)
(: Test: K-SeqExprCast-163                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: The canonical lexical representation for the xs:duration value P3Y0M is 'P3Y'. :)
(:*******************************************************:)
xs:string(xs:yearMonthDuration("P3Y0M")) eq "P3Y"