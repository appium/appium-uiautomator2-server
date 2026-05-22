(:*******************************************************:)
(: Test: K-SeqExprCast-206                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Test that a xs:yearMonthDuration value with a large year component is serialized properly. :)
(:*******************************************************:)
xs:string(xs:yearMonthDuration("P2Y323M")) eq "P28Y11M"