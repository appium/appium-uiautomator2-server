(:*******************************************************:)
(: Test: K-SeqExprCast-210                               :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Simple test of casting xs:dayTimeDuration to xs:yearMonthDuration. :)
(:*******************************************************:)

			xs:string(xs:yearMonthDuration(xs:dayTimeDuration("P31DT3H2M10.001S")))
			eq "P0M"