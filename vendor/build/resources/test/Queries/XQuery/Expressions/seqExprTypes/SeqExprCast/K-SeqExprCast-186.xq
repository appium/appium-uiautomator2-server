(:*******************************************************:)
(: Test: K-SeqExprCast-186                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Test that a xs:dayTimeDuration value with a large minute component is serialized properly. :)
(:*******************************************************:)
xs:string(xs:dayTimeDuration("PT6000M")) eq "P4DT4H"