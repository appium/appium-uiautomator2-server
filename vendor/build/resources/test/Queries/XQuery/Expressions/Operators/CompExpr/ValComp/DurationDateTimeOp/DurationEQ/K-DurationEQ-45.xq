(:*******************************************************:)
(: Test: K-DurationEQ-45                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: The 'ge' operator is not available between xs:yearMonthDuration and xs:dayTimeDuration(reversed operand order). :)
(:*******************************************************:)
 xs:dayTimeDuration("P3DT08H34M12.143S") ge
		xs:yearMonthDuration("P1999Y10M") 