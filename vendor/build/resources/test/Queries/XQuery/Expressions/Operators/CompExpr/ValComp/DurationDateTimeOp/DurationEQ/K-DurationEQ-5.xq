(:*******************************************************:)
(: Test: K-DurationEQ-5                                  :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Simple test of 'ne' for xs:duration.         :)
(:*******************************************************:)
not(xs:duration("P1999Y10M3DT08H34M12.143S") ne
		   xs:duration("P1999Y10M3DT08H34M12.143S"))