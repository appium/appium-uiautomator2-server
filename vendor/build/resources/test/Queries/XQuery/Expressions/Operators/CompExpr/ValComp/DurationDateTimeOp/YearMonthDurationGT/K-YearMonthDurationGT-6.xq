(:*******************************************************:)
(: Test: K-YearMonthDurationGT-6                         :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Simple test of 'ge' for xs:yearMonthDuration, evaluating to false. :)
(:*******************************************************:)
not(xs:yearMonthDuration("P1999Y9M") ge
			   xs:yearMonthDuration("P1999Y10M"))