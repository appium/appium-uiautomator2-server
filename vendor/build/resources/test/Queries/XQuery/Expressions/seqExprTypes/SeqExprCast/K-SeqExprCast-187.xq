(:*******************************************************:)
(: Test: K-SeqExprCast-187                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Test that a xs:dayTimeDuration value with a large second component is serialized properly. :)
(:*******************************************************:)
xs:string(xs:dayTimeDuration("PT1M1231.432S")) eq "PT21M31.432S"