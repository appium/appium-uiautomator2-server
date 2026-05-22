(:*******************************************************:)
(: Test: K-YearMonthDurationDivide-1                     :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: Simple test of dividing a xs:yearMonthDuration with 3. :)
(:*******************************************************:)
xs:yearMonthDuration("P3Y36M") div 3
			eq xs:yearMonthDuration("P2Y")