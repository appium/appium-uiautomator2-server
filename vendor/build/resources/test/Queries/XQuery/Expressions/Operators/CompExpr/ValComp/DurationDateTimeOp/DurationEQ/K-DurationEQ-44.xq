(:*******************************************************:)
(: Test: K-DurationEQ-44                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: The 'le' operator is not available between xs:yearMonthDuration and xs:dayTimeDuration. :)
(:*******************************************************:)
xs:yearMonthDuration("P1999Y10M") le
			   xs:dayTimeDuration("P3DT08H34M12.143S")