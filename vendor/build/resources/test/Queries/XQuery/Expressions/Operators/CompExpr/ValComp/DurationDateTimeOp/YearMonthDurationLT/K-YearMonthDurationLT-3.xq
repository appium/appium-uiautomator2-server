(:*******************************************************:)
(: Test: K-YearMonthDurationLT-3                         :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Simple test of 'lt' for xs:yearMonthDuration, evaluating to false. :)
(:*******************************************************:)
not(xs:yearMonthDuration("P1999Y10M") lt
			   xs:yearMonthDuration("P1999Y9M"))