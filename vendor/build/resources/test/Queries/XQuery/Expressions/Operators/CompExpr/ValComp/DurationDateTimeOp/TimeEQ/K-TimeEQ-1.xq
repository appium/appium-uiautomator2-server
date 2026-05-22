(:*******************************************************:)
(: Test: K-TimeEQ-1                                      :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Simple test of 'eq' for xs:time, returning positive. :)
(:*******************************************************:)
xs:time("23:01:04.12") eq
		              xs:time("23:01:04.12")