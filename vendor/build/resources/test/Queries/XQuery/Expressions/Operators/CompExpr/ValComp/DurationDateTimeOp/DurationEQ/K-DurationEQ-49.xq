(:*******************************************************:)
(: Test: K-DurationEQ-49                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: The 'ge' operator is not available between xs:yearMonthDuration and xs:duration. :)
(:*******************************************************:)
xs:yearMonthDuration("P1999Y10M") ge
			   xs:duration("P3DT08H34M12.143S")