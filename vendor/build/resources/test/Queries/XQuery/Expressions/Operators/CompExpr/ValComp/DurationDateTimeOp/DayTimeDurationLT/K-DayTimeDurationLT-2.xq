(:*******************************************************:)
(: Test: K-DayTimeDurationLT-2                           :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Simple test of 'lt' for xs:dayTimeDuration, evaluating to false. :)
(:*******************************************************:)
not(xs:dayTimeDuration("P3DT08H34M12.143S") lt
			   xs:dayTimeDuration("P3DT08H34M12.143S"))