(:*******************************************************:)
(: Test: K-YearMonthDurationDivide-3                     :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: Simple test of dividing a xs:yearMonthDuration with xs:double('INF'). :)
(:*******************************************************:)
xs:yearMonthDuration("P3Y36M") div xs:double("INF") eq
			   xs:yearMonthDuration("P0M")