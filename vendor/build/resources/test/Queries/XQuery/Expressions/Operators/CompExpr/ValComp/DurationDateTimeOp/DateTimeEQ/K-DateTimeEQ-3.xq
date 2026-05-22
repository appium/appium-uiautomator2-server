(:*******************************************************:)
(: Test: K-DateTimeEQ-3                                  :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Simple test of 'ne' for xs:dateTime.         :)
(:*******************************************************:)
xs:dateTime("2004-08-12T23:01:05.12") ne
			   xs:dateTime("2004-08-12T23:01:04.12")