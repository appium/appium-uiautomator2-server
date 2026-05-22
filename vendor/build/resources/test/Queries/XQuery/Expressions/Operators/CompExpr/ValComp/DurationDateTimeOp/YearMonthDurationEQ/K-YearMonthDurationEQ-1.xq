(:*******************************************************:)
(: Test: K-YearMonthDurationEQ-1                         :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Simple test of 'eq' for xs:yearMonthDuration, returning positive. :)
(:*******************************************************:)
xs:yearMonthDuration("P1999Y10M") eq
		   xs:yearMonthDuration("P1999Y10M")