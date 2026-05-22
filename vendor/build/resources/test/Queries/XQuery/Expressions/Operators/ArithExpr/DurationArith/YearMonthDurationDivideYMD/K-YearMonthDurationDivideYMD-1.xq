(:*******************************************************:)
(: Test: K-YearMonthDurationDivideYMD-1                  :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: Simple test of dividing a xs:yearMonthDuration with xs:yearMonthDuration. :)
(:*******************************************************:)
xs:yearMonthDuration("P3Y36M") div xs:yearMonthDuration("P60Y")
			   eq 0.1