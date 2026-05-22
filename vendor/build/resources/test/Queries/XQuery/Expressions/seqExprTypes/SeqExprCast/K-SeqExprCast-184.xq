(:*******************************************************:)
(: Test: K-SeqExprCast-184                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Test that a xs:dayTimeDuration value with a large day component is serialized properly. :)
(:*******************************************************:)
xs:string(xs:dayTimeDuration("P9876DT1M")) eq "P9876DT1M"