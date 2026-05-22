(:*******************************************************:)
(: Test: K-SeqExprCast-208                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: The canonical form of the xs:yearMonthDuration value -P0M is P0M. :)
(:*******************************************************:)
xs:string(xs:yearMonthDuration("-P0M")) eq "P0M"