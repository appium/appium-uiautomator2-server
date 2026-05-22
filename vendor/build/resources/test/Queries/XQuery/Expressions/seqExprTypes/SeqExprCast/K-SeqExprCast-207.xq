(:*******************************************************:)
(: Test: K-SeqExprCast-207                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Test that a xs:yearMonthDuration value with a large year and month component is serialized properly. :)
(:*******************************************************:)
xs:string(xs:yearMonthDuration("-P543Y456M")) eq "-P581Y"