(:*******************************************************:)
(: Test: K-SeqExprCast-211                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Simple test of casting a negative xs:duration to xs:yearMonthDuration. :)
(:*******************************************************:)

			xs:string(xs:yearMonthDuration(xs:duration("-P3Y0M31DT3H2M10.001S")))
			eq "-P3Y"