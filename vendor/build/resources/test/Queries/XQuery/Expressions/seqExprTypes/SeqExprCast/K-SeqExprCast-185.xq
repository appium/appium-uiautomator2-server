(:*******************************************************:)
(: Test: K-SeqExprCast-185                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Test that a xs:dayTimeDuration value with a large hour component is serialized properly. :)
(:*******************************************************:)
xs:string(xs:dayTimeDuration("PT9876H1M")) eq "P411DT12H1M"