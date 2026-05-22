(:*******************************************************:)
(: Test: K-DurationEQ-1                                  :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: Simple test of 'eq' for xs:duration, returning positive. :)
(:*******************************************************:)
xs:duration("P1999Y10M3DT08H34M12.143S") eq
		   xs:duration("P1999Y10M3DT08H34M12.143S")