(:*******************************************************:)
(: Test: K-DateTimeGT-1                                  :)
(: Written by: Frans Englich                             :)
(: Date: 2006-10-05T18:29:37+02:00                       :)
(: Purpose: Simple test of 'gt' for xs:dateTime.         :)
(:*******************************************************:)
xs:dateTime("2004-07-13T23:01:04.12") gt
			   xs:dateTime("2004-07-12T23:01:04.12")