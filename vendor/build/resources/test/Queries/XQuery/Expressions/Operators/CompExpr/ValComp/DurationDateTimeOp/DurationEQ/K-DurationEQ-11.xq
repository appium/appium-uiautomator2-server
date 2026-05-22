(:*******************************************************:)
(: Test: K-DurationEQ-11                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: The 'lt' operator is not available between xs:duration and xs:duration. :)
(:*******************************************************:)
xs:duration("P1999Y10M3DT08H34M12.142S") lt
			   xs:duration("P1999Y10M3DT08H34M12.143S")