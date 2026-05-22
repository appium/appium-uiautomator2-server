(:*******************************************************:)
(: Test: K-SeqExprCast-168                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Simple test of casting a negative xs:dayTimeDuration to xs:duration. :)
(:*******************************************************:)

			xs:string(xs:duration(xs:dayTimeDuration("-P31DT3H2M10.001S")))
			eq "-P31DT3H2M10.001S"