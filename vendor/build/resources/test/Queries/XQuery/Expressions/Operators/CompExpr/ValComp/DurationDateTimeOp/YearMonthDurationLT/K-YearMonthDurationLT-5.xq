(:*******************************************************:)
(: Test: K-YearMonthDurationLT-5                         :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Simple test of 'le' for xs:yearMonthDuration. :)
(:*******************************************************:)
xs:yearMonthDuration("P1999Y9M") le
			   xs:yearMonthDuration("P1999Y10M")