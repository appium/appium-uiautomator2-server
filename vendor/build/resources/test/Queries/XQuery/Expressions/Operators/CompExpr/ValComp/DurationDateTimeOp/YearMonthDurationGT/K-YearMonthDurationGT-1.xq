(:*******************************************************:)
(: Test: K-YearMonthDurationGT-1                         :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Simple test of 'gt' for xs:yearMonthDuration. :)
(:*******************************************************:)
xs:yearMonthDuration("P1999Y10M") gt
			   xs:yearMonthDuration("P1999Y9M")