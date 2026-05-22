(:*******************************************************:)
(: Test: K-DurationEQ-14                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: The 'ge' operator is not available between xs:duration and xs:duration. :)
(:*******************************************************:)
xs:duration("P1999Y10M3DT08H34M12.143S") ge
			   xs:duration("P1999Y10M3DT08H34M12.143S")