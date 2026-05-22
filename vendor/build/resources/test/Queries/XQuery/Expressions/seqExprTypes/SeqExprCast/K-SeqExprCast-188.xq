(:*******************************************************:)
(: Test: K-SeqExprCast-188                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Test that a xs:dayTimeDuration value with a small second component is serialized properly. :)
(:*******************************************************:)
xs:string(xs:dayTimeDuration("P31DT3H2M10.001S"))
		eq "P31DT3H2M10.001S"