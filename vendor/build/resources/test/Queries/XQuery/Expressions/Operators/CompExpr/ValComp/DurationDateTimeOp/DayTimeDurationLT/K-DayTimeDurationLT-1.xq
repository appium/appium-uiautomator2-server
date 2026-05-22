(:*******************************************************:)
(: Test: K-DayTimeDurationLT-1                           :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Simple test of 'lt' for xs:dayTimeDuration.  :)
(:*******************************************************:)
xs:dayTimeDuration("P3DT08H34M12.142S") lt
			   xs:dayTimeDuration("P3DT08H34M12.143S")