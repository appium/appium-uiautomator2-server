(:*******************************************************:)
(: Test: K-YearMonthDurationMultiply-4                   :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:36+02:00                       :)
(: Purpose: Simple test of multiplying a xs:yearMonthDuration with 0. :)
(:*******************************************************:)
xs:yearMonthDuration("P3Y36M") * 0
			eq xs:yearMonthDuration("P0M")