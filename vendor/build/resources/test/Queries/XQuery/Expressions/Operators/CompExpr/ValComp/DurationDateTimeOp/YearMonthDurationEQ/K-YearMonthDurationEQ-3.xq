(:*******************************************************:)
(: Test: K-YearMonthDurationEQ-3                         :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Simple test of 'eq' for xs:yearMonthDuration. :)
(:*******************************************************:)
not(xs:yearMonthDuration("P1999Y") eq
		   xs:yearMonthDuration("P1999Y10M"))