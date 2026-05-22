(:*******************************************************:)
(: Test: K-DurationEQ-60                                 :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: The 'le' operator is not available between xs:yearMonthDuration and xs:duration(reversed operand order)(reversed operand order). :)
(:*******************************************************:)
xs:duration("P3DT08H34M12.143S") le
		xs:yearMonthDuration("P1999Y10M")