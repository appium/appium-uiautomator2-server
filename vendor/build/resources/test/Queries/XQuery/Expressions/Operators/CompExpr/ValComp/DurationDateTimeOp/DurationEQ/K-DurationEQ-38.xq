(:*******************************************************:)
(: Test: K-DurationEQ-38                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: The 'gt' operator is not available between xs:duration and xs:dayTimeDuration(reversed operand order). :)
(:*******************************************************:)
 xs:dayTimeDuration("P3DT08H34M12.143S") gt
		xs:duration("P1999Y10M3DT08H34M12.143S") 