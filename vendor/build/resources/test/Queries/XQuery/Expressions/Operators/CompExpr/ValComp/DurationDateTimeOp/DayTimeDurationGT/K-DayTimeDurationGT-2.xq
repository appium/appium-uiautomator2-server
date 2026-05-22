(:*******************************************************:)
(: Test: K-DayTimeDurationGT-2                           :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Simple test of 'gt' for xs:dayTimeDuration, evaluating to false. :)
(:*******************************************************:)
not(xs:dayTimeDuration("P3DT08H34M12.144S") gt
			   xs:dayTimeDuration("P3DT08H34M12.144S"))