(:*******************************************************:)
(: Test: K-SeqExprCast-192                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Simple test of casting a negative xs:duration to xs:dayTimeDuration. :)
(:*******************************************************:)

			xs:string(xs:dayTimeDuration(xs:duration("-P3Y0M31DT3H2M10.001S")))
			eq "-P31DT3H2M10.001S"