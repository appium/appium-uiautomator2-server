(:*******************************************************:)
(: Test: K-DayTimeDurationDivide-3                       :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: Simple test of dividing a xs:dayTimeDuration with xs:double('INF'). :)
(:*******************************************************:)
xs:dayTimeDuration("P3D") div xs:double("INF") eq
			   xs:dayTimeDuration("PT0S")