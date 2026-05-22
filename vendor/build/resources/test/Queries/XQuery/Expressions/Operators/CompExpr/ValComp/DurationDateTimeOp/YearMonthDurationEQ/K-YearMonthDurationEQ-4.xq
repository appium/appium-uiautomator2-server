(:*******************************************************:)
(: Test: K-YearMonthDurationEQ-4                         :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Simple test of 'ne' for xs:yearMonthDuration. :)
(:*******************************************************:)
xs:yearMonthDuration("P1999Y01M") ne
		   xs:yearMonthDuration("P1999Y10M")