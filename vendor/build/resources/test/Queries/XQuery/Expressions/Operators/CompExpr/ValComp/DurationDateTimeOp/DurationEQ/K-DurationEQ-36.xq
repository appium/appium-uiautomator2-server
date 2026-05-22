(:*******************************************************:)
(: Test: K-DurationEQ-36                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: The 'le' operator is not available between xs:duration and xs:dayTimeDuration. :)
(:*******************************************************:)
xs:duration("P1999Y10M3DT08H34M12.143S") le
			   xs:dayTimeDuration("P3DT08H34M12.143S")