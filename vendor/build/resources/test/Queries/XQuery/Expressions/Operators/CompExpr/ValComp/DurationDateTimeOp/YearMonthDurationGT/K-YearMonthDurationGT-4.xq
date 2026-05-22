(:*******************************************************:)
(: Test: K-YearMonthDurationGT-4                         :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Simple test of 'ge' for xs:yearMonthDuration. :)
(:*******************************************************:)
xs:yearMonthDuration("P1999Y11M") ge
			   xs:yearMonthDuration("P1999Y10M")