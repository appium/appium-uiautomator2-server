(:*******************************************************:)
(: Test: K-DurationEQ-40                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: The 'le' operator is not available between xs:duration and xs:dayTimeDuration(reversed operand order). :)
(:*******************************************************:)
 xs:dayTimeDuration("P3DT08H34M12.143S") le
		xs:duration("P1999Y10M3DT08H34M12.143S")