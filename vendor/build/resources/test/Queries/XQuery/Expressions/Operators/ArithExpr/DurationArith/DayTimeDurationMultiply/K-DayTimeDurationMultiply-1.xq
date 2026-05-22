(:*******************************************************:)
(: Test: K-DayTimeDurationMultiply-1                     :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: Simple test of multiplying a xs:dayTimeDuration with 3. :)
(:*******************************************************:)
xs:dayTimeDuration("P3DT4H3M3.100S") * 3
	                eq xs:dayTimeDuration("P9DT12H9M9.3S")