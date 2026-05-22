(:*******************************************************:)
(: Test: K-DayTimeDurationEQ-3                           :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Simple test of 'eq' for xs:dayTimeDuration.  :)
(:*******************************************************:)
not(xs:dayTimeDuration("P3DT08H34M12.043S") eq
		   xs:dayTimeDuration("P3DT08H34M12.143S"))